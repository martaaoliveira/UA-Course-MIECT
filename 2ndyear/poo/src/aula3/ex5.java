package aula3;
import java.util.*;
import java.lang.Math;
public class ex5 {
    public static void main(String[] args){
        Scanner ler = new Scanner(System.in);
        int montante;
        double taxa,valor=0;
    
        do{
            System.out.print("Indique o montante investido(o número tem de ser positivo e multiplo de 1000!): \n");
            montante = ler.nextInt();
        }while((montante <= 0 || montante % 1000 != 0));   
            
        do{
            System.out.print("Qual a Taxa de juro mensal(valor entre 0 e 5): \n");
            taxa = ler.nextDouble();
        }while((taxa < 0 || taxa >5));
    
        ler.close();
    for(int i=1;i<=12;i++){
        valor = montante * Math.pow(1 + (taxa / 100), i);
        System.out.printf("Para um investimento de %d euros e uma taxa de %.1f%% o montante ao fim de %d meses será de %.2f euros.\n",montante, taxa,i, valor);
    }
    //valor = montante * Math.pow(1 + (taxa / 100), 12);
    
    }
    
}
