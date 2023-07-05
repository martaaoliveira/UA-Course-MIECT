package aula8;

public class taxi extends ligeiro {
    private int numLicense;
    public taxi(String matricula, String marca, String modelo, int potencia, int nrQuadro,int capacidadeBagageira,int numLicense){
        super(matricula,marca,modelo,potencia,nrQuadro,capacidadeBagageira);
        this.numLicense=numLicense;

    }

    public int getnumLicense(){
        return numLicense;
    }

    public void setnumLicense(int numLicense){
        this.numLicense=numLicense;
    }

    public boolean equals(taxi other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (numLicense != other.numLicense)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " Taxi com licensa nr: =" + numLicense;
    }

}
