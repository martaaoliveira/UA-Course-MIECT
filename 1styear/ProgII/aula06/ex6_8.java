
import java.util.Scanner;
public class ex6_8 {
	static Scanner ler = new Scanner (System.in);
	public static void main (String[] args) {
		System.out.print("A: ");
		int a = ler.nextInt();
		System.out.print("\nB: ");
		int b = ler.nextInt();
		System.out.println(mdc(a,b));
	}
	
	
	public static int mdc(int a, int b){
		
		if(b==0) return a;
		else{
			return mdc(b, a%b);
		}
		
	}
	
	
	
	
}

