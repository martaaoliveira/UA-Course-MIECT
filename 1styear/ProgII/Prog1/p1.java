

import java.util.Scanner;
public class p1 {
	
	public static void main (String[] args) {
	Scanner ler= new Scanner(System.in);	
		int n;
		
		do {
			System.out.print("Introduza o número de vezes que quer a frase repetida:");
			n=ler.nextInt();
		} while (n<=0);
		
		for (int i = 1; i <= n; i++)
		{
			System.out.println("P1 é fixe");
		}
			
		
	}
}

