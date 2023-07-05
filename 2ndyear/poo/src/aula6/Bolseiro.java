package aula6;
//import aula5.Data;

public class Bolseiro extends Aluno{
    private int bolsa;

    public Bolseiro(String nome, int cc, Data dataNasc, Data dataInsc){
        super(nome, cc, dataNasc, dataInsc);

    }
    public Bolseiro(String nome, int cc, Data dataNasc, Data dataInsc,int bolsa){
        super(nome, cc, dataNasc, dataInsc);
        this.bolsa=bolsa;
    }
    public Bolseiro(String nome, int cc, Data dataNasc, int bolsa) {
		super(nome, cc, dataNasc);
		this.bolsa = bolsa;
	}
    
    public int getbolsa(){
        return bolsa;
    }


    public void setBolsa(int bolsa) {
		this.bolsa = bolsa;
	}
    @Override
    public String toString(){
        return super.toString() + ", Bolsa: " + bolsa;
    }
}
