package aula3;
import java.util.*;
public class ex7 {
    public static void main(String[] args){
    Scanner ler= new Scanner(System.in);
    int n=0;
    int min=0;
    int max=100;
    String resposta="";
    int secret_number=(int)Math.random();
    //boolean finished;
    //boolean first_try=true;
    //finished=false;
    
    Random r= new Random();
    do{
        secret_number=(r.nextInt(max-min)+min);
        int numAttempts=1;
        do{
            System.out.print("Qual é a sua tentativa? ");
            n = ler.nextInt();
            if(n > secret_number){
                System.out.print("Valor alto\n");
                numAttempts++;
            }
            else if(n < secret_number){
                System.out.print("Valor baixo\n");
                numAttempts++;
            }
            else{
                System.out.print("Parabéns!Acertou!\n");
                System.out.printf("Acertou ao fim de %d tentativas\n", numAttempts);
                numAttempts++;
            }
            
        }while(n!=secret_number);
        System.out.println("Deseja continuar? ");
        resposta = ler.next();
    
    }while(resposta.equals("S")||resposta.equals("s"));
     ler.close();
    }
}



