package aula8;

public class PesadoEletrico extends pesadoPassageiros implements VeiculoEletrico {
    private int autonomia;

    public PesadoEletrico(String matricula, String marca, String modelo, int potencia, int numQuadro,int peso, int maxPassageiros, int autonomia) {
        super(matricula, marca, modelo, potencia, numQuadro, peso, maxPassageiros);
        this.autonomia = autonomia;
    }

    public int autonomia() {
        return this.autonomia;
    }

    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

    public void carregar(int percentagem) {
        if (percentagem + autonomia >= 100) {
            autonomia = 100;
        } else {
            autonomia += percentagem;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Pesado PassageirosEletrico com autonomia=" + autonomia;
    }

 

   
    public boolean equals(PesadoEletrico other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (autonomia != other.autonomia)
            return false;
        return true;
    }

 
}
