
import java.util.Scanner;
public class sequencia {
	static Scanner ler=new Scanner (System.in);
	public static void main (String[] args) {
		
	int a []= new int[100];

int dim=leitura(a); //podem nao ser usados as 100 posições
//procura de numero
System.out.print("Qual o número a procurar");
int num=ler.nextInt();
System.out.println("o número "+num+"aparece"+count(num,a,dim)+"vezes");
	}
	public static int leitura (int[]a){
		int i=0,n;
	do
	{
		System.out.printf("Introduza um valor:");
		n=ler.nextInt();
		if (n>0){ //o 0 é ignorado
			a[i]=n;
			i++;
		}
	} 
	while (i<a.length && n>=0);
	return i;
	}
	
	public static int count (int num,int[]a,int dim){
		int c=0;
		for (int j = 0; j< dim; j++)
		{
		if(a[j]==num) c++;
		
		}
		return c;
	}
}

