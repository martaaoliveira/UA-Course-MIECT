/*
 * ex503.java
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
public class ex503 {
	
	public static void main (String[] args) {
		int mes,ano,dias;
		int i=0;
		boolean bis=false;
		Scanner ler=new Scanner(System.in);
		
		do{
			System.out.print("Insira o Mês: ");
			mes = ler.nextInt();
		}while(mes<1 || mes>12);
		
		do{
			System.out.print("\nInsira o Ano: ");
			ano = ler.nextInt();
		}while (ano<1);
		
		if(((ano%4==0) && (ano%400==0)) || ((ano%4==0) && (ano%100!=0))) bis = true;
		switch (mes){
		
			case 1:i=31; break;
			case 2:
			if (bis==true)
			{
				i=29;
			} 
			else
			{
				i=28;
			}  
			break;
			case 3:i=31; break;
			case 4:i=30; break;
			case 5:i=31; break;
			case 6:i=30; break;
			case 7:i=31; break;
			case 8:i=31; break;
			case 9:i=30; break;
			case 10:i=31; break;
			case 11:i=30; break;
			case 12:i=31; break;
		}
		System.out.print("o Mês tem " + i+ " dias");
		
	}
}

