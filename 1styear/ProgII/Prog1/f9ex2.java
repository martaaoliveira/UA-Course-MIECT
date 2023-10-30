/*
 * f9ex2.java
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
public class f9ex2 {
	static Scanner ler= new Scanner (System.in);
	public static void main (String[] args) {
		
		String nome,acr;
		
		do
		{
			System.out.println("Nome: ");
			nome=ler.nextLine();
			acr=acronimo(nome);
			System.out.println("Acronimo" + acr);
		} while (nome.length()!=0);
		
		
	}
	
	public static String acronimo(String in){
		String acr="";
		char c;
		for (int i = 0; i < in.length(); i++)
		{
			c=in.charAt(i);
			
				
		if (Character.isUpperCase(c))
		{
			acr=acr+c;
		}
		
		}
		return acr;
	
	}
}

