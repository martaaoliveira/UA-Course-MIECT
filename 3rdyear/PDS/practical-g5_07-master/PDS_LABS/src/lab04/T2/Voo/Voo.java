
package lab04.T2.Voo;
// package pds_2021_111.lab03;
import java.util.HashMap;

public class Voo {
    private String flight_code;
    private Aviao Aviao;
    private int [][] executive_map;
    private int [][] touristic_map;
    private HashMap<Integer, Reserva> reservations = new HashMap<Integer, Reserva>();
    private int reservation_number;
    private int avl_seats_t;
    private int avl_seats_ex;

    public Voo(String code, Aviao Aviao) {
        this.flight_code = code;
        this.Aviao = Aviao;
        this.reservation_number = 1;

        this.avl_seats_ex = this.Aviao.getExecutiveSeats();
        this.avl_seats_t = this.Aviao.getTuristicSeats();

        this.executive_map = new int[this.Aviao.getExRows()][this.Aviao.getExCols()];
        this.touristic_map = new int[this.Aviao.getTuRows()][this.Aviao.getTuCols()];
    }

    public Aviao getAviao() {
        return this.Aviao;
    }

    public int getTouristicAvlSeats() {
        return this.avl_seats_t;
    }

    public int getExecutiveAvlSeats() {
        return this.avl_seats_ex;
    }

    public Reserva reserveT(int n_seats) {
        //boolean use_next = false;
        int total_seats = n_seats;
        String[] seats = new String[n_seats];

        int n_rows = this.Aviao.getTuRows();
        int n_cols = this.Aviao.getTuCols();

        for (int row = 0; row < n_rows; row++) { // primeiro tenta pôr toda gente junta ou em filas consecutivas se não couberem todos numa fila
            int col;
            for (col = 0; col < n_cols && this.touristic_map[row][col] == 0; col++);

            if (col == n_cols) { // todos os lugares desta fila estão livres
                if (col >= n_seats) { // pode pôr toda a gente aqui

                    // reserva os lugares
                    for (int j = 0; j < n_seats; j++) {
                        seats[total_seats-n_seats+j] = (row+1+this.Aviao.getExRows()) + " " + String.valueOf((char)(65 + j));                        
                        this.touristic_map[row][j] = this.reservation_number;
                    }

                    this.reservation_number++;
                    this.avl_seats_t -= total_seats;

                    Reserva r = new Reserva('T', this.reservation_number - 1, total_seats, seats);
                    this.reservations.put(this.reservation_number-1, r);
                    return r;
                }
                else { // mete parte aqui e parte na próxima fila
                    int c = 0;

                    // reserva parte dos lugares
                    for (int j = total_seats - n_seats; j < total_seats && c < n_cols; j++, c++) {
                        seats[total_seats-n_seats+c] = (row+1+this.Aviao.getExRows()) + " " + String.valueOf((char)(65 + c));
                        this.touristic_map[row][c] = this.reservation_number;
                    }

                    n_seats = n_seats - c;

                    if (n_seats == 0) { // já tem todos os lugares marcados
                        this.reservation_number++;
                        this.avl_seats_t -= total_seats;

                        Reserva r = new Reserva('T', this.reservation_number - 1, total_seats, seats);
                        this.reservations.put(this.reservation_number-1, r);
                        return r;
                    }
                }
            }
        }

        // já não há filas completamente disponíveis
        for (int row = 0; row < n_rows; row++) {
            int c = 0;
            for (int col = 0; col < n_cols; col++) {
                if (this.touristic_map[row][col] == 0) {
                    c++;
                }
            }

            if (c >= n_seats) { // pode pôr toda a gente aqui
                int x = 0;

                // reserva alguns lugares aqui
                for (int j = 0; j < n_cols && x < n_seats; j++) {
                    if (this.touristic_map[row][j] == 0) {
                        seats[(total_seats + x) - n_seats] = (row+1+this.Aviao.getExRows()) + " " + String.valueOf((char)(65 + j));
                        this.touristic_map[row][j] = this.reservation_number;
                        x++;
                    }
                }

                this.reservation_number++;
                this.avl_seats_t -= total_seats;

                Reserva r = new Reserva('T', this.reservation_number - 1, total_seats, seats);
                this.reservations.put(this.reservation_number-1, r);
                return r;
            }
            else { // mete parte aqui e parte na próxima fila
                int x = 0;

                // reserva alguns lugares aqui
                for (int j = total_seats - n_seats, k = 0; j < n_cols && k < n_seats; j++, k++) {
                    if (this.touristic_map[row][k] == 0) {
                        seats[(total_seats + x) - n_seats] = (row+1+this.Aviao.getExRows()) + " " + String.valueOf((char)(65 + j));
                        this.touristic_map[row][k] = this.reservation_number;
                        x++;
                    }
                }

                n_seats = n_seats - x;

                if (n_seats == 0) { // já tem todos os lugares marcados
                    this.reservation_number++;
                    this.avl_seats_t -= total_seats;

                    Reserva r = new Reserva('T', this.reservation_number - 1, total_seats, seats);
                    this.reservations.put(this.reservation_number-1, r);
                    return r;
                }
            }
        }
        
        return null;
    }

