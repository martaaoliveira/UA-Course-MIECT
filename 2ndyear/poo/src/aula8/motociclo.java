package aula8;

public class motociclo extends viatura {
    private String tipo;

    public motociclo(String matricula, String marca, String modelo, int potencia,String tipo){
        super(matricula, marca, modelo, potencia);
        this.tipo=tipo;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString() + " Motociclo tipo=" + tipo;
    }



    public boolean equals(motociclo other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        return true;
    }


}
