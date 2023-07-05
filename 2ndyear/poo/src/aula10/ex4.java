package aula10;
import java.util.*;
import java.io.FileReader;
import java.io.IOException;

public class ex4 {
    public static void main(String[]args) throws IOException {
        List<String> palavras = new ArrayList<>(); 
        Scanner input = new Scanner (new FileReader("C:/Users/Marta Oliveira/OneDrive - Universidade de Aveiro/Desktop/Universidade/POO/poo/src/aula10/words.txt"));
    
        while(input.hasNext()){
            String word=input.next();
            if(word.length()>2){
            palavras.add(word);
            }
            System.out.println(word + " ");
        }

        System.out.print("\nPalavras Acabam com 's': ");
        for(String frase : palavras){
            if(frase.endsWith("s")){
                System.out.print(frase + " ");
            }
        }

        System.out.print("\n\nEliminar todos os caracteres que nao sejam letras ");
        Iterator<String> iterador = palavras.iterator();
        while(iterador.hasNext()){
            if(!iterador.next().matches("[a-zA-Z]+")){
                iterador.remove();
            }
        }
        System.out.println(palavras);


    
    }
}
