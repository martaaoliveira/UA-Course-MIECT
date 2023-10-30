import static java.lang.System.*;
import java.io.*;
import java.util.Scanner;

public class SortInts
{
  public static void main(String[] args) throws IOException {
	  
	  
	SortedListInt values = new SortedListInt();
    
    for(int i = 0; i < args.length; i++) {

        File file = new File[args[i]];
        if (!file.exists()) {
            System.out.println("O ficheiro " + args[i] + " nao existe!");
        }
        else {
            Scanner scf = new Scanner(file);
            while(scf.hasNext()) {
                try {
                    String x = scf.next();
                    int v = Integer.parseInt(x);
                    values.insert(v);
                   assert values.isSorted();
                   values.print();
			   
                }
                catch (NumberFormatException e) {
                    ;
                }
            }
  }

}
}
}


