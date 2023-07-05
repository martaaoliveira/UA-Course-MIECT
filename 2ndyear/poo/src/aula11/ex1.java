package aula11;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ex1 {

    public static void main(String []args)throws IOException
    {
        File f= new File("C:/Users/Marta Oliveira/OneDrive - Universidade de Aveiro/Desktop/Universidade/POO/poo/src/aula11/major.txt");
        Scanner input= new Scanner(f);
        
        Map<String,Map<String,Integer>> words= new TreeMap<>();


        ArrayList<String>lista_palavras=new ArrayList<>();
        
        String palavras="";
        while(input.hasNext()){
            palavras=palavras+ " "+input.next();
        }
        for(String palavra : palavras.toLowerCase().replaceAll("[\\t\\n\\.\\,\\:\\'\\‘\\’\\;\\?\\!\\-\\*\\{\\}\\=\\+\\&\\/\\\\(\\)\\[\\]\\”\\“\\\"\\']", " ").split("\\s+")){

            if(palavra.length()>2){
                lista_palavras.add(palavra);
            }
        }

        for(int i = 0; i<lista_palavras.size()-1; i++){
            Map<String, Integer> mapa2 = new TreeMap<>();
            words.put(lista_palavras.get(i), mapa2);
        }

// cada (par qual)  ficheiro (texto numero)
        for(int i = 0; i<lista_palavras.size()-1; i++){
            String word1 = lista_palavras.get(i);
            String word2 = lista_palavras.get(i+1);
            words.get(word1).put(word2, words.get(word1).getOrDefault(word2, 0) + 1);
        }
        System.out.println(words.toString());


        System.out.println(words.toString());
        input.close();
    }

   
 
}
