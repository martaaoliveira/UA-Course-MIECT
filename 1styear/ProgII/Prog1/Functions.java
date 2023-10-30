/*
 * Functions.java
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
public class Functions {

	public static void main (String args[]) {
		// Testar função sqr:
		System.out.printf("sqr(%f) = %f\n", 10.1, sqr(10.1));
		System.out.printf("sqr(%f) = %f\n", -2.0, sqr(-2.0));

		// Invoque as funções pedidas no enunciado para as testar:
		// Por exemplo, para testar func f (problema 5.2):
		System.out.printf("f(%d) = %f\n", 5, f(5));

		// Testar as restantes funções desenvolvidas
		System.out.printf("max(%f,%f) = %f\n", 3.1, 5.2, max(3.1, 5.2));
		
		//polinomio
Scanner ler= new Scanner(System.in);
System.out.println("Introduza os coeficentes do polinomio e o valor de x");
double a=ler.nextDouble();
double b=ler.nextDouble();
double c=ler.nextDouble();
double d=ler.nextDouble();
double x=ler.nextDouble();
System.out.printf("p(x)=%f\n",poly3(a,b,c,d,x));

// fatorial
 System.out.println("Factorial de : ");
 int n;
 n = ler.nextInt();
 System.out.println("o fatorial desse numero é " + fatorial(n)); 
 
//GetinPos
int ano = getIntPos("Ano? ");
System.out.printf("ano = %d\n", ano);

//getIntRange
n= getIntRange(10,20,"Int,valores no Intervalo: ");

//printNTimes
printNtimes(5,"ola");


 
	}

	public static double sqr(double x) {
		double y;	// variavel para resultado
		y = x*x;	// calculo do resultado a partir dos dados
		return y;	// devolver o resultado
	}
	
	public static double f (int n) {
		double y;
		y= 1/(1+sqr(n));
		return y;
		
	}

	public static double max (double x, double y) {
		double z;
		
		if (x>=y)
		{
			z=x;
		}
		else
		{
			z=y;
		}
		return z;
	}
	public static double poly3 (double a, double b, double c, double d,double x){
		double y;
		y=a*Math.pow(x,3)+b*Math.pow(x,2)+c*x+d;
		return y;
		
	}

public static int fatorial (int n) {
	int fact;
	fact=1;
	 for (int i = 1; i <= n; i++) {
             fact=fact*i;
            }
	
   return fact;
	
}

public static int getIntPos(String msg){
	int y;
	Scanner ler= new Scanner(System.in);
	do {
		System.out.println(msg);
		y=ler.nextInt();
	}
while (y<=0);
return y;
}

public static int getIntRange (int linf,int linsp,String msg){
	int n;
	Scanner ler= new Scanner(System.in);
	do{
		System.out.println(msg);
		n=ler.nextInt();
	}
	while (n<linf || n>linsp);
	return n;
}
public static void printNtimes(int n, String msg){
	for (int i=0;i<n;i++){
		System.out.println(msg);
	}
	
}

}
