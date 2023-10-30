/*
 * ex114.java
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
public class ex114 {
	
	public static void main (String[] args) {
		Scanner ler=new Scanner(System.in);
		double cat1,cat2,hipo,angulo;
		System.out.println("Introduza o valor de um dos catetos");
		cat1=ler.nextDouble();
		System.out.println("Introduza o valor do outro cateto");
		cat2=ler.nextDouble();
		
		hipo=Math.sqrt(Math.pow(cat1,2)+Math.pow(cat2,2));
		System.out.println("O valor da hipotenusa é " + hipo);
		
		angulo=Math.toDegrees(Math.acos(cat1/hipo));
	
		System.out.println("O valor do angulo é " + angulo);
	}
}

