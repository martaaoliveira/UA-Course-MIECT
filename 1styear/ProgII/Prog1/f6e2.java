import java.util.Scanner;


public class f6e2 {
	static Scanner sc = new Scanner (System.in);
	
	public static void main (String[] args) {
		
		int a[] = new int [100]; // declaração e inicialização do array
		
		// leitura dos valores para o array
		
		int dim = leitura(a); // a - in/out; dim - nº de elementos lidos
		
		// Procura de número
		System.out.print("Qual o número a procurar?");
		int num = sc.nextInt();
		System.out.println("O número " + num + " aparece " + count(num,a,dim) + " vezes.");
		
	}
	public static int leitura (int[]a) {
		int i = 0,n;
		do { 
			System.out.printf("Introduza um valor:");
			n= sc.nextInt();
			if (n>0){ // 0 é ignorado
				a[i]=n;
				i++;}
		} while (i<a.length && n>=0);
		return i;
		}
	
	public static int count (int num, int[]a, int dim){
		int c=0;
		for (int i = 0; i < dim; i++)
		{
		if (a[i] ==num) c++;
		}
		return c;
	}
	
}

