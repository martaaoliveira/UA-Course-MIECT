package aula8;

public class pesadoMercadorias extends viatura {
    private int nrQuadro,peso,cargaMaxima;

    public pesadoMercadorias(String matricula, String marca, String modelo, int potencia, int nrQuadro, int peso,int cargaMaxima) {
        super(matricula, marca, modelo, potencia);
        this.nrQuadro = nrQuadro;
        this.peso = peso;
        this.cargaMaxima=cargaMaxima;
    }

    @Override
    public String toString() {
        return super.toString() + "Pesado com numQuadro=" + nrQuadro + ", peso=" + peso + "e carga maxima"+ cargaMaxima;
    }


    public int getCargaMaxima() {
        return cargaMaxima;
    }

    public void setCargaMaxima(int cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
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

   
    public boolean equals(pesadoMercadorias other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (nrQuadro != other.nrQuadro)
            return false;
        if (peso != other.peso)
            return false;
        if(cargaMaxima!=other.cargaMaxima)
            return false;
        return true;
    }



}
