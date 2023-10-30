/*
 * ex405.java
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


public class ex405 {
	
	public static void main (String[] args) {
		int e =0;
		double c;
	
		 
		 for (int a = 1; a <100; a++)
		 {
				for (int b = 1; b < 100; b++)
				{
				if (a<b)
				{
					c=Math.sqrt(Math.pow(a,2)+Math.pow(b,2));
					if(c>=100) break;
					if(c%1==0){
						e = (int)c;
						System.out.println(a + " " + b + " " + e);
						
					}
				
				}	
				}
				
		 }
		 
		
	}
}

