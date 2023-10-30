/*
 * f11e1.java
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

public class f11e1 {
	
	static final Scanner sc = new Scanner(System.in);
	
	public static void main (String[] args) {
		
		String[] lista = new String[10]; //array de strings
		
		int n = lerFrases(lista);
		
		System.out.println("Resultado: ");
		for (int k = n-1; k >= 0; k--)
		{
			System.out.println(inverte(lista[k]));
		}
	}
	
	public static int lerFrases(String[]lista) {
		
		String s;
		int n = 0;
		do
		{
			System.out.printf("Introduza a %2d frase: \n", n+1);
			s = sc.nextLine();
			if(s.compareTo("fim") != 0)
			{
				lista[n] = s;
				n++;
			}
		} while (s.compareTo("fim") != 0 && n < lista.length);
		
		return n;
	}
	
	public static String inverte (String in) {
		
		String out = new String();
		char c;
		for (int i = in.length() - 1; i >= 0; i--)
		{
			c = in.charAt(i);
			out += c;
		}
		
		return out;
	}
}


