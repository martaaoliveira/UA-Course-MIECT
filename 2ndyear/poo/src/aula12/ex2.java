package aula12;
import java.util.*;
import java.io.*;
public class ex2 {

    public static void main(String[]args)throws IOException{

        File file_reader = new File("C:/Users/Marta Oliveira/OneDrive - Universidade de Aveiro/Desktop/Universidade/POO/poo/src/aula12/movies.txt");
        Scanner read = new Scanner(file_reader);
        read.nextLine();
        SortedSet<Movie> movies= new TreeSet<>();
        while(read.hasNextLine()){
            String linha= read.nextLine();
            String[] split= linha.split("\t");
            movies.add(new Movie(split[0], Float.parseFloat(split[1]), split[2], split[3], Integer.parseInt(split[4])));
        }
    
        System.out.println("\nFilmes por ordem alfabetica");
        for(Movie Filmes:movies){
            System.out.println(Filmes.toString());
        }
        System.out.println("\nFilmes por ordem decrescente de pontuação");
       
        SortedSet<Movie>filmes_decrescentes=new TreeSet<>(Comparator.comparing(Movie::getScore).reversed());
        filmes_decrescentes.addAll(movies);
        
        for(Movie filmes:filmes_decrescentes){
            System.out.println(filmes.toString());
        }

        System.out.println("\nFilmes por ordem crescente de running time");
        SortedSet<Movie>running_time=new TreeSet<>(Comparator.comparing(Movie::running_time));
        running_time.addAll(movies);
        for(Movie filmes:running_time){
            System.out.println(filmes.toString());
        }

       System.out.println("\nGénero de filmes");
        SortedSet<String>genre= new TreeSet<>();
        for(Movie filmes:movies){
            genre.add(filmes.genre());

        }
        System.out.println(genre.toString());

     
        String Dados="";
        for(Movie filmes: movies){
            if(filmes.getScore()>60&&filmes.genre().equals("comedy")){
                Dados+=filmes.toString()+"\n";
            }
        }
        EscreverFicheiro(Dados,"C:/Users/Marta Oliveira/OneDrive - Universidade de Aveiro/Desktop/Universidade/POO/poo/src/aula12/myselection.txt");
        
        

    }

    public static void EscreverFicheiro(String toWrite,String path)throws IOException{
        PrintWriter print= new PrintWriter(new File(path));
        print.println(toWrite);
        print.close();

    }

    
}
