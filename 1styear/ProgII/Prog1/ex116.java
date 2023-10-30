/*
 * ex115.java
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

public class ex116 {
	
	public static void main (String[] args) {
	Scanner ler=new Scanner(System.in);
	double desp1,desp2,desp3,desp4,media;
	System.out.println("Quanto gastou o turista no primeiro dia ?");
	desp1=ler.nextDouble();
	desp2= desp1 + desp1*0.20;
	desp3= desp2 + desp2*0.20;
	desp4= desp3 +desp3*0.20;
	media=((desp1+desp2+desp3+desp4)/4);
	System.out.println(media);
	}
}

