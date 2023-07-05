package aula5;

public class Circle {
    private double radius;

    public Circle(double radius){
        assert radius>0 : "Valor do raio é maior que 0";
        this.radius=radius;
    }
    
	public double getRadius() {
		return this.radius;
	}
    public double area(){
        return Math.PI * this.radius * this.radius;
    }
    public double perimetro(){
        return 2 * Math.PI * this.radius;
    }
    public String toString(){
        return "Circle with radius: " + this.radius;
    }
    public boolean equals(Circle another){
        return this.radius == another.getRadius();
    }
}
