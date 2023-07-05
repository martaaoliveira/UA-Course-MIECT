package aula7;

public class triangulo extends forma {
    private double l1;
	private double l2;
	private double l3;

    public triangulo (String cor, double l1, double l2, double l3){
		super(cor);
        assert l1>0 && l2>0 && l3>0 : "os comprimentos do triangulo tem de ser positivos"; 
		assert l1<l2+l3 : "Desiguldade triangular nao verificada: um dos lados é inferior a soma dos outros dois ";
        this.l1=l1;
        this.l2=l2;
        this.l3=l3;
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

    public boolean equals(triangulo b) {
		return l1 == b.getL1() && l2 == b.getL2() && l3 == b.getL3();
	}
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        triangulo to_compare = (triangulo) obj;
        if (l1!= to_compare.l1)
            return false;
        if (l2!= to_compare.l2)
            return false;
        if (l3!= to_compare.l3)
            return false;
        if (getCor() == null) {
            if (to_compare.getCor() != null)
                return false;
        } else if (!getCor().equals(to_compare.getCor()))
            return false;
        return true;
    }

}