    // Podemos criar um método único que faça a reserva para as duas classes
    public Reserva reserveE(int n_seats) {
        int total_seats = n_seats;
        String[] seats = new String[n_seats];

        int n_rows = this.Aviao.getExRows();
        int n_cols = this.Aviao.getExCols();

        for (int row = 0; row < n_rows; row++) { // primeiro tenta pôr toda gente junta ou em filas consecutivas se não couberem todos numa fila
            int col;
            for (col = 0; col < n_cols && this.executive_map[row][col] == 0; col++);

            if (col == n_cols) { // todos os lugares desta fila estão livres
                if (col >= n_seats) { // pode pôr toda a gente aqui

                    for (int j = 0; j < n_seats; j++) {
                        seats[total_seats-n_seats+j] = (row+1) + " " + String.valueOf((char)(65 + j));                        
                        this.executive_map[row][j] = this.reservation_number;
                    }

                    this.reservation_number++;
                    this.avl_seats_ex -= total_seats;

                    Reserva r = new Reserva('E', this.reservation_number - 1, total_seats, seats);
                    this.reservations.put(this.reservation_number-1, r);
                    return r;
                }
                else { // mete parte aqui e parte na próxima fila
                    int c = 0;
                    for (int j = total_seats - n_seats; j < total_seats && c < n_cols; j++, c++) {
                        seats[total_seats-n_seats+c] = (row+1) + " " + String.valueOf((char)(65 + c));
                        this.executive_map[row][c] = this.reservation_number;
                    }

                    n_seats = n_seats - c;

                    if (n_seats == 0) { // já tem todos os lugares marcados
                        this.reservation_number++;
                        this.avl_seats_ex -= total_seats;

                        Reserva r = new Reserva('E', this.reservation_number - 1, total_seats, seats);
                        this.reservations.put(this.reservation_number-1, r);
                        return r;
                    }
                }
            }
        }

        // já não há filas completamente disponíveis
        for (int row = 0; row < n_rows; row++) {
            int c = 0;
            for (int col = 0; col < n_cols; col++) {
                if (this.executive_map[row][col] == 0) {
                    c++;
                }
            }

            if (c >= n_seats) { // pode pôr toda a gente aqui
                int x = 0;
                for (int j = 0; j < n_cols && x < n_seats; j++) {
                    if (this.executive_map[row][j] == 0) {
                        seats[(total_seats + x) - n_seats] = (row+1) + " " + String.valueOf((char)(65 + j));
                        this.executive_map[row][j] = this.reservation_number;
                        x++;
                    }
                }

                this.reservation_number++;
                this.avl_seats_ex -= total_seats;

                Reserva r = new Reserva('E', this.reservation_number - 1, total_seats, seats);
                this.reservations.put(this.reservation_number-1, r);
                return r;
            }
            else { // mete parte aqui e parte na próxima fila
                int x = 0;
                for (int j = total_seats - n_seats, k = 0; j < n_cols && k < n_seats; j++, k++) {
                    if (this.executive_map[row][k] == 0) {
                        seats[(total_seats + x) - n_seats] = (row+1) + " " + String.valueOf((char)(65 + j));
                        this.executive_map[row][k] = this.reservation_number;
                        x++;
                    }
                }

                n_seats = n_seats - x;

                if (n_seats == 0) { // já tem todos os lugares marcados
                    this.reservation_number++;
                    this.avl_seats_ex -= total_seats;

                    Reserva r = new Reserva('E', this.reservation_number - 1, total_seats, seats);
                    this.reservations.put(this.reservation_number-1, r);
                    return r;
                }
            }
        }
        
        return null;
    }
    
