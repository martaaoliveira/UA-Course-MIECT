package aula10;
import java.util.*;
public class ex2 {

public static void main(String[] args){
    Map<String,ArrayList<String>>cores= new HashMap<>();

    cores.put("branco", new ArrayList<String>(Arrays.asList("que tem a cor de neve")));
    cores.put("azul", new ArrayList<String>(Arrays.asList("que tem a cor do ceu")));
    cores.put("amarelo", new ArrayList<String>(Arrays.asList("que tem a cor das estrelas")));
    cores.put("laranja", new ArrayList<String>(Arrays.asList("que tem a cor da laranja")));
    cores.put("vermelho", new ArrayList<String>(Arrays.asList("que tem a cor da maça")));



   

cores.get("vermelho").add("Que tem a cor de uma morango");
cores.get("amarelo").add("Que tem a cor do Sol");

cores.remove("laranja");



System.out.println("A procurar por vermelho...");
System.out.println("Vermelho: "+cores.get("vermelho"));
System.out.println("A procurar por laranja...");
System.out.println("Laranja: "+ cores.get("laranja"));


cores.remove("laranja");


System.out.println("Cores: "+cores.toString());

System.out.println("Keys: "+cores.keySet());

System.out.println("Valor das cores "+cores.values());

System.out.println("Usando a função aleatorio na cor vermelha calhou: " + aleatorio(cores,"vermelho"));


}
public static int random(int minimo, int maximo){
    int valores = (maximo - minimo) + 1;
    return (int)(Math.random()*valores + minimo);
}

public static String aleatorio(Map<String, ArrayList<String>> cores, String Key) {
    
    return cores.get(Key).get(random(0,cores.get(Key).size()-1));
}
}