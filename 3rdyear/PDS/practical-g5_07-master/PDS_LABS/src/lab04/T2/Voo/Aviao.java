

// package pds_2021_111.lab03;
package lab04.T2.Voo;
public class Aviao {
    private int ex_rows;
    private int ex_cols;
    private int t_rows;
    private int t_cols;
    
    public Aviao(int ex_rows, int ex_cols, int t_rows, int t_cols) {
        this.ex_rows = ex_rows;
        this.ex_cols = ex_cols;
        this.t_rows = t_rows;
        this.t_cols = t_cols;
    }

    public Aviao(int t_rows, int t_cols) {
        this.t_rows = t_rows;
        this.t_cols = t_cols;
    }

    public int getTuristicSeats() {
        return this.t_rows * this.t_cols;
    }

    public int getExecutiveSeats() {
        return this.ex_rows * this.ex_cols;
    }


    public int getExRows() {
        return this.ex_rows;
    }

    public int getExCols() {
        return this.ex_cols;
    }

    public int getTuRows() {
        return this.t_rows;
    }

    public int getTuCols() {
        return this.t_cols;
    }


    @Override
    public String toString() {
        return "Plane : {" +
            " ex_rows='" + this.getExRows() + "'" +
            ", ex_cols='" + this.getExCols() + "'" +
            ", t_rows='" + this.getTuRows() + "'" +
            ", t_cols='" + this.getTuCols() + "'" +
            "}";
    }

}
