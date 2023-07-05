package aula7;

public abstract class alojamento {
    private String codigo;
    private String nome;
    private String local;
    private double precoNoite;
    private boolean disponivel;
    private double avaliacao;

    public alojamento(String codigo, String nome, String local, double precoNoite,
            double avaliacao) {
        if (avaliacao >= 1 && avaliacao <= 5) {
            this.codigo = codigo;
            this.nome = nome;
            this.local = local;
            this.precoNoite = precoNoite;
            this.disponivel = true;
            this.avaliacao = avaliacao;
        }
    }

    public boolean checkIn() {
        if (disponivel) {
            disponivel = false;
            return true;
        }
        return false;
    }

    public boolean checkOut() {
        if (!disponivel) {
            disponivel = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Alojamento com avaliacao=" + avaliacao + ", codigo=" + codigo + ", disponibilidade=" + disponivel + ", local="
                + local + ", nome=" + nome + ", precoNoite=" + precoNoite + "";
    }
    
    public String getCodigo() {
        return codigo;
    }

    // public void setCodigo(String codigo) {
    //     this.codigo = codigo;
    // }

    public String getNome() {
        return nome;
    }

    // public void setNome(String nome) {
    //     this.nome = nome;
    // }

    public String getLocal() {
        return local;
    }

    // public void setLocal(String local) {
    //     this.local = local;
    // }

    public double getPrecoNoite() {
        return precoNoite;
    }

    // public void setPrecoNoite(double precoNoite) {
    //     this.precoNoite = precoNoite;
    // }

    public boolean isDisponivel() {
        return disponivel;
    }

    // public void setDisponivel(boolean disponivel) {
    //     this.disponivel = disponivel;
    // }

    public double getAvaliacao() {
        return avaliacao;
    }

    // public void setAvaliacao(double avaliacao) {
    //     this.avaliacao = avaliacao;
    // }
}

