package aula10;
import java.util.*;
public class ex1 {
    public static void main(String[] args){
        Map<String,String>cores= new HashMap<>();

        cores.put("branco", "que tem a cor de neve");
        cores.put("azul","que tem a cor do ceu");
        cores.put("amarelo","que tem a cor das estrelas");
        cores.put("laranja","que tem a cor da laranja");
        cores.put("vermelho","que tem a cor da maça");


        cores.put("vermelho","que tem a cor de um morango");
        cores.put("amarelo","que tem a cor do Sol");



        System.out.println("A procurar por vermelho...");
        System.out.println("Vermelhor: "+cores.get("vermelho"));
        System.out.println("A procurar por laranja...");
        System.out.println("Laranja: "+ cores.get("laranja"));


        cores.remove("laranja");


        System.out.println("Cores: "+cores.toString());

        System.out.println("Keys: "+cores.keySet());

        System.out.println("Valor das cores "+cores.values());

    }
}
