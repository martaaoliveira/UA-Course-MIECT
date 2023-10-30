/*
 * ex402.java
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
public class ex402 {
	
	public static void main (String[] args) {
	Scanner ler=new Scanner(System.in);
	int n;
	System.out.println("Indique um valor entre 0 e 100");
	n=ler.nextInt();
	do
	{
		System.out.println("Indique um valor entre 0 e 100");
	} while (n<0 || n>100);
		System.out.println("-------------------");
		System.out.println("| Tabuada dos " + n + " |");
		System.out.println("|  " + n + " x 1 |  " + n*1 +"   |");
		System.out.println("|  " + n + " x 2 |  " + n*2 +"  |");
		System.out.println("|  " + n + " x 3 |  " + n*3 +"  |");
		System.out.println("|  " + n + " x 4 |  " + n*4 +"  |");
		System.out.println("|  " + n + " x 5 |  " + n*5 +"  |");
		System.out.println("|  " + n + " x 6 |  " + n*6 +"  |");
		System.out.println("|  " + n + " x 7 |  " + n*7 +"  |");
		System.out.println("|  " + n + " x 8 |  " + n*8 +"  |");
		System.out.println("|  " + n + " x 9 |  " + n*9 +"  |");
		System.out.println("|  " + n + " x10 |  " + n*10 +"  |");
		System.out.println("-------------------");
	
	
	
	}
}

