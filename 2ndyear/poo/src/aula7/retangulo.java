package aula7;

public class retangulo extends forma {
    private final double comprimento;
	private final double largura;
   

    public retangulo(String cor,double comprimento, double largura) {
		super(cor);
        this.comprimento=comprimento;
        this.largura=largura;
	}
 
    public double comprimento() {
		return comprimento;
	}
	
	public double largura() {
		return largura;
	}

    public double area() {
		return comprimento * largura;
	}

	public double perimetro() {
		return 2 * comprimento + 2 * largura;
	}
    public boolean equals(retangulo b) {
		return comprimento == b.comprimento()  && largura ==  b.largura();
	}

    public String toString() {
		return "Retangulo com lados: " + this.comprimento + " : " + this.largura;
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        retangulo to_compare = (retangulo) obj;
        if (largura != (to_compare.largura))
            return false;
        if (comprimento != to_compare.comprimento)
            return false;
        if (getCor() == null) {
            if (to_compare.getCor() != null)
                return false;
        } else if (!getCor().equals(to_compare.getCor()))
            return false;
        return true;
    }


}
