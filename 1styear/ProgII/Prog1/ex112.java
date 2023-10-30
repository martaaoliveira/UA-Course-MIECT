/*
 * ex112.java
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
public class ex112 {
	
	public static void main (String[] args) {
		
 Scanner sc = new Scanner (System.in);
        
        int seg, min, hor;
        
        System.out.print("Insira aqui o numero de segundos: ");
        seg = sc.nextInt();
        System.out.println();
        
        hor = seg/3600;
        min = (seg % 60);
        seg = seg % 60;

        System.out.println(hor + ":" + min + ":" + seg);
    }
    
}
	
		

