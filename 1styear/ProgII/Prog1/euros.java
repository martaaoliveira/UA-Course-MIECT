import java.util.Scanner;
public class euros {
	
	public static void main (String[] args) {
		Scanner kb= new Scanner(System.in);
		System.out.print("Introduza o valor em dolares ");
		double c= kb.nextDouble();
		double f = 0.90*c;
		System.out.print(c + " dolares é equivalente a "+ f +"euros");
		
	}
}
