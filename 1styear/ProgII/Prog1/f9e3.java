
import java.util.Scanner;
public class f9e3 {
	
	static Scanner ler=new Scanner(System.in);
	
	public static void main (String[] args) {
		

		System.out.println("Intoduza uma frase");
		String frase=ler.nextLine();
		
		int n =countWords(frase);
		System.out.println("A frase contem"+n+"palavras");
		
	}
	
	public static int countWords(String fr){
		char c;
		
		int palavras=0;
		
		for (int i = 0; i < fr.length(); i++)
		{
			
			c=in.charAt(i);
			
			if (Character.isSpace(c) || Character.isWhiteSpace)
			{
				
			}
			
			else if (Character.isLetterOrDigit)
			{
				int palavras++;
			}
		}
				
		
		return 
	}
}

