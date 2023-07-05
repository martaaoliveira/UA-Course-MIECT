package aula8;

public class viatura implements KmPercorridosInterface {
    private String matricula,marca,modelo;

    private int potencia,kmPercorridos,ultimoTrajeto;

    public viatura(String matricula, String marca, String modelo, int potencia){
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.potencia = potencia;
        this.kmPercorridos = 0;
        this.ultimoTrajeto = 0;

    }

    public int ultimoTrajeto(){
        return this.ultimoTrajeto;
    }

    public int distanciaTotal() {
        return this.kmPercorridos;
    }

    public void trajeto(int km){
        this.kmPercorridos +=km;
        this.ultimoTrajeto = km;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }


    public String getMarca() {
        return marca;
    }

  

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPotencia() {
        return potencia;
    }

  

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }


    public int compareTo(viatura v){
        return this.potencia - v.getPotencia();
    }

    @Override
    public String toString() {
        return "Viatura com kmPercorridos=" + kmPercorridos + ", marca=" + marca + ", matricula=" + matricula + ", modelo="
                + modelo + ", potencia=" + potencia + ", ultimoTrajeto=" + ultimoTrajeto;
    }

    public boolean equals(viatura other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (kmPercorridos != other.kmPercorridos)
            return false;
        if (marca == null) {
            if (other.marca != null)
                return false;
        } else if (!marca.equals(other.marca))
            return false;
        if (matricula == null) {
            if (other.matricula != null)
                return false;
        } else if (!matricula.equals(other.matricula))
            return false;
        if (modelo == null) {
            if (other.modelo != null)
                return false;
        } else if (!modelo.equals(other.modelo))
            return false;
        if (potencia != other.potencia)
            return false;
        if (ultimoTrajeto != other.ultimoTrajeto)
            return false;
        return true;
    }

    


}
