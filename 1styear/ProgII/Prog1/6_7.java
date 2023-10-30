/*
 * 6.7.java
 * 
 * Copyright 2019 Marta Alexandra Pinheiro Oliveira <marta.alex@l230214-ws12.ua.pt>
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
public class 6_7 {
	static Scanner ler=new Scanner(System.in);
	public static void main (String[] args) {
		int inf,sup; //limite superior;
		int nels=Functions.getIntPos("N.de valores a introduzir?"); //int numero de elementos
		
		do
		{
			System.out.print("Introduze os limites inferior e superior");
			inf=ler.nextInt();
			sup=ler.nextInt();
		} while (inf>=sup);
		
		int[]A=geraAleat(inf,sup,nels);
		System.out.println("Elementos gerados: "); // para verificação
		for (int i = 0; i < nels; i++)
		{
			System.out.println(A[i]);
		}
		
		
	}
}

