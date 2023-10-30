
import java.util.Scanner;
public class testetp1_1_2 {
	static Scanner ler=new Scanner(System.in);
	
	public static void main (String[] args) 
{
		int notas[];
		System.out.println("Introduza as notas dos alunos (notas0-10;11-faltou)");
		notas=lerNotas(10);
		listarnoecra(notas);
		//Histograma
		int[]histograma;
		histograma=histo(notas);
		listarhistograma(histograma);
		//medias
		double mediaf,mediat;
		mediaf= Medias(notas,'f');
		mediat= Medias(notas,'t');
		System.out.printf("Media total = %3.1f, Media alunos freq. = %3.1f",mediat,mediaf);
}

public static int[] lerNotas(int numerodenotas)
{
	int notas[]=new int[numerodenotas];
	for (int i = 0; i <notas.length; i++)
	{
		System.out.printf("Nota aluno %3d: ",i+1,notas[i]);
		int nota=ler.nextInt();
		notas[i]=nota;
		if (nota<0 || nota>11)
		{
			System.out.println("Nota inválida");
		}
		
		
	}
	
	return notas;
	
}

public static void listarnoecra(int[]notas)
{
	System.out.println("Lista Notas: ");
	for (int i = 0; i < notas.length; i++)
	{
		System.out.printf("%2d", notas[i]);
	}
	System.out.println();
	
}

public static int[] histo(int[]notas)
{
	int histograma[]=new int[12];
	
	for (int i = 0; i <notas.length; i++)
	{
		
			histograma[notas[i]]++;
		
	}
	return histograma;
}

public static void listarhistograma(int[]histograma)
{

	for (int i = 0; i < histograma.length; i++)
	{
		System.out.printf("|Nota %2d",i);
	
		for (int j = 0; j < histograma[i]; j++)
		{
			System.out.print("*");
			for (int k = 0; i < histograma[i]; k++)
			{
				
			}
			
			
		
		}
		
	System.out.println("");
	System.out.println("-"*j);
	
	}
	
	
	
}

public static double Medias(int[]notas, char type)
{
	double sum=0;
	double count=0;
	switch (type)
	{
		case 'f':
			for (int i = 0; i < notas.length; i++)
			{
				if (notas[i]==1)
				{
					continue;
				}
			sum+=notas[i];
			count++;
			}
		
			break;
			
		case't':
		for (int i = 0; i < notas.length; i++)
		{
			 if(notas[i] == 11)
			 {
				 count++;
				//a soma é 0;
				 continue;
				
			}
		sum += notas[i]; 
		count++;	
		}
		
		break;
			
			
		default:
		System.out.println("caso inexistente");
		System.exit(1);
		break;
			
	}
	

return sum/count;	
}



}

