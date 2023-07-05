package aula4;

import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {
    Scanner ler = new Scanner(System.in);
    String str;
    String[] separar;
    do{
        System.out.println("Introduza um texto no minimo com 3 caracters");
        str = ler.nextLine();
        separar = str.split(" ");
    }while(str.length()<3);
   
     
    ler.close();
    String compare=str;
    System.out.println(str);
    System.out.println("Frase em minusculas: " + str.toLowerCase());
    System.out.println("Ultimo caracter da frase: " + str.charAt(str.length()-1));
    System.out.println("Os 3 primeiros caracteres: " + str.substring(0,3));
    System.out.println("Numero de espacos " + (separar.length -1));
    System.out.println("Frase sem espaços: " + compare.replace(" ", ""));
    System.out.println("Numero de caracteres " + (str.length()));
   
    boolean exist=false;
//metodo pata verificar se existem 2 ou + palavras iguais 
for(int i=1;i<separar.length;i++){
    if (separar[i-1].equals(separar[i])){
        exist=true;
        break;
    }
    else if(separar[0].equals(separar[i])){
        exist=true;
        break;
    }
}
if(!exist){
    System.out.println("Nao Existem palavras iguais nesta frase!");
}
if(exist){
    System.out.println("Existem palavras iguais nesta frase!");
}


//////!!///////////!!///!!/////!!////!!
//tentativa de implementar uma função para verificar se a frase estava em ordem alfabetica
//os resultados não estavam a dar bem.

// if (em_ordem(separar,separar.length)==true){
//     System.out.println("A palavra "+str +" esta ordenada" );
// }
// else{
//     System.out.println("A palavra "+str +" nao esta ordenada" );
// }
    
}


// public static boolean em_ordem(String str[],int length){
//     boolean ordem;
//     for(int i=0;i<str.length;i++){
//        for(int k=i+1;k<str.length;k++){
//             if(str[i].compareTo(str[k])>0){
//                 return ordem=false;
//             }
//             if((str[i].charAt(i)>str[i].charAt(k)))
//             {
//                 return ordem=false;
//             }
//        }
//     }
//     return ordem =true;

// }
}
