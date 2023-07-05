package aula5;

public class Triangle {
    private double l1;
	private double l2;
	private double l3;
    public Triangle(double l1, double l2, double l3){
		assert l1>0 && l2>0 && l3>0 : "os comprimentos do triangulo tem de ser positivos"; 
		assert l1<l2+l3 : "Desiguldade triangular nao verificada: um dos lados é inferior a soma dos outros dois ";
        this.l1=l1;
        this.l2=l2;
        this.l3=l3;
    }
    public double perimetro() {
		return l1 + l2 + l3;
	}

	public double area() {
		double s = this.perimetro()/2;
		return Math.sqrt(s * (s-this.l1) * (s-this.l2) * (s-this.l3));
	}

	public String toString() {
		return "Triangulo com lados: " + this.l1 + " : " + this.l2 + " : " + this.l3;
	}

	public boolean equals(Triangle b) {
		return this.l1 == b.getL1() && this.l2 == b.getL2() && this.l3 == b.getL3();
	}

	public double getL1() {
		return this.l1;
	}
	public double getL2() {
		return this.l2;
	}
	public double getL3() {
		return this.l3;
	} 
}
