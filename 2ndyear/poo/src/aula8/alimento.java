package aula8;

public abstract class alimento {
    private double proteinas;
    private double calorias;
    private double peso;

    public alimento(double proteinas, double calorias, double peso) {
        this.proteinas = proteinas;
        this.calorias = calorias;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return String.format(", Proteinas %3.1f, calorias %3.1f, Peso %.1f", proteinas, calorias, peso);
    }

    public double getProteinas() {
        return proteinas;
    }

    public void setProteinas(double proteinas) {
        this.proteinas = proteinas;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

   
    public boolean equals(alimento al) {
        if (this == al)
            return true;
        if (al == null)
            return false;
        if (calorias != al.calorias)
            return false;
        if (peso != al.peso)
            return false;
        if (proteinas != (al.proteinas))
            return false;
        return true;
    }


}
