/*
 * ex210.java
 * 
 * Copyright 2019 Marta Oliveira <marta@pc>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */

import java.util.Scanner;
public class ex210 {
	
	public static void main (String[] args) {
		Scanner ler=new Scanner(System.in);
		int mes,dia,ano,ano2,mes2,dia2;
		
		System.out.println("Diga o mês");
		mes=ler.nextInt();
		System.out.println("Diga o ano");
		ano=ler.nextInt
		();
		System.out.println("Diga o dia");
		dia=ler.nextInt();

		switch (mes)
		{
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
			dia=31;
	do
	{
		System.out.println("Data não existente");
	dia=0;
	mes=0;
	ano=0;
	} 
	while(dia>31);
	
	
	if (dia==31)
	{
		dia=1;
		mes=2;
		if (mes==12)
		{
			ano=ano+1;
			
		}
		
		else
		{
			ano=ano;
			
			
		}
	}
			break;
			case 2:
			do
			{
				System.out.println(" Data não existente");
				mes=0;
				dia=0;
				ano=0;
			} while (dia>30);
			
				if (ano%4==0 && (ano%100!=0 || ano%400!=0) && dia==29)
				{
					dia=0;
					ano=ano;
					mes=mes;
				}
			if ( ano%4==0 && (ano%100!=0 || ano%400!=0) && dia==28){
				dia=29;
				ano=ano;
				mes=mes;
			}
			
			else
			{
				dia=28;
				if (dia==28)
				{
					dia=1;
					ano=ano;
					mes=mes;
				}
			}
			
			
			
			
			
			
			break;
				
				case 4:
				case 6:
				case 9:
				case 11:
				dia=30;
				if (dia==30)
				{
				dia=dia+1;
				ano=ano;
				mes=mes;
				}
	
				break;
			default:
			

		}
		
			System.out.println(" a data seguinte é composta por"+ "O mes " + mes + " do ano " + ano + " tem " + dia + " dias.");

		
	}
			
}

