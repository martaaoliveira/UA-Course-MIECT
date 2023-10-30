/*
 * Vale.java
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

public class Vale{
	static Scanner sc = new Scanner(System.in);

	public static void main(String [] args){
		int opcao;
		int [] notas = {0, 1, 2, 3, 1, 2, 1, 2, 4, 5, 6, 7, 8, 9,0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,20};
		int numero,min=0,max=20;
		do{
			System.out.println("\n\n1) Inserir Notas");
			System.out.println("2) Mostrar Notas");
			System.out.println("3) Media");
			System.out.println("4) Maior nota");
			System.out.println("5) Menor nota");
			System.out.println("6) Imprimir Histograma");
			System.out.println("7) Imprimir Histograma Star");
			System.out.println("0) Sair");
			System.out.print("opcao ->");
			opcao = sc.nextInt();
			switch(opcao){
	
				case 1:
					System.out.println("Introduza o numero de notas:");
					numero = sc.nextInt();
					System.out.println("Introduza o numero minimo:");
					min = sc.nextInt();
					System.out.println("Introduza o numero maximo:");
					max = sc.nextInt();
					notas = introduzNotas(numero,min,max);
				break;
				case 2:
					printArr(notas);
				break;
				case 3:
					System.out.println("\nmedia = " +media(notas));
				break;
				case 4:
					System.out.println("maior = " +maior(notas));
				break;
				case 5:
					System.out.println("menor = " +menor(notas));
				break;
				case 6:
					printHisto(notas,min,max);
				break;
				case 7:
					printHistoStar(notas,min,max);
				break;
				default:
					System.out.println("Seu burro as opcoes vao ate 5");
				break;
			}
		}while(opcao!=0);	
	}

	//função para introduzir notas entre [min,max]
	public static int []  introduzNotas(int size, int min,int max){
		int [] arr = new int[size];
		int aux, i=0;
		System.out.println("Introduza numeros:");
		do{
			aux = sc.nextInt();
			if(aux >= min && aux <= max){
				arr[i] = aux;
			i++;
			}
		}while(i<size);
		return arr;
	}

	//imprime conteudo do array
	public static void printArr(int [] arr){
		for (int i = 0;i< arr.length ;i++ ) {
			System.out.print(arr[i]+ " ");
		}
	}
	//calcula a media das notas do array
	public static double media(int [] arr){
		double media = 0;
		for (int i = 0;i<arr.length;i++ ) {
			media += arr[i]; //somar tudo
		}
		media /= arr.length; // dividir soma pelo N
		return media;
	}
	//saber o maior numero do array
	public static int maior(int [] arr){
		int maior = arr[0];
		for (int i = 1;i<arr.length ;i++ ) {
			if(arr[i]> maior){
				maior = arr[i];
			}
		}
		return maior;
	}

	//saber o menor numero do array		
	public static int menor(int [] arr){
		int menor = arr[0];
		for (int i = 1;i<arr.length ;i++ ) {
			if(arr[i]< menor){
				menor = arr[i];
			}
		}
		return menor;
	}

	//print histograma
	public static void printHisto(int [] arr,int min,int max){
		int []histo = new int [max-min+1];
		for (int i = 0;i<arr.length ;i++ ) {
			histo[arr[i]-min]++;
		}
		for (int i = 0;i<histo.length ;i++ ) {
			System.out.printf("%d \t| %d\n", i+min, histo[i]);
		}
	}

	//print histograma
	public static void printHistoStar(int [] arr,int min,int max){
		for (int i = min;i<max ;i++ ) {
			System.out.print(i+"\t");
			for (int j = 0;j<arr.length ;j++ ) {
				if(i == arr[j])
					System.out.print("*");
			}
			System.out.println();
		}
	}

}
