package aula7;

public class apartamento extends alojamento {
    private int numQuartos;

public apartamento(String codigo, String nome, String local, double precoNoite, double avaliacao, int numQuartos) {
    super(codigo, nome, local, precoNoite, avaliacao);
    this.numQuartos = numQuartos;
}

@Override
public String toString() {
    return super.toString() + "Apartamento com numQuartos=" + numQuartos + "";
}

public int getNumQuartos() {
    return numQuartos;
}

public void setNumQuartos(int numQuartos) {
    this.numQuartos = numQuartos;
}


}
