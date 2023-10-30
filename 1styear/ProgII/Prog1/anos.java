/*
 * anos.java
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
public class anos {
	
	public static void main (String[] args) {
	int mes;
	int ano;
	int dia;
	Scanner ler= new Scanner (System.in);
	System.out.print("Qual o mês em que se encontra ( 1 a 12):");
	mes= ler.nextInt();
	System.out.print("Qual o ano em que se encontra:");
	ano= ler.nextInt();
	
	
	switch(mes) {
	case 1: case 3: case 5: case 7: case 8: case 10: case 12:	
		dia=31;
		break;
	case 4: case 6: case 9: case 11:
		dia=30;
		break;	
	default:	
	
	if (ano%4==0 &&(ano%100!=0 || ano%400==0)){
		
		dia=29;
		
	}
	
	
	else {
		
		dia=28;
		
	}
	
	}
	System.out.printf("o mês %2d do ano %4d tem %2d dias: " ,mes,ano,dia);
	}
}

