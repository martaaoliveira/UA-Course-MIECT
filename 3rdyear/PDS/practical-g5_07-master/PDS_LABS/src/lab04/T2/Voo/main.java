package lab04.T2.Voo;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;




public class main {
    Manager manager = new Manager();

    public static void main(String[] args) throws FileNotFoundException{
        main menu = new main();
        if (args.length == 0) {            
            menu.menu();
        }
        else if (args.length == 1 && args[0].contains(".txt")) {
            readFile(args[0]);
        }
        else {
            System.out.println("Error! VoosMain.java only takes one argument and it must be .txt file.");
        }
    }

    public boolean options(String line) {
        Scanner sc = new Scanner(System.in);
        boolean ver = true;
        while (ver) {
            System.out.print("Escolha uma opção: (H para ajuda)\n");
            String[] option = sc.nextLine().split(" ");

            switch (option[0].toUpperCase()) {
                case "H":
                    ajuda();
                    break;

                case "I":
                    if (option.length == 2) {
                        if(!option[1].contains(".txt")) {
                            System.out.println("Ficheiro inválido");
                        }

                        String filename = option[1];
                        printFlightInfo(filename);

                    }else{ System.out.println("[ERROR] Usage: I <filename>");}
                    break;

                case "M":
                    if(option.length == 2){  // verificar se há input depois do M   
                        if (!validateFlightCode(option[1])){
                            System.err.println("Flight code must be type AA0000");
                        }

                        String flight_code = option[1];
                        manager.printReservation(flight_code);

                    } else { System.out.println("[ERROR] Usage: M <flight_code> (example: 'M TP1920')"); menu();}
                    break;

                case "F":
                    String[] num_seats_tourist = option[2].split("x"); 
                    int t_rows = 0; int t_cols = 0; int e_rows = 0; int e_cols = 0;

                    String f_error = "[ERROR] Usage: F <flight_code> <num_seats_executive> <num_seats_tourist> (example: 'F TP1930 5x3 12x4') (<num_seats_executive> is optional)";
                    
                    if (option.length == 3){
                        if (validateFlightCode(option[1]) && option[2].matches("[0-9]+x[0-9]+")){
                            
                            String flight_code = option[1];
                            t_rows = Integer.parseInt(num_seats_tourist[0]); 
                            t_cols = Integer.parseInt(num_seats_tourist[1]);
                            manager.addFlight(flight_code, t_rows, t_cols);

                        } else { System.out.println(f_error); menu();}

                    } else if (option.length == 4){
                        if (validateFlightCode(option[1]) && option[2].matches("[0-9]+x[0-9]+") && option[3].matches("[0-9]+x[0-9]+")){
                            String flight_code = option[1];

                            String[] num_seats_executive = option[2].split("x"); 
                            e_rows = Integer.parseInt(num_seats_executive[0]); 
                            e_cols = Integer.parseInt(num_seats_executive[1]);
                            
                            num_seats_tourist = option[3].split("x"); 
                            t_rows = Integer.parseInt(num_seats_tourist[0]); 
                            t_cols = Integer.parseInt(num_seats_tourist[1]);
                            
                
                            manager.addFlight(flight_code, e_rows, e_cols, t_rows, t_cols);
                            
                        } else { System.out.println(f_error); menu();}

                    } else { System.out.println(f_error); menu();}
                    break;

                case "R":
                    String r_error = "[ERROR] Usage: R <flight_code> <class> <number_seats> (example: 'R TP1930 T 3')";
                    if(option.length  == 4){
                        if (!validateFlightCode(option[1]) || !option[2].matches("T|E") || !isInt(option[3])){        // se qqlr um deles n verificar dá erro
                            System.out.println(r_error); 
                        }

                        String flight_code = option[1];
                        char cl = option[2].charAt(0);
                        int number_seats = Integer.parseInt(option[3]);
                        manager.reserveSeats(flight_code, cl, number_seats);

                    } else { System.out.println(r_error); menu();}
                    break;

                case "C":
                    String c_error = "C <reservation_code> (flight_code:sequential_reservation_number) (example: 'C TP1920:2')";
                    if (option.length == 2){
            
                        String[] reservation_code = option[1].split(":");

                        if (!validateFlightCode(reservation_code[0]) || !isInt(reservation_code[1])){
                            System.out.println(c_error);
                        }

                        String flight_code = reservation_code[0];
                        int reservation_number = Integer.parseInt(reservation_code[1]);

                        manager.cancelReservation(flight_code, reservation_number);

                    } else {System.out.println(c_error); menu(); }
                    break;

                case "Q":
                    System.out.println("Goodbye!");
                    ver = false;
                    break;
                default:
                    System.out.println("Invalid menu option!");
            }
        }
        sc.close();
        return ver;
        

    }

