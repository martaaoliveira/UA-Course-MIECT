
import java.util.Scanner;
public class temperatura {
	
	public static void main (String[] args) {
		Scanner kb= new Scanner(System.in);
		System.out.print("Introduza a tempertaura em Celsius ");
		double c= kb.nextDouble();
		double f = 1.8*c+32;
		System.out.print(c + "C é equivalente a "+ f +"F");
		
		
		
		
		
	}
}

