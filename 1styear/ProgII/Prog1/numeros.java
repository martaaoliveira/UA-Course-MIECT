/*
 * numeros.java
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
public class numeros {
	
	public static void main (String[] args) {
		Scanner ler= new Scanner(System.in);
		double n1,n2;
		
		System.out.print(" Introduza um valor:");
		n1= ler.nextDouble();
		System.out.print("Introduza outro valor:");
		n2= ler.nextDouble();
		
		if(n1>n2){
			System.out.print(n1 + " :este valor é maior que o outro dado");
			
			
		}
		
		else if (n1<n2) {
			System.out.print(n2 + " :este valor é maior que o outro introduzido");
		}
		
		else {
			System.out.print( " os números sao iguais");
			
			
		}
		
	}
}

