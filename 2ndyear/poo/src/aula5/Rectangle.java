package aula5;

public class Rectangle {
    private double comprimento;
	private double altura;

    public Rectangle(double comprimento, double altura){
		assert altura>0 && comprimento>0 : "os comprimentos do retangulo tem de ser positivos"; 
        this.altura=altura;
        this.comprimento=comprimento;
    }

    public double area() {
		return comprimento * altura;
	}
    public double perimetro() {
		return comprimento*2 + altura*2;
	}
	public String toString() {
		return "Retangulo com lados: " + this.comprimento + " : " + this.altura;
	}

	public boolean equals(Rectangle b) {
		return this.comprimento == b.getComprimento()  && this.altura ==  b.getAltura();
	}

	public double getComprimento() {
		return this.comprimento;
	}

	public double getAltura() {
		return this.altura;
	}


}
