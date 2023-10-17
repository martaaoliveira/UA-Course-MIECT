package lab07.ex2;
import java.util.*;
public class Main {

    public static void main (String[] args){
    
    System.out.println("TextReader(sentence): ");
    ReaderInterface reader= new TextReader("lab07/ex2/teste.txt");
    System.out.println(reader.next());
    System.out.println("\n");
    System.out.println("Normalization and capitalization: ");
    reader= new NormalizationFilter(new CapitalizationFilter(new TextReader("lab07/ex2/teste.txt")));
    System.out.println(reader.next());
    System.out.println("\n");
    System.out.println("vowel filter: ");
    reader= new VowelFilter(new TextReader("lab07/ex2/teste.txt"));
    System.out.println(reader.next());
    System.out.println("\n");

}
}
