package lab03.ex2;

public class Reserva {
    private String classe;
    private int numLugares;
    private int id;

    public Reserva (String classe, int numLugares) {
        this.classe = classe;
        this.numLugares = numLugares;
    }

    public Reserva (String classe, int numLugares, int id) {
        this.classe = classe;
        this.numLugares = numLugares;
        this.id = id;
    }

    public String getClasse() {return classe;}
    public int getNumLugares() {return numLugares;}
    public int getID() {return id;}

    public void setClasse(String classe) {this.classe = classe;}
    public void setNumLugares(int numLugares) {this.numLugares = numLugares;}
    public void setID(int ID) {this.id = ID;}
    
    public String toID(){return String.valueOf(id);}

    @Override
    public String toString() {
        return classe + " " + String.valueOf(numLugares);
    }

}