    private static boolean validateFlightCode(String code){
        boolean ver = false;
    
        String pattern = "[A-Z]{2}[0-9]{4}";
        if (code.matches(pattern)){
            ver = true;
        }

        return ver;
    }

    private static boolean isInt(String str) {
        return str.matches("^-?\\d+$");
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Option: (H for help)");
        while(options(sc.nextLine())){
            System.out.println("Option: (H for help)");
        }

        sc.close();
    }
    
    public static void readFile(String fname) throws FileNotFoundException {

        main menu = new main();

        File f = new File(fname);
        Scanner sc = new Scanner(f);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            menu.options(line);
        }

        sc.close();
    }

    private static void printFlightInfo(String fname) {
        Path filePath = Paths.get(fname);
        String str = ""; 
        
        try {
            int num_seats_exec = 0;
            int num_seats_tur = 0;
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                String[] lineWords = line.split(" ");
                String flightCode = lineWords[0].replace(">", "");
            
                if(validateFlightCode(flightCode) && lineWords.length == 3){
                    str+="Código de voo " + flightCode + ". Lugares disponíveis: " ;

                    String[] exec_seats = lineWords[1].split("x");       
                    String[] tur_seats = lineWords[2].split("x");

                    if (isInt(exec_seats[0]) && isInt(exec_seats[1]) && isInt(tur_seats[0]) && isInt(tur_seats[1])){  
                        num_seats_exec = Integer.parseInt(exec_seats[0]) * Integer.parseInt(exec_seats[1]);
                        str += num_seats_exec + " lugares em classe Executiva; ";
                        num_seats_tur = Integer.parseInt(tur_seats[0]) * Integer.parseInt(tur_seats[1]);
                        str += num_seats_tur + " lugares em classe Turística.\n";
                    }

                }else if(validateFlightCode(flightCode) && lineWords.length == 2){    // ha cadeiras turistica só
                        
                    str +="Código de voo " + flightCode + ". Lugares disponíveis: " ;
                    
                    String[] t_seats = lineWords[1].split("x");      // turistica

                    if (isInt(t_seats[0]) && isInt(t_seats[1])){  // verifica q tudo é numeros
                        num_seats_tur = Integer.parseInt(t_seats[0]) * Integer.parseInt(t_seats[1]);
                        str += num_seats_tur + " lugares em classe Turística.\nClasse executiva não disponível neste voo.\n";
                    }
                
                } else if (lineWords.length == 2 && (lineWords[0].charAt(0)  == 'T') || (lineWords[0].charAt(0)  == 'E')){     // linha q representa lugares
                    
                    if (lineWords[0].charAt(0)  == 'T'){

                        if (isInt(lineWords[1])){
                            int seat_taken_t = Integer.parseInt(lineWords[1]);
                            if (num_seats_tur >= seat_taken_t){
                                num_seats_tur -= seat_taken_t;
                            } else { str += "Não foi possível obter lugares para a reserva: " + lineWords[0] + " " + lineWords[1]; }
                        }

                    } else if (lineWords[0].charAt(0) == 'E'){
                        if (isInt(lineWords[1])){
                            int seat_taken_e = Integer.parseInt(lineWords[1]);
                            if (num_seats_exec >= seat_taken_e){
                                num_seats_exec -= seat_taken_e;
                            } else { str += "Não foi possível obter lugares para a reserva: " + lineWords[0] + " " + lineWords[1]; }
                        }
                    }

                } else {System.out.println("[ERROR] Invalid syntax!"); System.exit(0); }
            }

            System.out.println(str + "\n");
    

        } catch (IOException e) {
            System.err.println("Error while reading file");
            System.exit(1);
        }
    }
    
    

    private static void ajuda(){
        String str = "";
        str += "\n------------------------------- Options: -------------------------------\n" ;
        str += "I <filename> - Ler ficheiro de texto contento informação sobre um voo\n";
        str += "M <flight_code> - Exibir mapa das reservas de um voo\n";
        str += "F <flight_code> <num_seats_executive> <num_seats_tourist> - Acrescentar um novo voo (ter lugares na executiva é opcional)\n";
        str += "R <flight_code> <class> <number_seats> - Acrescentar nova reserva a um voo\n";
        str += "C <reservation_code> (flight_code:sequential_reservation_number) - Cancelar reserva\n";
        str += "Q - Sair\n";
        str += "-------------------------------------------------------------------------------";

        System.out.println(str);

    }
}
