package aula5;

import java.util.Scanner;

public class ex1 {

    public static final Scanner ler = new Scanner(System.in);
    public static void main(String [] args){
    Data data = new Data(1,1,1);
    while(true){
        System.out.println("\nDate operations:");
        System.out.println("1 - create new date");
        System.out.println("2 - show current date");
        System.out.println("3 - increment date");
        System.out.println("4 - decrement date");
        System.out.println("0 - exit");
        System.out.print("\nOption: ");
        int op=ler.nextInt();
        switch(op){
            case 1:
            System.out.println("\n(A criar nova data)");
			System.out.print("dia: ");
			int dia = ler.nextInt();
			System.out.print("mes: ");
			int mes = ler.nextInt();
			System.out.print("ano: ");
			int ano = ler.nextInt();
			data = new Data(dia, mes, ano);
            break;

            case 2:
            System.out.println(data);
            break;

            case 3:
            System.out.println("\n(incrementar data)");
			System.out.print("numero de dias: ");
			int dias1 = ler.nextInt();
			data.incrementar(dias1);
            System.out.println("nova data " + data);
            break;

            case 4:
            System.out.println("\n(decrementar data)");
            System.out.print("numero de dias: ");
            int dias2 = ler.nextInt();
            data.decrementar(dias2);
            System.out.println("nova data " + data);
            break;
            case 0:
            System.exit(1);
        
            default:
            break;
        }
        


    }
   
}
}
