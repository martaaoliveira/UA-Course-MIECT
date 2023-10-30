

import java.util.Scanner;
public class inversa {
	
	static Scanner ler= new Scanner(System.in);
	
	public static void main (String[] args) {

int N=Functions.getIntPos("Qual a quantidade de elementos?");
//declaração e inicialização do array
//após esta declaração tem sempre a dimensao N
int[]a=new int[N];
//leitura dos valores para o array
leitura(a);
//impressao dos valores
System.out.println(" Lista na ordem inversa");
imprimeInv(a);
}
	
	public static void leitura (int[]a){
		for (int i = 0; i < a.length; i++)
		{
		System.out.print("Valor para a posição" +i);
		a[i]=ler.nextInt();	
		}
		
	}
	
	public static void imprimeInv (int a[]) {
		for (int i = a.length-1; i >= 0; i--)
		{
		System.out.printf("a[%d] contém o valor %d\n", i,a[i]);
		
		}
		
	}

}

