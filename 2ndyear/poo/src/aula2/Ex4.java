package aula2;
import java.lang.Math;
import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args){
        Scanner ler = new Scanner(System.in);
        System.out.printf("Indique o montante investido\n");
        double montante=ler.nextDouble();
        System.out.printf("e a taxa de juro mensal:\n");
        double taxa= ler.nextDouble();
        double valor= montante*(Math.pow(1+(taxa/100),3));
        ler.close();
        System.out.printf("Para um investimento de %.1f euros e uma taxa de %.1f o montante ao fim de 3 meses sera de %.3f euros.\n",montante, taxa, valor);
        
    }
}
