/*
 * l.java
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


public class l {
	
	public static void main (String[] args) {
		
static final int qntddLinhas = 10; 
static final int qntddColunas = 10; 

for (int i = 0; i < qntddLinhas; i++){ // para cada linha
   for (int j = 0; j < i; j++){
      System.out.print(" ");  // imprime espaços
   }
   for (int j = i; j < qntddColunas ; j++){
      System.out.print("*");  // imprime asterisco
  }
  System.out.println(""); // pula linha 
}


		
	}
}

