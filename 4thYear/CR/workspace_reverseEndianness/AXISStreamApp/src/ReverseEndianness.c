/******************************************************************************
*
* Copyright (C) 2009 - 2014 Xilinx, Inc.  All rights reserved.
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* Use of the Software is limited solely to applications:
* (a) running on a Xilinx device, or
* (b) that interact with a Xilinx device through a bus or interconnect.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
* XILINX  BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF
* OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
* Except as contained in this notice, the name of the Xilinx shall not be used
* in advertising or otherwise to promote the sale, use or other dealings in
* this Software without prior written authorization from Xilinx.
*
******************************************************************************/

/*
 * helloworld.c: simple test application
 *
 * This application configures UART 16550 to baud rate 9600.
 * PS7 UART (Zynq) is not initialized by this application, since
 * bootrom/bsp configures it to baud rate 115200
 *
 * ------------------------------------------------
 * | UART TYPE   BAUD RATE                        |
 * ------------------------------------------------
 *   uartns550   9600
 *   uartlite    Configurable only in HW design
 *   ps7_uart    115200 (configured by bootrom/bsp)
 */

#include <stdlib.h>
#include <stdio.h>
#include "platform.h"
#include "xparameters.h"
#include "fsl.h"
#include "xtmrctr_l.h"
#include "xil_printf.h"

#include "stdbool.h"

#define min(a, b)	((a < b) ? a : b) //

#define N	4000  //processar 4k valores -> se quisermos aumentar temos que aumentar STACK
//se fosse uma stack de 132kilobytes -> nao podemos pq no BD temos definido no address editor temos 64k reservado para a memoria de dados
//umas centas de kilobytes ainda suporta se nao temos que usar memoria externa
void ReverseEndiannessSw(int* pDst, int* pSrc, unsigned int size)
{
	int* p;

	for (p = pSrc; p < pSrc + size; p++, pDst++)
	{
		*pDst = ((((*p) << 24) & 0xFF000000) | (((*p) <<  8) & 0x00FF0000) |
				 (((*p) >>  8) & 0x0000FF00) | (((*p) >> 24) & 0x000000FF));
	}
}

//MicroBlaze Processor Fast Simplex Link (FSL) Interface Macros
//The FSL channels are dedicated unidirectional point-to-point data streaming interfaces.
void ReverseEndiannessHw(int* pDst, int* pSrc, unsigned int size)
{
	int* p;

	for (p = pSrc; p < pSrc + size; p++, pDst++)
	{
		putfslx(*p, 0, FSL_DEFAULT);	//macros: *p - o valor a passar, 0 - o n�mero da interface, FSL_DEFAULT - flags (by default)
//		Performs a get function on an input FSL of the MicroBlaze processor;
//		id is the FSL identifier and is a literal in the range of 0 to 7 (0 to 15 for MicroBlaze v7.00.a and later).
//		The semantics of the instruction is determined by the valid FSL macro flags, which are listed in Table 2
		getfslx(*pDst, 0, FSL_DEFAULT);
	}
}

bool CheckReversedEndianness(int* pData1, int* pData2, unsigned int size)
{
	int* p;

	for (p = pData1; p < pData1 + size; p++, pData2++)
	{
		if (*pData2 != ((((*p) << 24) & 0xFF000000) | (((*p) <<  8) & 0x00FF0000) |
						(((*p) >>  8) & 0x0000FF00) | (((*p) >> 24) & 0x000000FF)))
		{
			return FALSE;
		}
	}

	return TRUE;
}

void PrintDataArray(int* pData, unsigned int size)
{
	int* p;

	xil_printf("\n\r");
	for (p = pData; p < pData + size; p++)
	{
		xil_printf("%08x  ", *p);
	}
}

