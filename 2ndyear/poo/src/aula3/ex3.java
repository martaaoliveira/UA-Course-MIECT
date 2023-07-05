package aula3;
import java.util.*;
public class ex3 {
    public static void main(String[] args){
        int i,m=0,flag=0;      
        int n;
        Scanner ler = new Scanner(System.in);
        System.out.println("Insira um numero inteiro");
        n=ler.nextInt();   
        m=n/2;
        ler.close();
        if(n==0||n==1){  
         System.out.println(n +"não primo");      
        }else{  
         for(i=2;i<=m;i++){      
          if(n%i==0){      
           System.out.println(n+" nao primo ");      
           flag=1;      
           break;      
          }      
         }      
         if(flag==0)  { System.out.println(n+" numero primo"); }  
        }
    }

}
