package aula8;

public class ligeiro extends viatura {
    int nrQuadro,capacidadeBagageira;

    public ligeiro(String matricula, String marca, String modelo, int potencia, int nrQuadro,int capacidadeBagageira) {
    super(matricula, marca, modelo, potencia);
    this.nrQuadro = nrQuadro;
    this.capacidadeBagageira = capacidadeBagageira;
}

public int getNumQuadro() {
    return nrQuadro;
}

public void setNumQuadro(int nrQuadro) {
    this.nrQuadro = nrQuadro;
}

public int getCapacidadeBagageira() {
    return capacidadeBagageira;
}

public void setCapacidadeBagageira(int capacidadeBagageira) {
    this.capacidadeBagageira = capacidadeBagageira;
}

@Override
public String toString() {
    return super.toString() + "Ligeiro com capacidadeBagageira=" + capacidadeBagageira + ", nrQuadro=" + nrQuadro;
}


public boolean equals(ligeiro other) {
    if (this == other)
        return true;
    if (!super.equals(other))
        return false;
    if (capacidadeBagageira != other.capacidadeBagageira)
        return false;
    if (nrQuadro != other.nrQuadro)
        return false;
    return true;
}


}
