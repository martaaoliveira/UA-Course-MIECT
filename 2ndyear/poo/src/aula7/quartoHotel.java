package aula7;

public class quartoHotel extends alojamento {
    private String tipo;
    
    public quartoHotel(String codigo, String nome, String local, double precoNoite,double avaliacao, String tipo) {
        super(codigo, nome, local, precoNoite, avaliacao);
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString() + "Quarto Hotel tipo=" + tipo + "";
    }

    public String getTipo() {
        return tipo;
    }

    // public void setTipo(String tipo) {
    //     this.tipo = tipo;
    // }

}
