package aula4;
import java.util.Scanner;
public class ex3 {

    public static void main(String [] args){
        String acr;
        Scanner ler = new Scanner(System.in);
        do{
        System.out.println("Este programa faz acronimos.\nInsira um frase(minimo 3 caracteres)");
        acr=ler.nextLine();
        }while(acr.length()<3);
        String[] espaco = acr.split(" ");
        StringBuilder sb= new StringBuilder();
        for(int i=0;i<espaco.length; i++ ){
            if (espaco[i].length()<=2){
                continue;
            }
            else{
            sb.append(espaco[i].charAt(0));
            }
        }
        System.out.println(sb.toString());
        ler.close();
    }
    
}
