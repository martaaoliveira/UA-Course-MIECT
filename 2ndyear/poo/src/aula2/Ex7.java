package aula2;
import java.lang.Math;

import java.util.Scanner;
public class Ex7 {
    public static void main(String[] args){
        Scanner ler = new Scanner(System.in);
        int x1,y1,x2,y2;
        double dist;
        System.out.printf("Introduza coordenadas reais x e y de 2 pontos:\nx1:\n");
        x1=ler.nextInt();
        System.out.printf("y1:\n");
        y1=ler.nextInt();
        System.out.printf("x2:\n");
        x2=ler.nextInt();
        System.out.printf("y2:\n");
        y2=ler.nextInt();
        dist=Math.sqrt((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
        System.out.printf("A distancia entre os 2 pontos %.2f:",dist);
        ler.close();
    }
}
