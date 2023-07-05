package aula12;
import java.util.*;


import java.io.*;

public class ex1{
    public static void main(String[]args)throws IOException {

        try{
            //System.out.println("Introduza um ficheiro para ler:");
            //Scanner ficheiro= new Scanner(System.in);
            int nrPalavras=0;// nr palavras 
            FileReader f= new FileReader("C:/Users/Marta Oliveira/OneDrive - Universidade de Aveiro/Desktop/Universidade/POO/poo/src/aula12/teste.txt");
            //ficheiro.close();
            Scanner read= new Scanner(f);
            Map<String, Integer>palavras=new HashMap<>();

            while(read.hasNext() || read.hasNextLine()){
                ++nrPalavras;
                palavras.put(read.next(),null);
    
            }
            
           
            while(read.hasNext()){
            Integer integer = palavras.get(read.next());

            if (integer == null)
                palavras.put(read.next(), 1);
 
            else {
                palavras.put(read.next(), integer + 1);
            }
        
        }
                       
          
            System.out.println("Numero total de palavras: "+ nrPalavras +"\n" );
            System.out.println("Numero de diferentes Palavras "+ palavras.size() +"\n" );


        }
        catch(FileNotFoundException e){
            System.err.println("Ficheiro nÃ£o existente");
        }

    }
    
}