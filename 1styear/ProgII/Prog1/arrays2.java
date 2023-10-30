/*
 * arrays2.java
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

public class arrays2 {

public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    int a[] = {5, 10, 15, 20, 25};

    int b[] = new int[a.length];

    int d = 0;

    for(int i = 1; i < a.length; i++) {

      b[i] = b[i-1] + a[i];

    }

    for(int i = 0; i < b.length; i++) {

      d = d + b[i];

    }

    for(int i = 0; i < b.length; i++){

      System.out.printf("%d,", b[i]);

    }

    System.out.printf("\n");

    System.out.printf("%d", d);

  }

} 

