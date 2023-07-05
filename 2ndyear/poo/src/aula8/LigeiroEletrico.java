package aula8;

public class LigeiroEletrico extends ligeiro implements VeiculoEletrico {
    private int autonomia;

    public LigeiroEletrico(String matricula, String marca, String modelo, int potencia, int numQuadro,int capacidadeBagageira, int autonomia) {
        super(matricula, marca, modelo, potencia, numQuadro, capacidadeBagageira);
        this.autonomia = autonomia;
    }

    
    @Override
    public int autonomia() {
        return autonomia;
    }

    @Override
    public void carregar(int percentagem) {
        if (percentagem + autonomia >= 100) {
            autonomia = 100;
        } else {
            autonomia += percentagem;
        }
    }

   
   
    public boolean equals(LigeiroEletrico another) {
        if (this == another)
            return true;
        if (!super.equals(another))
            return false;
        if (autonomia != another.autonomia)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "Ligeiro Eletrico com autonomia=" + autonomia;
    }

    
    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }
}
