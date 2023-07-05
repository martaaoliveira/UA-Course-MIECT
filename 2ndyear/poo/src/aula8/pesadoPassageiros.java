package aula8;

public class pesadoPassageiros extends viatura {
    private int nrQuadro,peso,maxPassageiros;
    public pesadoPassageiros(String matricula, String marca, String modelo, int potencia, int nrQuadro, int peso,int maxPassageiros) {
        super(matricula, marca, modelo, potencia);
        this.maxPassageiros = maxPassageiros;
        this.nrQuadro = nrQuadro;
        this.peso = peso;
}


@Override
public String toString() {
    return super.toString() + "Pesado com numQuadro=" + nrQuadro + ", peso=" + peso + "e transporta no maximo"+ maxPassageiros;
}


public int getMaxPassageiros() {
    return maxPassageiros;
}

public void setMaxPassageiros(int maxPassageiros) {
    this.maxPassageiros = maxPassageiros;
}

public int getNumQuadro() {
    return nrQuadro;
}

public void setNumQuadro(int numQuadro) {
    this.nrQuadro = numQuadro;
}

public int getPeso() {
    return peso;
}

public void setPeso(int peso) {
    this.peso = peso;
}

public boolean equals(pesadoPassageiros another) {
    if (this == another)
        return true;
    if (!super.equals(another))
        return false;
    if (nrQuadro != another.nrQuadro)
        return false;
    if (peso != another.peso)
        return false;
    if(maxPassageiros!=another.maxPassageiros)
        return false;
    if (maxPassageiros != another.maxPassageiros)
        return false;
    return true;
}
    
}
