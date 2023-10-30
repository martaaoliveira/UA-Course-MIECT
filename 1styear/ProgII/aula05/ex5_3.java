
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class ex5_3{
	
	final static Scanner ler = new Scanner(System.in);
	public static void main (String[] args){
		String a;
		try{
		File ori = new File(args[0]);
		File cop = new File(args[1]);
		
		if (!ori.isFile() || !ori.canRead())throw new FileNotFoundException();
		
		if(cop.isFile()){
			do
			{
				System.out.println("Ficheiro ja existente, deseja destrui-lo? : s/n ");
				a=ler.next();
				if (a.equals("n"))
				{
					System.out.print("Dê entao um novo nome ao ficheiro");
					String b = ler.next();
					cop = new File(b);
					
				}
				
			} while (!a.equals("s") && !a.equals("n"));
			
		}
		Scanner deFicheiro = new Scanner(ori);
		PrintWriter paraFicheiro = new PrintWriter(cop);
		
		while (deFicheiro.hasNextLine())
		{
	
	
			paraFicheiro.println(deFicheiro.nextLine());
		}
		
	}
	
	//catch(FileNotFoundException e){
		//System.out.println("Ficheiro nao encontrado");
	//}
		
	catch(IndexOutOfBoundsException e ){
		System.out.println("Argumentos passados nao correspondem ao pedido");
	}
	catch(IOException e){
		System.out.println("Ficheiros nao podem ser lidos");
		
	}

		
	}
	
}

