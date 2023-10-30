/*
 * tp1.java
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
public class tp1 {
	static Scanner ler=new Scanner(System.in);
	public static void main (String[] args) {
		int[] notas = {4, 6, 7, 5, 4, 6, 6, 8, 11};
		int[] histo;
		double mediaf,mediat;
		System.out.printf("Introduza notas dos alunos(nota 0-10; 11 faltou): %n");
		notas = LerNotas(10);
		// lista notas
		listar(notas);
		// calcula histograma
		histo = Histograma(notas);
		// imprime histograma
		ImprimeHistograma(histo);
		// calcular média freq e total
		mediaf= Medias(notas,'f');
		mediat= Medias(notas,'t');
		// 6) Imprime medias
		System.out.printf("Media total=%3.1f,Media alunos freq.=%3.1f",mediaf,mediat);
	}
	
	public static void listar(int[] notas) {
		System.out.println("notas aluno");
		for (int i = 0; i <notas.length; i++)
		{
			System.out.printf("%d ",notas[i]);
		}
		System.out.println();
		
}
	// 2) função para ler notas entre 0 e 11. 11 significa que o aluno faltou. Devolve array com as notas
	public static int[] LerNotas(int numNotas) {
		int[]notas=new int[numNotas];
		int nota;
		for (int i = 0; i < notas.length; i++)
		{
		
		do
		{
			System.out.println("Introduza nota");
			nota=ler.nextInt();
			if(nota<0 || nota>11)System.out.println("Nota Inválida");
		} while (nota<0 || nota>11);
		notas[i]=nota;
		
	}
		return notas;
												}
// 3) função histograma
	public static int[] Histograma(int[] notas) {
		int[]histo=new int[12];
		for (int i = 0; i <notas.length; i++)
		{
			
			for (int j = 0; j < 12; j++)
			{
				if(notas[i]==j)histo[j]++;
			}
			
		}
		return histo;
												}	
// 4) Função ImprimeHistograma.
// Cada linha tem a nota, e um no de * = à sua frequência
public static void ImprimeHistograma (int[] histo) {
	for (int i = 0; i <histo.length; i++)
	{
		System.out.print("Nota"+i);
			for (int j = 0; j < histo[i]; j++)
			{
				System.out.print("*");
			}
			System.out.println();
	}
	
	
												  }	
// 5) função medias de freq ou total conforme tipo = 'f' ou 't'
public static double Medias(int[] lista,char tipo) {
	int sum=0;
	int num=0;	
	for (int i = 0; i <lista.length; i++)
	{
		if (lista[i]!=11)
		{
			sum+=lista[i];
			num++;
		}
	}
	if (tipo == 't') num = lista.length;
	return(double)sum/num;
	
	
}												  
												  
												  										
}

