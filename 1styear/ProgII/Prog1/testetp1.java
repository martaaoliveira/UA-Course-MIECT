
import java.util.Scanner;
public class testetp1 {
	static Scanner ler=new Scanner(System.in);
	public static void main (String[] args) {
		
		
		int[]notas;
		int[]histo;
		double mediaf,mediat;
		System.out.printf("Introduza as notas dos alunos(nota 0-10;11-faltou): %n");
		notas=lerNotas(10);
		//lista notas
		listar(notas);
		
		//calcula histograma
		histo=Histograma(notas);
		//imprime histograma
		ImprimeHistograma(histo);
		//calcular media freq e total
		mediaf= Medias(notas,'f');
		mediat= Medias(notas,'t');
		
		//imprimir medias
		System.out.printf("Media total=%3.1f,Media alunos freq.=%3.1f",mediaf,mediat);
		
	}
	
	//1)Listar notas no ecrã
	
	public static void listar (int[]notas) {
		
		System.out.print("Lista notas:");
		for (int i = 0; i < notas.length; i++)
		{
			System.out.printf("%d ",notas[i]);
			
		}
		
		System.out.println();
	}
	
	// 2) função para ler notas entre 0 e 11. 11 significa que o aluno faltou. Devolve array com as notas
			
		public static int[] lerNotas(int numNotas) {
			
			int notas[]=new int[numNotas];
			int nota;
			for (int i = 0; i <notas.length; i++)
			{
				do
				{
					System.out.print("Int.nota:");
					nota=ler.nextInt();
					if(nota<0 || nota>11 )System.out.println("Nota invalida");
				} while (nota<0 || nota>11 );
				notas[i]=nota;
				
			}
			
			return notas;
			
			}
	
	// 3) função histograma
	public static int[] Histograma(int[] notas) {
		
		int[]histo=new int[12];
	for (int i = 0; i <notas.length; i++)
	{
		for (int j= 0; j < 12; j++)
		{
			if (notas[i]==j) histo[i]++;
	}
}
	return histo;	
}
	
// 4) Função ImprimeHistograma.
// Cada linha tem a nota, e um no de * = à sua frequência
public static void ImprimeHistograma (int[] histo) {
	for (int i = 0; i <histo.length; i++)
	{
		System.out.printf("Nota %2d:",i);
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

