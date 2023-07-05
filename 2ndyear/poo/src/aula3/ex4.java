package aula3;
import java.util.*;
public class ex4 {
    public static void main(String[] args){

        Scanner ler = new Scanner(System.in);

        double sum = 0;
        int counter = 0;
        double max, min, n2;

        System.out.print("Insira um valor: ");
        double  n1 = ler.nextDouble();
        max = n1;
        min = n1;
        do{
            System.out.println("Insira outro valor: ");
            n2 = ler.nextDouble();
            sum += n2;
            counter++;
            if(n2>max){
                max = n2;
            }else if(n2<min){
                min = n2;
            }
        }while(n2 != n1);

        System.out.printf("O valor máximo é %.2f",max);
        System.out.print("\n");
        System.out.printf("O valor mínimo é %.2f",min);
        System.out.print("\n");
        System.out.printf("A média é %.2f",sum/counter);
        System.out.print("\n");
        System.out.printf("Números lidos: %d",counter);

        ler.close();
    }
}
