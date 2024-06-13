#include <stdlib.h>
#include <stdio.h>
#include "platform.h"
#include "xparameters.h"
#include "fsl.h"
#include "xtmrctr_l.h"
#include "xil_printf.h"
#include <math.h>
#include "stdbool.h"

#define min(a, b)	((a < b) ? a : b) // Macro to calculate the minimum of two values
#define N	2 // Number of coordinates

typedef struct
{
    int8_t x;
    int8_t y;
} Coordinate;

void generateRandomCoordinate(Coordinate* c)
{
    c->x = (int8_t)(rand() % 256 - 128);
    c->y = (int8_t)(rand() % 256 - 128);
}

void DistanceSquaredSw(unsigned int* pDst, Coordinate* pSrc, unsigned int size)
{
    for (unsigned int i = 0; i < size; i++)
    {
        Coordinate* index = pSrc + i * 2;
        int dx = (index + 1)->x - index->x;
        int dy = (index + 1)->y - index->y;
        pDst[i] = dx * dx + dy * dy;
    }
}

void DistanceSquaredHw(unsigned int* pDst, Coordinate* pSrc, unsigned int size)
{
    Coordinate* p;
    unsigned int * compacted_bytes = malloc(sizeof(int));

    for (p = pSrc; p < pSrc + size; p+=2, pDst++)
    {
        // Operation to compact the coordinates into a single 32-bit word
        // this is done this way because Coordinates store 8-bit values
        // which means that they can't be shifted more than 8 bits, therefore
        // we can't shift those 8 bits to the left and then OR them with the next value
        *compacted_bytes = (p+1)-y;                             // y2 = S_AXIS_TDATA(31 downto 24)                  
        *compacted_bytes = (*compacted_bytes << 8) | (p+1)->x;  // x2 = S_AXIS_TDATA(23 downto 16)
        *compacted_bytes = (*compacted_bytes << 8) | p->y;      // y1 = S_AXIS_TDATA(15 downto 8)
        *compacted_bytes = (*compacted_bytes << 8) | p->x;      // x1 = S_AXIS_TDATA(7 downto 0)  
        putfslx(*compacted_bytes, 0, FSL_DEFAULT);
        getfslx(*pDst, 0, FSL_DEFAULT);
    }
}

bool CheckEuclideanDistance( unsigned int* pData1,  unsigned int* pData2, unsigned int size)
{
    for (int i = 0; i < size/2; i++)
    {
        if (pData1[i] != pData2[i])
        {
            return false;
        }
    }
    return true;
}

void PrintDataArray(Coordinate* pData, unsigned int size)
{
	Coordinate* p;

	xil_printf("\n\r");
	for (p = pData; p < pData + size; p+=2)
    {
        xil_printf("%8d %8d %8d %8d ", p->x, p->y, (p+1)->x, (p+1)->y);
    }
    xil_printf("\n\r");
}

void PrintResultArray(unsigned int* pData, unsigned int size)
{
    unsigned int* p;

    xil_printf("\n\r");
    for (p = pData; p < pData + size; p++)
    {
        xil_printf("%8d ", *p);
    }
    xil_printf("\n\r");
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
    Coordinate srcData[N];
    unsigned int dstData[N/2], hwDstData[N/2];
    unsigned int timeElapsed;
    init_platform();

    xil_printf("\n\rSoftware Only vs. Hardware Assisted Euclidean Distance Demonstration\n\r");

    RestartPerformanceTimer();

    srand(0);

    for (int i = 0; i < N; i++)
    {
        generateRandomCoordinate(&srcData[i]);
        xil_printf("Coordinate %d: (%d, %d)\n\r", i, srcData[i].x, srcData[i].y);
    }

    timeElapsed = StopAndGetPerformanceTimer();
    xil_printf("\n\rArray initialization time: %d microseconds\n\r",
               timeElapsed / (XPAR_CPU_M_AXI_DP_FREQ_HZ / 1000000));

    PrintDataArray(srcData, N);

    if (N % 2 != 0)
    {
        xil_printf("Error: N must be a multiple of 2\n\r");
        cleanup_platform();
        return 1;
    }

    // Software only
    RestartPerformanceTimer();
    DistanceSquaredSw(dstData, srcData, N);
    timeElapsed = StopAndGetPerformanceTimer();
    xil_printf("\n\rSoftware only Euclidean Distance time: %d microseconds",
               timeElapsed / (XPAR_CPU_M_AXI_DP_FREQ_HZ / 1000000));

    xil_printf("\n\r");

    xil_printf("Software Euclidean distance squared:\n");

    PrintResultArray(dstData, N/2);

    // Hardware assisted
    RestartPerformanceTimer();

    DistanceSquaredHw(hwDstData, srcData, N);

    timeElapsed = StopAndGetPerformanceTimer();

    xil_printf("\n\rHardware assisted Euclidean Distance time: %d microseconds",
               timeElapsed / (XPAR_CPU_M_AXI_DP_FREQ_HZ / 1000000));

    xil_printf("\n\r");
    xil_printf("Hardware Euclidean distance squared:\n");

    PrintResultArray(hwDstData, N/2);

    xil_printf("\n\rChecking result: %s\n\r",
               CheckEuclideanDistance(dstData, hwDstData, N) ? "OK" : "Error");

    cleanup_platform();
    return 0;
}