    public boolean cancelRes(int r_number) {
        // verifica se o número da reserva existe
        if (this.reservations.containsKey(r_number)) {
            Reserva r = this.reservations.get(r_number);
            String [] s = r.getSeats();

            if (r.getClass_code() == 'T') { // vê qual a classe da reserva
                this.avl_seats_t += r.getN_seats();
                for (int i = 0; i < s.length; i++) {
                    String [] seat = s[i].split(" ");
                    int row = Integer.parseInt(seat[0]) - this.getAviao().getExRows() - 1;
                    int col = (int) seat[1].charAt(0) - 65; // retirar o valor do A

                    this.touristic_map[row][col] = 0;
                }
            }
            else if (r.getClass_code() == 'E') {
                this.avl_seats_ex += r.getN_seats();
                for (int i = 0; i < s.length; i++) {
                    String [] seat = s[i].split(" ");
                    int row = Integer.parseInt(seat[0]) - 1;
                    int col = (int) seat[1].charAt(0) - 65; // retirar o valor do A

                    this.executive_map[row][col] = 0;
                }
            }
            else {
                return false;
            }

            this.reservations.remove(r_number);
            return true;
        }
        return false;
    }

    public void printRes() {
        Aviao p = this.getAviao();
        int total_rows = p.getExRows() + p.getTuRows();
        int total_cols = (p.getExCols() > p.getTuCols()) ? p.getExCols() : p.getTuCols();

        // primeira linha
        String msg = String.format("%2s ", " ");
        for (int i = 1; i <= total_rows; i++) {
            msg += String.format("%2d ", i);
        }

        msg += '\n';

        // o resto das linhas
        for (int col = 0; col < total_cols; col++) {
            // adiciona a letra
            msg += String.format("%2s ", String.valueOf((char)(65 + col)));

            if (col < p.getExCols()){
                for (int lin = 0; lin < p.getExRows(); lin++) {
                    msg += String.format("%2d ", this.executive_map[lin][col]);
                }
            }
            else { // adicionar espaços em branco
                for (int lin = 0; lin < p.getExRows(); lin++) {
                    msg += String.format("%2s ", " ");
                }
            }

            if (col < p.getTuCols()) {
                for (int lin = 0; lin < p.getTuRows(); lin++) {
                    msg += String.format("%2d ", this.touristic_map[lin][col]);
                }
            } // aqui não precisa de espaços em branco porque tem \n

            msg += '\n';
        }

        System.out.println(msg);
    }

    @Override
    public String toString() {
        return "Flight : {" +
            " flight_code='" + this.flight_code + "'" +
            " Aviao='" + this.Aviao.toString() + "'" +
            ", avl_seats_t='" + this.avl_seats_t + "'" +
            ", avl_seats_ex='" + this.avl_seats_ex + "'" +
            "}";
    }

}

