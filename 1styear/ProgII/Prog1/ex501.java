/*
 * ex501.java
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


public class ex501 {
	
	public static void main (String[] args) {
		int a;
		
		System.out.println(funcoes.sqr(2.0));
		System.out.println(funcoes.divisao(2));
		System.out.println(funcoes.max(2, 3));
		System.out.println(funcoes.poly3(1, 1, 1, 1));
		System.out.println(funcoes.fact(3));
		a = funcoes.getintpos();
		funcoes.getIntRange(5,0);
		System.out.println(funcoes.max(3, 2));
		System.out.println(a);
		funcoes.printntimes();
		
		
	}
}

