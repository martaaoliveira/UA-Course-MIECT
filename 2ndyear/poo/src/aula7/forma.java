package aula7;

public abstract class forma {
    
    private String cor;
    
    public forma(String cor) {
        this.cor = cor;
    }
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return "Forma [cor=" + cor + "] ";
    }
    public abstract double area();
    public abstract double perimetro();
    //public abstract String cor();
    
}


