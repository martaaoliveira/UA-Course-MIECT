package lab04.T2.Voo;

import java.util.HashMap;

// package pds_2021_111.lab03;

public class Manager {
    private HashMap<String, Voo> flights = new HashMap<String, Voo>();

    public Manager() {

    }

    // adiciona e cria o voo com a respetiva info (duas opções)
    public void addFlight(String f_code, int t_rows, int t_cols) {
        Aviao p = new Aviao(t_rows, t_cols);
        Voo f = new Voo(f_code, p);

        flights.put(f_code, f);
        if (flights.get(f_code)!= null) { // verifica se o código foi introduzido no map
            System.out.println("Flight " + f_code + " added with success");
        }
        else {
            System.out.println("Error while creating flight");
        }
    }

    public void addFlight(String f_code, int ex_rows, int ex_cols, int t_rows, int t_cols) {
        Aviao p = new Aviao(ex_rows, ex_cols, t_rows, t_cols);
        Voo f = new Voo(f_code, p);

        flights.put(f_code, f);
        if (flights.get(f_code)!= null) { // verifica se o código foi introduzido no map
            System.out.println("Flight " + f_code + " created successfully");
        }
        else {
            System.out.println("Error while creating flight");
        }
    }

    public Voo getFlight(String f_code) {
        if (flights.containsKey(f_code)) {
            return flights.get(f_code);
        }
        return null;
    }

    public boolean reserveSeats(String f_code, char class_code, int n_seats) {
        
        Voo f = this.getFlight(f_code);
        int ex_seats = f.getAviao().getExecutiveSeats();

        if (class_code == 'T') {
            // Ainda há lugares?
            if (n_seats <= f.getTouristicAvlSeats()) {
                // reservar lugares em classe turistica
                Reserva res = f.reserveT(n_seats);
                if (res != null) {
                    System.out.println(f_code + ":" + res.getNumber() + " = " + res.seatsToString());
                }
                else {
                    System.out.println("An error ocurred while trying to make your reservation");
                }
            }
            else {
                System.out.println("Touristic class does not have " + n_seats + " available. " + f.getTouristicAvlSeats() + " available.");
                return false;
            }

        }
        else if (class_code == 'E') {

            if (ex_seats != 0){
                //reservar lugares em classe executiva
                // ainda há lugares?
                if (n_seats <= f.getExecutiveAvlSeats()) {
                    Reserva res = f.reserveE(n_seats);
                    if (res != null) {
                        System.out.println(f_code + ":" + res.getNumber() + " = " + res.seatsToString());
                    }
                    else {
                        System.out.println("An error ocurred while trying to make your reservation");
                    }
                }
                else {
                    System.out.println("Executive class does not have " + n_seats + " available. " + f.getExecutiveAvlSeats() + " available.");
                    return false;
                }
            } else {
                System.out.println("This is flight doesn't have an Executive class");
                return false;
            }

        }
        else {
            System.out.println("The class character must be a E for executive or a T for touristic.");
            return false;
        }

        return false;
    }

    public void cancelReservation(String f_code, int r_number) {

        Voo f = this.getFlight(f_code);

        if (f != null) {
            if (f.cancelRes(r_number)){ // chama o método da classe flight
                System.out.println(f_code + ":" + r_number + " deleted with success.");
            }
            else {
                System.out.println("Error while canceling your reservation. Maybe reservation " + r_number + " does not exist.");
            }
        }
        else{
            System.out.println("The flight code you entered does not exist.");
        }
    }

    public void printReservation(String f_code) {
        Voo f = this.getFlight(f_code);

        if (f != null) {
            f.printRes();  // chama o método da classe flight
        }
        else {
            System.out.println("The flight code you entered does not exist.");
        }
    }
}