void ResetPerformanceTimer()
{
	// Disable a timer counter such that it stops running (base address of the device, the specific timer counter within the device)
	XTmrCtr_Disable(XPAR_TMRCTR_0_BASEADDR, 0);
	/*
	 * Set the value that is loaded into the timer counter and cause it to
	 * be loaded into the timer counter
	 */
//	Set the Load Register of a timer counter to the specified value.
//	(the base address of the device, specific timer counter within the device, 32 bit value to be written to the register)
	XTmrCtr_SetLoadReg(XPAR_TMRCTR_0_BASEADDR, 0, 0);
//	Cause the timer counter to load it's Timer Counter Register with the value in the Load Register.
//	(the base address of the device, the specific timer counter within the device)
	XTmrCtr_LoadTimerCounterReg(XPAR_TMRCTR_0_BASEADDR, 0);
//	Set the Control Status Register of a timer counter to the specified value.
//	(base address of the device, specific timer counter within the device, 32 bit value to be written to the register)
	XTmrCtr_SetControlStatusReg(XPAR_TMRCTR_0_BASEADDR, 0, 0x00000000);
}

void RestartPerformanceTimer()
{
	ResetPerformanceTimer();
	XTmrCtr_Enable(XPAR_TMRCTR_0_BASEADDR, 0); //começar processo contagem
}

unsigned int GetPerformanceTimer()
{
	return XTmrCtr_GetTimerCounterReg(XPAR_TMRCTR_0_BASEADDR, 0);
}

unsigned int StopAndGetPerformanceTimer()
{
	XTmrCtr_Disable(XPAR_TMRCTR_0_BASEADDR, 0);
	return GetPerformanceTimer(); //ler conteudo do registo timer
}

int main()
{
	int srcData[N], dstData[N]; //array source array resultante do processamento
	//alocar 4k inteiros e cada, alocar na memoria STACK (variaveis automaticas/locais) -> 8000 +2  inteiros no codigo da main, cada inteiro é de 4 bytes
	//stack precisa de 8000 * 4 bytes +4 +2 = 32006 Bytes -> em hexadecimal 7D...
	//tamanho sack defult 0x400 hexadecimais
	unsigned int timeElapsed;
	//mais um inteiro
    init_platform();

    xil_printf("\n\rSoftware Only vs. Hardware Assisted Reverse Endianess Demonstration\n\r");

    RestartPerformanceTimer(); //timer reset
    srand(0); // medir tempo de tarefa de inicialização do array. Usar AXI timer (hardware timer)
    for (int i = 0; i < N; i++)
    {
    	//mais um inteiro a alocar
    	srcData[i] = rand();
    }
    timeElapsed = StopAndGetPerformanceTimer();//guarda nr ciclos de relogio que foram demorados
    xil_printf("\n\rArray initialization time: %d microseconds\n\r",
    		   timeElapsed / (XPAR_CPU_M_AXI_DP_FREQ_HZ / 1000000)); // each period is 10 ns = 0.01 us
    PrintDataArray(srcData, min(8, N));
    xil_printf("\n\r");

    // Software only
    RestartPerformanceTimer();
    ReverseEndiannessSw(dstData, srcData, N);
    timeElapsed = StopAndGetPerformanceTimer();
    xil_printf("\n\rSoftware only reverse endianness time: %d microseconds",
    		   timeElapsed / (XPAR_CPU_M_AXI_DP_FREQ_HZ / 1000000));
    PrintDataArray(dstData, min(8, N));
    xil_printf("\n\rChecking result: %s\n\r",
    		   CheckReversedEndianness(srcData, dstData, N) ? "OK" : "Error");

    // Hardware assisted
    RestartPerformanceTimer();
    ReverseEndiannessHw(dstData, srcData, N);
    timeElapsed = StopAndGetPerformanceTimer();
    xil_printf("\n\rHardware assisted reverse endianness time: %d microseconds",
    		   timeElapsed / (XPAR_CPU_M_AXI_DP_FREQ_HZ / 1000000));
    PrintDataArray(dstData, min(8, N));
    xil_printf("\n\rChecking result: %s\n\r",
    		   CheckReversedEndianness(srcData, dstData, N) ? "OK" : "Error");

    cleanup_platform();
    return 0;
}
