package aula10;
import java.util.*;
public class ex3 {
    public static void main(String[] args){
        System.out.println(posicoes("Hello World"));
        System.out.println(posicoes("Programming in Java"));
        
    }

    public static String posicoes(String frase){
        Map<Character,ArrayList<Integer>> pos = new HashMap<>();

        for(int i=0;i<frase.length();i++){
            char l=frase.charAt(i);
            pos.put(l, new ArrayList<>());
            pos.get(l).add(i);
          
        }
        return pos.toString();
    }
    
}
