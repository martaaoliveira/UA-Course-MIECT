package aula8;

public class Cereal extends alimentoVegetariano {
    private String nome;
    public Cereal(String nome, double proteinas, double calorias, double peso) {
        super(proteinas, calorias, peso);
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cereal " + nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public boolean equals(Cereal ce) {
        if (this == ce)
            return true;
        if (!super.equals(ce))
            return false;
        if (nome == null) {
            if (ce.nome != null)
                return false;
        } else if (!nome.equals(ce.nome))
            return false;
        return true;
    }

   
}
