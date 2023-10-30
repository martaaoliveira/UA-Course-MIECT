/*
 * sem título.java
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

import static java.lang.System.out;

import java.util.Scanner;

public class funcoes {
	
	public static void main (String[] args) {
		
// Testar funÃ§Ã£o sqr:
		System.out.printf("sqr(%f) = %f\n", 10.1, sqr(10.1));
		System.out.printf("sqr(%f) = %f\n", -2.0, sqr(-2.0));
	
		// Invoque as funÃ§Ãµes pedidas no enunciado para as testar:
		// Por exemplo, para testar func f (problema 5.2):
		//System.out.printf("f(%d) = %f\n", 5, f(5));

		// Testar as restantes funÃ§Ãµes desenvolvidas
		//System.out.printf("max(%f,%f) = %f\n", 3.1, 5.2, max(3.1, 5.2));
		//System.out.printf("max(%d,%d) = %d\n", 3, 2, max(3, 2));
		//int ano = getIntPos("Ano? ");
		//System.out.printf("ano = %d\n", ano);
	
	}
	
	public static double sqr(double x) {
		double y;	// variavel para resultado
		y = x*x;	// calculo do resultado a partir dos dados
		return y;	// devolver o resultado
	}
	
	// Defina as funÃ§Ãµes pedidas no enunciado:
	public static double divisao(int x){
		return (1/(1+Math.pow(x, 2)));
	}
	public static double max(double x, double y){
		double i;
		if(y>x){ i=y; y=x; x=i;}
		return x;
	}
	public static double max(int x, int y){
		int i;
		if(y>x){ i=y; y=x; x=i;}
		return x;
	}
	public static double poly3(int a, int b, int c, int d){
		double e;
		
		e = Math.pow(a, 3) + Math.pow(b, 2) + Math.pow(c, 1) + Math.pow(d, 0);
		
		return e;
	}
	public static int fact(int n){
		int f=1;
		
		for(int i=n; i>=1;i--){
			f = f*i;
		}
		
		return f;
	}
	public static int getintpos(){
		Scanner c = new Scanner (System.in);
		double a;
		do{
			System.out.println("Escreva um número positivo.");
			a = c.nextDouble();
		}while(a<=0 || Math.floor(a)!=a);

		c.close();
		return (int)a;
	}
	public static int getIntRange(int a, int b){

		Scanner sc = new Scanner (System.in);
		int c;
		if (a<b){
			c=a;
			a=b;
			b=c;
		}
		do{
			System.out.print("\nInsira um número: ");
			c = sc.nextInt();
		}while(c>=a || c<=b);
		sc.close();
		return c;
	}
	public static void printntimes(){
		
		Scanner ab = new Scanner (System.in);
		
		int a;
		System.out.print("Insere o número de vezes: ");
		a = ab.nextInt();
		
		for(int i=0; i<a; i++){
			System.out.print("*");
		}
		
		ab.close();
	}
	public static void retangulo(int a, int b){
		
		int i, c;
		for(i=0; i<a; i++){
			if(i==0 || i == a-1){
				for (c=0; c<b; c++){
					out.print("*");
				}
			}
			else{
				out.print("*");
				for (int d =1; d<b-1; d++){
					out.print(" ");
				}
				out.print("*");
			}
			out.print("\n");			
			
		}
	}
	public static double poly4(double a, double b, double c, double d){
		double e;
		
		e = 7*Math.pow(a, 3) + 3*Math.pow(b, 2) + 5*Math.pow(c, 1) + Math.pow(d, 0);
		
		return e;
	}
}
