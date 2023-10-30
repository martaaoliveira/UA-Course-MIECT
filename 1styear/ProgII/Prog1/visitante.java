/*
 * visitante.java
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
public class visitante {
	
	public static void main (String[] args) {
	Scanner ler= new Scanner(System.in);	
	int idade;
	System.out.print ("Qual a idade do visitante?:");	
	idade= ler.nextInt();
	
	if(idade<6){
		System.out.print("O utilizador é isento de pagamento");
		
	}
	else if(idade>=6 && idade<13){
		System.out.print("O utilizador paga o valor de Bilhete Criança");
	}
	
	else if(idade>=13 && idade<65){
		
		System.out.print("O utilizador paga o Bilhete normal");
	} 	
	
	else {
	System.out.print("O utilizador paga o Bilhete de terceira idade");
	}
		
	}
}

