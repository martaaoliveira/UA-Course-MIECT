/*
 * ex201.java
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
public class ex201 {
	
	public static void main (String[] args) {
		double teste1,teste2,teste3,notafinal;
		Scanner ler= new Scanner(System.in);
		System.out.println(" Qual o valor do primeiro teste?");
		teste1=ler.nextDouble();
		System.out.println(" Qual o valor do segundo teste?");
		teste2=ler.nextDouble();
		System.out.println(" Qual o valor do terceiro teste?");
		teste3=ler.nextDouble();
		
		notafinal= teste1*0.20 +teste2*0.30 + teste3*0.50;
		
		if(notafinal>=10)
		{
			System.out.println("Aluno Aprovado");
		}
		else {
			System.out.println("Aluno Reprovado");
		}
	}
}

