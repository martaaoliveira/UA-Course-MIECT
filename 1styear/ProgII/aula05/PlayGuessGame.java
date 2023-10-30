import static java.lang.System.*;
import java.util.Scanner;

public class PlayGuessGame {
  private static final Scanner sc = new Scanner(System.in);

  public static void help(GuessGame game) {
    assert game != null;
    out.printf("Find the secret number in range [%d,%d]!\n",
                game.min(), game.max());
    out.printf("Available commands:\n");
    out.printf("  <number>   (new attempt to guess secret)\n");
    out.printf("  count      (show current number of attempts)\n");
    //out.printf("  show       (show all attempts done)\n");
    out.printf("  help       (information on command usage)\n");
    out.printf("  quit       (exits the program)\n");
  }

  public static void main(String[] args) {
	  
    if (args.length != 0 && args.length != 2) {
      out.println("Usage: PlayGuessGame [ <min> <max> ]");
      out.println("(by default range [0,20] is used)");
      exit(1);
    }
    
    int min,max;
    int tentativas=0;
    GuessGame a;
 
    try {
      min = Integer.parseInt(args[0]);
      max = Integer.parseInt(args[1]);
      System.out.println("Valores maximos e minimos atualizados");
      a= new GuessGame(min,max);
    }
    
  catch(NumberFormatException e){
	  
	  min=0;
	  max=20;
	  System.out.println("Valor minimo 0 valor maximo 20");
	 a= new GuessGame(min,max);
  }
  
   catch(ArrayIndexOutOfBoundsException e){
	  
	  min=0;
	  max=20;
	  System.out.println("Valor minimo 0 valor maximo 20");
	  a= new GuessGame(min,max);
  }
  
  
  
  

			out.print("\nQual é o número que acha estar certo?");
			
			try{
				tentativas = sc.nextInt();
				
			}
			
			catch(NumberFormatException e){
				err.println("Número inválido!");
				exit(1);
			}
			
			
			a.play(tentativas);
			if(a.attemptIsHigher()){
				out.print("\nTente mais baixo!");
			}
			else if(a.attemptIsLower()){
				out.print("\nTente mais alto!");
			}
			else{
				out.print("\nParabéns! Acertou!");
			}
		while(!a.finished());
		
		out.print("\nUsou "+a.numAttempts()+" tentativas.");
	
  
  
  
  
  

    out.println();
    help(a);
    out.println();
    while (!a.finished()) {
      out.print("Command? ");
      String command = sc.next();
      out.println();
      switch (command) {
      case "quit":
        exit(1);
        break;
      case "help":
        help(a);
        break;
      case "count":
        //...
        break;
      default:
        //...
        break;
      }
      out.println();
    }
    out.printf("Game finished in %d attempts\n", a.numAttempts());
  }
}

