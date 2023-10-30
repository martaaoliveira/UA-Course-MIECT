/*
 * ex113.java
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
public class ex113 {
	
	public static void main (String[] args) {
		Scanner ler=new Scanner(System.in);
		double x1,x2,y1,y2,distancia;
		//Coordenadas da localidade
		System.out.println("Insira as coordenadas x1");
		x1=ler.nextDouble();
		System.out.println("Insira as coordenadas y1");
		y1=ler.nextDouble();
		System.out.println("Insira as coordenadas x2");
		x2=ler.nextDouble();
		System.out.println("Insira as coordendas y2");
        y2=ler.nextDouble();
        //calculo distancia
        distancia= Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
        System.out.println("A distância entre as 2 localidades é" + distancia );
	}
}

