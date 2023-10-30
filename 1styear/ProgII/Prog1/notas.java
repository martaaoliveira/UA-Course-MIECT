/*
 * nota final.java
 * 
 * Copyright 2019 Marta Alexandra Pinheiro Oliveira <marta.alex@l230214-ws13.ua.pt>
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
public class notas {
	
	public static void main (String[] args) {
		Scanner kb= new Scanner(System.in);
		double classfinal;//nota final
		double n1,n2,n3;// valor das notas
		
		System.out.print("Introduzir o valor do primeiro teste:");
		n1=kb.nextDouble();
		System.out.print("Introduzir o valor do segundo teste:");
		n2=kb.nextDouble();
		System.out.print("Introduzir o valor do terceiro teste:");
		n3=kb.nextDouble();
		
		classfinal= (int)Math.round(n1*0.2)+(n2*0.3)+(n3*0.5);
		System.out.print("A classificação final é:" + classfinal);
		
		
		
		if(classfinal <10) {
			System.out.println("Aluno reprovado");
			
		}
		
		else{
			System.out.println("Aluno aprovado");
		}
			
	}
}

