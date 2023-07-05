package aula11;
import java.io.*;
import java.util.*;

public class ex2 {
    public  static void main(String[]args)throws IOException{
       
        ArrayList<voo> voos= new ArrayList<>();
        carregarDados(voos);
        System.out.println(print(voos));
        
        criarFicheiro(print(voos),"C:/Users/Marta Oliveira/OneDrive - Universidade de Aveiro/Desktop/Universidade/POO/poo/src/aula11/infopublico.txt");
        criarFicheiro(Chegadas(voos), "C:/Users/Marta Oliveira/OneDrive - Universidade de Aveiro/Desktop/Universidade/POO/poo/src/aula11/cidades.txt");
        
        System.out.println(Chegadas(voos));
        System.out.println(ListaAtrasos(voos));
    }
    

    public static void carregarDados(ArrayList<voo> voos) throws IOException {
        try{
            
            File f= new File("C:/Users/Marta Oliveira/OneDrive - Universidade de Aveiro/Desktop/Universidade/POO/poo/src/aula11/voos.txt");
            Scanner input= new Scanner(f);
            input.nextLine();
            while(input.hasNext()){
                String[]voo=input.nextLine().split("\t");
                if(voo.length==3){
                    voos.add(new voo(voo[0],voo[1],voo[2],""));
                }
                else{
                    voos.add(new voo(voo[0],voo[1],voo[2],voo[3]));
                }
            }
            
        }
            
        catch(IOException e){
            System.err.println("Erro na leitura do ficheiro");
        }
    }

    public static String print(ArrayList<voo>voos){
        String head = String.format("%-7s %-8s %-20s %-21s %-7s %-20s\n", "Hora", "Voo", "Companhia", "Origem", "Atraso", "Obs");
        String print="";
        for(voo voo: voos){
            print+=voo.toString() +"\n";
        }
        return head + print;
    }
    
    public static void criarFicheiro(String toWrite,String path)throws IOException{
        PrintWriter out= new PrintWriter(new File(path));
        out.print(toWrite);
        out.close();
    }

    public static int media(ArrayList<Integer>atrasos){
        int soma=0;
        for (int i : atrasos) soma += i; 
        return soma / atrasos.size();
        
    }

    public static String ListaAtrasos(ArrayList<voo> voos){
        Map<String,ArrayList<Integer>>atrasos= new HashMap<>();
        for (voo voo : voos) {
            if (!voo.getAtraso().equals("")) {
                atrasos.putIfAbsent(voo.getCompanhia(), new ArrayList<>());
                atrasos.get(voo.getCompanhia()).add(voo.setAtrasoSegundos());
            }
        }
        Map<String,String> atrasoMedio= new HashMap<>();
        for (String companhia : atrasos.keySet()) {
            int media_sec = media(atrasos.get(companhia));
            String atraso = String.format("%02d:%02d", media_sec/3600, (media_sec-((media_sec/3600)*3600))/60);
            atrasoMedio.put(companhia, atraso);
        }
        LinkedHashMap<String,String> atrasos_ordenados=new LinkedHashMap<>();
        atrasoMedio.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(x -> atrasos_ordenados.put(x.getKey(), x.getValue()));
        
        String head = String.format("%-20s %-7s\n", "Companhia", "Atraso Médio");
        String body = "";
        for (String companhia : atrasos_ordenados.keySet()) {
            body += String.format("%-20s %-7s\n", companhia, atrasos_ordenados.get(companhia));
        }
        return head + body;
    
    }

    public static String Chegadas(ArrayList<voo>voos){
        HashMap<String, Integer> chegadas = new HashMap<>();
        for (voo voo : voos) {
            chegadas.putIfAbsent(voo.getOrigem(), 0);
            chegadas.put(voo.getOrigem(), chegadas.get(voo.getOrigem()) + 1);
        }
        Map<String, Integer> chegadas_ordenadas = new LinkedHashMap<String, Integer>();
        chegadas.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> chegadas_ordenadas.put(x.getKey(), x.getValue()));
        String head = String.format("%-20s %-5s\n", "Origem", "Voos");
        String body = "";
        for (String cidade : chegadas_ordenadas.keySet()) {
            body += String.format("%-20s %-5s\n", cidade, chegadas_ordenadas.get(cidade));
        }
        return head + body;
    }
}

