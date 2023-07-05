package aula11;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class voo {
    private String hora;
    private String voo;
    private String companhia;
    private String origem;
    private String atraso;
    private String obs;
    private HashMap<String,String>companhias=new HashMap<>();

    public voo(String hora,String voo,String origem,String atraso) throws IOException {
        this.hora=hora;
        this.voo=voo;
        this.origem=origem;
        this.atraso=atraso;
        if(this.atraso==""){
            obs="";
        }
        else{
            this.obs="Previsto "+ this.previsao();
        }
        this.companhia=getCompanhias().get(this.voo.substring(0,2));

    }


    public String getHora(){
        return hora;
    }

    public String getVoo(){
        return voo;
    }

    public String getOrigem(){
        return origem;
    }

    public String getAtraso(){
        return atraso;
    }
    public String getObs(){
        return obs;
    }
    public String getCompanhia(){
        return companhia;
    }

    public int setAtrasoSegundos(){
        return Integer.parseInt(this.getAtraso().substring(0, 2))*3600 + Integer.parseInt(this.getAtraso().substring(3, 5))*60; 
    }

    public String previsao(){
        int hora_prevista=Integer.parseInt(this.hora.substring(0, 2)) + Integer.parseInt(this.atraso.substring(0, 2));
        int minuto_previsto = Integer.parseInt(this.hora.substring(3, 5)) + Integer.parseInt(this.atraso.substring(3, 5));
        if (minuto_previsto >= 60) {
            hora_prevista += 1;
            minuto_previsto -= 60;
        }
        if (hora_prevista >= 24) {
            hora_prevista -= 24;
        }

        return String.format("%02d:%02d ", hora_prevista,minuto_previsto);

    }

    @Override
    public String toString() {
        return String.format("%-7s %-8s %-20s %-21s %-7s %-20s", this.hora, this.voo, this.companhia, this.origem, this.atraso, this.obs);
    }
    private HashMap<String,String> getCompanhias()throws IOException{
        File f= new File("C:/Users/Marta Oliveira/OneDrive - Universidade de Aveiro/Desktop/Universidade/POO/poo/src/aula11/companhias.txt");
        Scanner input= new Scanner(f);
        while(input.hasNext()){
            String[] companhia=input.nextLine().split("\t");
            companhias.put(companhia[0], companhia[1]);
        }
        input.close();
        return companhias;
    }
}
