package aula2;

import java.util.Scanner;

public class Ex5 {
    public static void main(String[] args){
        double vm;
        Scanner ler= new Scanner(System.in);
        System.out.printf("Introduza o valor da primeira velocidade em KM/h: ");
        double v1=ler.nextDouble();
        System.out.printf("Introduza o valor da primeira distância em metros:");
        double d1 =ler.nextDouble();
        System.out.printf("Introduza o valor da segunda velocidade em KM/h: ");
        double v2=ler.nextDouble();
        System.out.printf("Introduza o valor da segunda distância em metros:");
        double d2= ler.nextDouble();
        ler.close();
        vm=(d1+d2)/((d1/v1)+(d2/v2));
        System.out.printf("velocidade média: %.3f",vm);
     

    

    }
}
