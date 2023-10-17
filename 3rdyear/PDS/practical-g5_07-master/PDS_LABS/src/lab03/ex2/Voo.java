package lab03.ex2;

import java.util.*;

public class Voo {

    private String id;
    private int classeE;
    private int LugExec;
    private int classeT;
    private int LugTur;
    //private LinkedList<Reserva> reservas;     When creating a LinkedList (or any child of List), you must init it.
    //                                          If you do not do it, it remains a null. You can init it when declaring it
    //                                          or in the constructor when an object of this class is instantiated
    private LinkedList<Reserva> reservas = new LinkedList<Reserva>();   // <- Correct form

    // construtores
     public Voo(String id) {
        this.id = id;
    }

    public Voo(String id, String classE, String classeT) {
        this.id = id;
        this.classeE = multiplicar(classE);
        this.classeT = multiplicar(classeT);
    }

    public Voo(String id, String tur) {
        this.id = id;
        classeE = 0;
        classeT = multiplicar(tur);
    }

    public Voo(String id, int exec, int tur) {
        this.id = id;
        classeE = exec;
        classeT = tur;
    }

    // getters e setters
    public String getID() {
        return id;
    }

    public int getClasseE() {
        return classeE;
    }

    public int getClasseT() {
        return classeT;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public void setClasseE(int classeE) {
        this.classeE = classeE;
    }

    public void setClasseT(int classeT) {
        this.classeT = classeT;
    }

    // funcoes
    public void addReserva(Reserva reserva) {
        String classe = reserva.getClasse();
        boolean adicionado;
        if (classe.equals("E"))
            adicionado = addReservaExecutiva(reserva);
        else
            adicionado = addReservaTuristica(reserva);
        if (!adicionado) {
            System.out.println("Não foi possível obter lugares para a reserva: " + reserva.toString());
        }
        reservas.getLast().setID(reservas.size());
    }

    //------- Funcoes auxiliares ---------
    private int multiplicar(String str) { //String do tipo AxB
        char[] ch = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }
        return Character.getNumericValue(ch[0]) * Character.getNumericValue(ch[2]); //multiplicar A * B
    }

    private boolean addReservaExecutiva(Reserva reserva) {
        if (classeE - LugExec >= reserva.getNumLugares()) {
            LugExec += reserva.getNumLugares();
            reservas.add(reserva);
            return true;
        }
        return false;
    }
    
    private boolean addReservaTuristica(Reserva reserva) {
        if (classeT - LugTur >= reserva.getNumLugares()) {
            LugTur += reserva.getNumLugares();
            reservas.add(reserva);
            return true;
        }
        return false;
    }
    

    public void printReservas(){
        int nlug = classeE + classeT;
        int cont = 0;
        for(int i=0; i<reservas.size(); i++){
            for(int j=0; j<reservas.get(i).getNumLugares(); j++){
                System.out.print(reservas.get(i).toID()+" ");
                cont++;
            }
        }
        for(int i=cont; i<nlug; i++){
            System.out.println("0 ");
        }
    }

    public void cancelReserva (int id) {
        reservas.remove(id);
    }

    @Override
    public String toString() {
        int lugaresDisponiveisExec = classeE - LugExec;
        int lugaresDisponiveisTur = classeT - LugTur;
        return "Código de voo " + id + ". \nLugares disponíveis: " + lugaresDisponiveisExec + " lugares em classe Executiva; "
        + lugaresDisponiveisTur + " lugares em classe Turística.\n";
    }

}


