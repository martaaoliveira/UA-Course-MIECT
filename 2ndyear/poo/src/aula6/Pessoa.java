package aula6;
//import aula5.Data;



public class Pessoa {
    private String nome;
    private int cc;
    private Data dataNasc;
    
    public Pessoa(String nome, int cc, Data dataNasc){
        this.nome=nome;
        this.cc=cc;
        this.dataNasc=dataNasc;
    }
    public String getName(){
        return nome;
    }
    public int getcc(){
        return cc;
    }

    public Data getdataNasc(){
        return dataNasc;
    }
    @Override
    public String toString(){
        return nome + ";" + " CC " + cc + " Data " + dataNasc;
        
    }
}
 
