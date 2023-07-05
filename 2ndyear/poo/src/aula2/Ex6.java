package aula2;

import java.util.Scanner;

public class Ex6 {
    public static void main(String[] args){
        Scanner ler = new Scanner(System.in);
        int s,horas,minutos;
        System.out.print("Insira o números de segundos para se converter no formato hh:mm:ss\n");
        s=ler.nextInt();
        ler.close();
        horas=s/3600;
        minutos=(s%3600)/60;
        s%=60;
        System.out.printf("%dh:%dm:%ds", horas, minutos, s);

    }
}
