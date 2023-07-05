package aula8;

public class Peixe extends alimento {
    private tipoPeixe tipo;

    public Peixe(tipoPeixe tipo, double proteinas, double calorias, double peso) {
        super(proteinas, calorias, peso);
        this.tipo = tipo;
    }

    public tipoPeixe getTipo() {
        return tipo;
    }

    public void setTipo(tipoPeixe tipo) {
        this.tipo = tipo;
    }


    @Override
    public String toString() {
        return "Peixe " + tipo + super.toString();
    }



    public boolean equals(Peixe fish) {
        if (this == fish)
            return true;
        if (fish == null)
            return false;
        if (tipo != fish.tipo)
            return false;
        return true;
    }


}
