/*
 * ex205.java
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
public class ex205 {
	
	public static void main (String[] args) {
		Scanner sc=new Scanner(System.in);
		double x1, x2, x3, x4, y1, y2, y3, y4, dist1, dist2;
		
		
		
		System.out.println("Insira as cordenadas do primeiro ponto.");
		System.out.print("X: ");
		x1 = sc.nextDouble();
		System.out.println();
		System.out.print("Y: ");
		y1 = sc.nextDouble();
		System.out.println();
		
		System.out.println("Insira as cordenadas do segundo ponto.");
		System.out.print("X: ");
		x2 = sc.nextDouble();
		System.out.println();
		System.out.print("Y: ");
		y2 = sc.nextDouble();
		System.out.println();
		
		System.out.println("Insira as cordenadas do terceiro ponto.");
		System.out.print("X: ");
		x3 = sc.nextDouble();
		System.out.println();
		System.out.print("Y: ");
		y3 = sc.nextDouble();
		System.out.println();
		
		System.out.println("Insira as cordenadas do quarto ponto.");
		System.out.print("X: ");
		x4 = sc.nextDouble();
		System.out.println();
		System.out.print("Y: ");
		y4 = sc.nextDouble();
		System.out.println();
		
		dist1 = Math.sqrt(Math.pow((x1-x2), 2)+Math.pow((y1-y2),2));
		dist2 = Math.sqrt(Math.pow((x3-x4), 2)+Math.pow((y3-y4), 2));
		
		if(dist1==dist2){
			
			System.out.print("É um quadrado!");
			
		}
		else {
			System.out.println("Não é um quadrado!");
		
	}
	
	}
}

