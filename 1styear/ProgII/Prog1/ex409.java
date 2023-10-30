/*
 * ex409.java
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
public class ex409 {
	
	public static void main (String[] args) {
		Scanner ler=new Scanner(System.in);
		int n;
		int soma=0;
		int b=0;
	
		do
		{
		System.out.println("Introduza um numero inteiro positivo menor que 1000");
		n=ler.nextInt();
		} while (n<=0 || n>=1000);
		
		for (int i = 1; i <= n; i++)
		{
			b=2*i;
			soma += b;
			System.out.println(b);
		}
		
			System.out.println(" A soma dos números é " + soma );
	}
}

