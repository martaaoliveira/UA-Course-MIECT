package lab03.ex2;

import java.io.*;
import java.util.*;

public class GestorInterface {
    private String option;
    private String[] args;
    private LinkedList<Voo> voos = new LinkedList<Voo>();

    public GestorInterface() throws FileNotFoundException {
        menu();
    }

    private void menu() throws FileNotFoundException {
        helpGuide();
        Scanner sc = new Scanner(System.in);
        option = "Z";
        while (!option.equals("Q")) {
            System.out.println("\n\nIndique uma opção:(H para ajuda)\n");
            // ler opção indicada
            try {
                String temp = sc.nextLine();
                args = temp.split(" ");
                if (!"HIMERCQF".contains(args[0].toUpperCase())) {
                    System.err.println("\nOpção tem de ser uma das especificadas");
                }
                option = args[0].toUpperCase();
            } catch (Exception e) {
                System.err.println("\nOpção tem de seguir o formato correto");
                continue;
            }
            switch (option) {
                case "Q":
                    System.out.println("A terminar programa");
                    System.exit(1);
                    break;
                case "H":
                    helpGuide();
                    break;

                case "I":
                    String filename = args[1];
                    readFile(filename);
                    break;

                case "M":
                    printVooReservas(args[1]);
                    break;

                case "F":
                    if (args.length == 3)
                        addSeats(args[1], args[2]);
                    else if (args.length == 4)
                        addSeats(args[1], args[2], args[3]);
                    else
                        System.out.println("Introduza flightcode class numberofseats");
                    break;

                case "R":
                    if (args.length == 4)
                        addReserva(args[1], args[2], args[3]);
                    else
                        System.out.println("Introduza flightcode class numberofseats");
                    break;

                case "C":
                    if (args.length > 2)
                        System.out.println("Introduza apenas reservation_code (flightcode:seq_res_number)");
                    else
                        cancelaReserva(args[1]);
                    break;

                default:
                    System.out.println("");
            }
        }

        sc.close();
    }

    // função para imprimir ajuda

    public void helpGuide() {
        System.out.println("Lista de opções:");
        System.out.println("H -> apresenta o menu de ajuda");
        System.out.println("I [filename] -> ler um ficheiro com informação sobre um voo");
        System.out.println("M [flight_code] -> exibir o mapa de reservas de um voo.");
        System.out.println(
                "F [flight_code] [num_seats_executive] [num_seats_tourist] -> acrescenta um novo voo com o codigo, lugares executivos e lugares turisticos se for o caso");
        System.out.println(
                "R [flight_code] [class number_seats] -> acrescenta reserva a um voo, da classe (T/E) e numero de lugares");
        System.out.println("C [reservation_code] - cancela uma reserva");
        System.out.println("Q -> termina o programa");
        System.out.println("");
        System.out.println(
                "Para utilizar este programa deve introduzir a letra da opção que pretende e argumentos caso seja preciso");
        System.out.println("Exemplo: M TP1920 ");
        System.out.println("");
    }

    public boolean readFile(String input) throws FileNotFoundException {
        String absPath = System.getProperty("user.dir") + "/" + input;
        File file = new File(absPath);
        try {
            Scanner scf = new Scanner(file);
            String tmp = scf.nextLine();
            String[] primeiralinha = tmp.split(" ");

            if (primeiralinha[0].toCharArray()[0] == '>') {
                Voo v;
                if (primeiralinha.length == 2) {
                    v = new Voo(primeiralinha[0].substring(1), primeiralinha[1]);
                } else {
                    v = new Voo(primeiralinha[0].substring(1), primeiralinha[1], primeiralinha[2]);
                }
                System.out.println(v.toString());
                while (scf.hasNextLine()) {
                    String read = scf.nextLine();
                    String[] linha = read.split(" ");
                    Reserva reserva = new Reserva(linha[0], Integer.parseInt(linha[1]));
                    v.addReserva(reserva);
                }
                
                // voos.add(v); --- When using an Object attribute, it should be referenced as
                // this. Also, see line 11
                this.voos.add(v);
                scf.close();
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        return true;
    }

    public void printVooReservas(String id) {
        findVoo(id).printReservas();
    }

    public void addSeats(String name, String tur) {
        Voo voo = new Voo(name, tur);
        voos.add(voo);
    }

    public void addSeats(String name, String exec, String tur) {
        Voo voo = new Voo(name, exec, tur);
        voos.add(voo);
    }

    public void addReserva(String name, String classe, String nlug) {
        Reserva reserva = new Reserva(classe, Integer.parseInt(nlug));
        findVoo(name).addReserva(reserva);
        System.out.println(name + ":" + reserva.getID() + " = " + nlug);
    }

    private Voo findVoo(String id) {
        for (int i = 0; i < voos.size(); i++) {
            if (id.equals(voos.get(i).getID())) {
                return voos.get(i);
            }
        }
        System.out.println("Código de Voo não encontrado");
        return null;
    }

    public void cancelaReserva(String input) {
        String[] str = input.split(":");
        String nome = str[0];
        String code = str[1];
        findVoo(nome).cancelReserva(Integer.parseInt(code));
    }


}