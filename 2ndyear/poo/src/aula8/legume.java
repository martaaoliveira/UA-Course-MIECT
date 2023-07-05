package aula8;

public class legume extends alimentoVegetariano {
    private String nome;
    public legume(String nome, double proteinas, double calorias, double peso) {
        super(proteinas, calorias, peso);
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Legume [nome=" + nome + "]";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    

    public boolean equals(legume le) {
        if (this == le)
            return true;
        if (!super.equals(le))
            return false;
        if (nome == null) {
            if (le.nome != null)
                return false;
        } else if (!nome.equals(le.nome))
            return false;
        return true;
    }

    

}
