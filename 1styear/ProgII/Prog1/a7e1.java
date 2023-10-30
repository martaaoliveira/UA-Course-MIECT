/*
 * a7e1.java
 * 
 * Copyright 2019 Marta Alexandra Pinheiro Oliveira <marta.alex@l230214-ws12.ua.pt>
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
import java.io.*;
public class a7e1 {
	static Scanner ler=new Scanner(System.in);
	public static void main (String[] args)throws IOException {
		
		boolean valid_file;
		File fin;
		String nameIn;
		
		
		do
		{
			System.out.println("Qual o ficheiro que pretende ler?");
			nameIn=ler.nextLine();
			fin=new File(nameIn);
			valid_file = true;
			
			if (fin.isFile()==false)
			{
				System.out.printf("%n não é um ficheiro!!",nameIn);
				valid_file=false;
			}
			else if (fin.canRead()==false)
			{
				System.out.printf("%n não é um ficheiro!!",nameIn);
				valid_file=false;
			}
		} while (valid_file== false);
		
		System.out.print("Ficheiro válido!");
		
		Scanner lerf=new Scanner(fin);
		String[]t=new String[20];
		
		int i=0;
		
		do
		{
			t[i]=lerf.nextLine();
			i++;
		} while (i<t.length && lerf.hasNextLine()); //enquanto houver linha vai lendo
		
		System.out.println("Conteudo do ficheiro:");
		
		
		for (int j = 0; j < i; j++)
		{
			System.out.println(t[j]);
		}
		
		
		System.out.println("Ficheiro de Saída");
		String nameOut=ler.nextLine();
		File fout=new File(nameOut);
		
		PrintWriter pw = new PrintWriter(fout);
		
		for (int j = 0; j < i; j++)
		{
			pw.println(t[j]);
		}
		pw.close();
		
	}
}
