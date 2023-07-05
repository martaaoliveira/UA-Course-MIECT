package aula6;

//import aula5.Data;

public class Aluno extends Pessoa {
    private int nmeca;
    private Data inscricao;
    private static int contador=100;

    public Aluno(String nome,int cc, Data dataNasc){
        super(nome, cc, dataNasc);
        this.nmeca=contador++;
        this.inscricao=Data.today();
    }

    public Aluno(String nome,int cc, Data dataNasc,Data inscricao){
        super(nome, cc, dataNasc);
        this.inscricao=inscricao;
        this.nmeca=contador++;
    }

    public int getnmeca(){
        return nmeca;
    }
    public Data getIncricao(){
        return inscricao;
    }

    @Override
    public String toString(){
        return super.toString() + " Data de incricao " + inscricao + " nr meca " + nmeca;

}

}
