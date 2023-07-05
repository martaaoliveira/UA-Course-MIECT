package aula2;

import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args){
    Scanner ler = new Scanner(System.in);
    System.out.printf("Indique a temperatura em Celsius:\n");

    double temperatura_C= ler.nextDouble();
    double temperatura_F= (1.8*temperatura_C) + 32;
    ler.close();
    System.out.printf("A temperatura em Fahrenheit: %.2f F\n", temperatura_F);
    
    
}
}
