/*
 * ex406.java
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
public class ex406 {
	
	public static void main (String[] args) {
		int largura,altura;
		Scanner ler=new Scanner(System.in);
		System.out.println("Qual a altura que quer que tenha o seu retângulo?");
		largura=ler.nextInt();
		System.out.println("E qual a largura?");
		altura=ler.nextInt();

		for (int i = 0; i < altura; i++)
		{
			for (int j =0; j < largura; j++)
			{
				System.out.print("*");
			}
			System.out.println(" ");
		}
		
		
	}
}

