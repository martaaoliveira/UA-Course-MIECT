package aula7;

public class circulo extends forma {
    private final double raio;

    public circulo(String cor,double raio){
        super(cor);
        assert raio>0 : "Valor do raio é maior que 0";
        this.raio=raio;
    }
    public double raio(){
        return raio;
    }
    public double area() {
		return Math.PI * raio * raio; 
	}
    
    public double perimetro() {
		return 2 * Math.PI * raio;
	}

    public String toString(){
        return "Circle with radius: " + this.raio;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        circulo to_compare = (circulo) obj;
        if (raio != to_compare.raio)
            return false;
        if (getCor() == null) {
            if (to_compare.getCor() != null)
                return false;
        } else if (!getCor().equals(to_compare.getCor()))
            return false;
        return true;
    }
}
