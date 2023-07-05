package aula5;
import java.util.Scanner;
public class ex2 {
    public static final Scanner ler = new Scanner(System.in);
    public static void main(String[] args) {
    calendario c = new calendario(0,1);

		while(true) {
			System.out.println("\nCalendar operations:");
			System.out.println("1 - create new calendar");
			System.out.println("2 - print calendar month");
			System.out.println("3 - print calendar");
			System.out.println("0 - exit");
			System.out.print("\nOption: ");

			int op = ler.nextInt();
            switch(op){

                case 1:
                System.out.println("\nCreate calendar");
				System.out.print("ano: ");
				int ano = ler.nextInt();
				System.out.print("Week day(in numbers): ");
				int dia_semana = ler.nextInt();
				c = new calendario(ano, dia_semana);
                break;
                case 2:
                System.out.println("\nmonth calendar:");
				System.out.print("mes: ");
				int mes = ler.nextInt();
				System.out.print(c.mes(mes));
                break;
                case 3:
                System.out.println(c);
                break;
                default:
                System.exit(1);
            }
        }
}

}


