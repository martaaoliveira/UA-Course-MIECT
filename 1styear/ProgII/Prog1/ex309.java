/*
 * ex309.java
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
public class ex309 {
	
	public static void main (String[] args) {
		Scanner ler=new Scanner(System.in);
		double a;
		int pos=0;
		int neg=0;
		int pos100=0;
		int neg100=0;
		
		do
		{
			System.out.println("Introduza um valor");
			a=ler.nextDouble();
			if (a==0) break;
			if(a>0) pos++ ;
			if(a<0) neg++;
			if(a>=100 && a<=1000) pos100++;
			if(a>-1000 && a<=-100) neg100++;
			
			
		} while (a!=0);
		
		System.out.println("Os numeros positivos no total foram " + pos + " Os numeros negativos no total foram " + neg + "Entre 100 e 1000 foram " + pos100 + " Os entre -1000 e -100 " + neg100);
		
	}
}

