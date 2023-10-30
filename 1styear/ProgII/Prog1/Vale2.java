

import java.util.Scanner;
public class Vale2 {
	
	static Scanner ler=new Scanner(System.in);
	
	public static void main (String[] args) {
		
		//variaveis
		int notas[]={0,1,5,9,10,11};
		int opcao;
		int numeronotas;
		int Histo[];
		
		
		//menu
		do
		{
		System.out.println("MENU Clique em *numero* de acordo com o que quer seguir:");
		System.out.println("1)Inserir notas");
		System.out.println("2)Mostrar Notas");
		System.out.println("3)Media");
		System.out.println("4)Qual a maior nota");
		System.out.println("5)Qual a nota mais baixa");
		System.out.println("6)Histograma");
		System.out.println("Clixar x para Sair do programa");
		opcao=ler.nextInt();
		switch (opcao)
		{
			case 1:	
			
			System.out.println("Quantas notas de alunos vão existir?");
			numeronotas=ler.nextInt();
			notas=lerNotas(numeronotas);	
			
			break;
			
			case 2:
		
			imprimirNotas(notas);
			break;
			
			case 3:
			System.out.println("A média das notas é " + medias(notas));
			break;
			
			case 4:
			histograma(Histo);
			break;
			
			//case 5:
			//break;
			
			//case 6:
			//break;
			
			
			
			default:
			System.out.println("Opcao nao valida");
			break;
				
		}
		
		
		
		
		
		} while (opcao!=0);
		

	}
	
//opcao1: inserir as notas
	public static int [] lerNotas(int notastotais)
	{
		int notas[]=new int[notastotais];
	
		
		for (int i = 0; i < notas.length; i++)
		{
			System.out.println("Introduza a nota"+ (i+1) );
			int nota=ler.nextInt();
			notas[i]=nota;
			if (nota<0 || nota>20)
			{
				System.out.println("Nota invalida");
			}
		
		}
		return notas;
		
	}
//Opcao 2:mostrar as notas
	public static void imprimirNotas(int notas[])
	{
		
		for (int i = 0; i < notas.length; i++)
		{
			System.out.printf("Lista de notas:%d %d",(i+1),notas[i]);
		}
		System.out.println();
		
	}
	
//Opcão 3: Media	
	public static double medias(int[]notas)
	{
		double sum=0;
		int count=0;
		double Media;
		for (int i = 0; i < notas.length; i++)
		{
			sum+=notas[i];
			count++;
		}
		
		Media=sum/count;
		return Media;
	}
//Histograma valores
	public static int[] histograma (int[]notas)
	{
		int Histo= int[notas[numeronotas]];
		
		for (int i = 0; i < notas.length; i++)
		
		{
		
			System.out.println("Nota"+i);
			Histo[notas[i]]++;
			

		}
		

		
		return Histo;
	}
//Histograma tabela
	
	
	
	
}

