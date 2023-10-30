/*
 * ex211.java
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
public class ex211 {
	
	public static void main (String[] args) {
		Scanner ler= new Scanner (System.in);
		double a,b,c,delta,x1,x2;
		System.out.println("Introduza um valor para o coeficiente A");
		a=ler.nextDouble();
		System.out.println("Introduza um valor para o coeficiente B");
		b=ler.nextDouble();
		System.out.println("Introduza um valor para o coeficiente C");
		c=ler.nextDouble();
		
		delta=Math.pow(b,2)-(4*a*c);
		if (delta<0)
		{
			System.out.println("O sistema é impossível");
		}
		else if (delta==0)
		{
			x1= -b /2;
			System.out.println("A solução é" + x1);
			
		}
		else
		{
			x1= ((-b + delta))/(2*a);
			x2= ((-b - delta)/2*a);
			System.out.println("As soluções são" + x1 + x2);
		}
	}
}

