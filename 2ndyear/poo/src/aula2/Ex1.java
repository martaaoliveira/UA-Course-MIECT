package aula2;

import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args) {
    double km, milhas;
    Scanner ler= new Scanner(System.in);
    System.out.printf("Introduza um valor em KM: \n");
    km = ler.nextDouble();
    milhas= km/1.609;
    ler.close();
    System.out.printf("O valor em milhas: %.2f\n",milhas);
 
    }
}
