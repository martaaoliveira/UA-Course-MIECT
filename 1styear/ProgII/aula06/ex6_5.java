

import java.util.Scanner;
import java.io.*;

public class ex6_5 {
	
	public static void main (String[] args) {
		
		try
		{
			listDirs(args[0]);
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("Erro, falta de argumentos");
		}
		
		
		
		
		
	}
	
	public static void listDirs(String args){
		File file = null;
		
		try
		{
			file = new File(args);
			if(!file.exists())throw new FileNotFoundException();
		
		}
		catch (FileNotFoundException e)
		{
			System.out.println("O ficheiro nao existe");
			System.exit(1);
		}
		
		File [] files = file.listFiles();
		
		for (int i = 0; i < files.length; i++)
		{
			System.out.println(files[i].getPath());
			if(!files[i].isFile()){
					listDirs(files[i].getPath());
			}
		}
		
		
	}
	
}

