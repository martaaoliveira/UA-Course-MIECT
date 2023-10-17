package lab04.T2.Voo;

//package pds_2021_111.lab03;

public class Reserva {
    private char class_code;
    private int res_number;
    private int n_seats;
    private String[] seats; // array com os sitios (ex: [3A, 3B, 3C])

    public Reserva(char class_code, int res_number, int n_seats, String[] seats) {
        this.class_code = class_code;
        this.res_number = res_number;
        this.n_seats = n_seats;
        this.seats = seats;
    }

    public Reserva(char class_code) {
        this.class_code = class_code;
    }

    public int getNumber() {
        return this.res_number;
    }

    // ajuda a fazer o print quando se faz uma reserva
    public String seatsToString() {
        String ret = "";
        for (int i = 0; i < this.n_seats; i++) {
            ret += this.seats[i];

            if (i + 1 < this.n_seats) {
                ret += " | ";
            }
        }
        return ret;
    }

    /**
     * @return char return the class_code
     */
    public char getClass_code() {
        return class_code;
    }

    /**
     * @return int return the res_number
     */
    public int getRes_number() {
        return res_number;
    }

    /**
     * @return int return the n_seats
     */
    public int getN_seats() {
        return n_seats;
    }

    public String[] getSeats() {
        return this.seats;
    }

}