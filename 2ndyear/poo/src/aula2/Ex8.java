package aula2;
import java.util.Scanner;
public class Ex8 {
    public static void main(String[] args){
        Scanner ler = new Scanner(System.in);
        System.out.printf("Introduza a valor do cateto1:\n");
        double c1=ler.nextDouble();
        System.out.printf("Introduza a valor do cateto2:\n");
        double c2=ler.nextDouble();
        double hipo= Math.sqrt(Math.pow(c1, 2) + Math.pow(c2, 2));
        ler.close();
        double ang =Math.cos(c1/hipo);
        System.out.printf("O valor da hipotenusa sera aproximadamente: %.2f \n",hipo);
        System.out.printf("O valor do angulo sera aproximadamente: %.2f\n",ang);

    }
}
