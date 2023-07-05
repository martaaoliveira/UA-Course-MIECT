package aula3;
import java.util.Scanner;

public class ex2 {
    public static void main(String[] args){
        Scanner read = new Scanner(System.in);
        int n;
        do{
            System.out.print("Insira um valor n positivo: ");
            n= read.nextInt();
        }while(n<0);
        do{
            System.out.printf("%d\n",n);
            n-=1;
        }while(n>=0);


        read.close();
    }
}
