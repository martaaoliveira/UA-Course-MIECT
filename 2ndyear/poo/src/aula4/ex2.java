package aula4;
import java.util.*;

public class ex2 {
    public static void main(String [] args){
        Scanner ler = new Scanner(System.in);
        System.out.println("Introduza uma frase: ");
        String frase=ler.nextLine();
        ler.close();
        System.out.println("A frase tem " + nrDigits(frase) + " digitos.");
        System.out.println("A frase tem " + nrSpace(frase) + " espacos.");

        if (isLower(frase)){
            System.out.println("A frase contem apenas caracteres minusculos.");
        }
        else{
            System.out.println("A frase nao contem apenas caracteres minusculos");
        }

        System.out.println("Frase reconstruida sem espacos a mais: " + withoutSpaces(frase));

        if (isPalindromo(frase)){
            System.out.println("A frase é um palíndromo.");
        }
        else{
            System.out.println("A frase não é um palíndromo." );
        }
    }


public static int nrDigits(String str){
    int count = 0;
    char ch;
    for(int i = 0; i < str.length(); i++){
        ch = str.charAt(i);
        if(Character.isDigit(ch)){
            count++;
        }
    }
    return count;
}

public static int nrSpace(String str){
    int count = 0;							
    char ch;
    for(int i=0; i < str.length(); i++) {
        ch = str.charAt(i);
        if(Character.isWhitespace(ch)) {
            count++;
        }
    }
   return count;
}


public static boolean isLower(String str){
    boolean lower = true;
    char ch;
    for(int i = 0; i < str.length(); i++){
        ch = str.charAt(i);
        if(Character.isUpperCase(ch)){
            lower = false;
            break;
        }
    }
    return lower;
}


public static String withoutSpaces(String str){
    String Wspace;
    Wspace = str.replaceAll("\\s{2,}", " "); //regex
    return Wspace;
}

public static boolean isPalindromo(String s){
    boolean is=true;
    char ch1;
    char ch2;
    for(int i = 0; i < s.length(); i++){
        ch1 = s.charAt(i);
        ch2 = s.charAt(s.length() - 1 - i);

        if(ch1 != ch2){
           is=false;
            break;
        }

    }
    return is;
}

}