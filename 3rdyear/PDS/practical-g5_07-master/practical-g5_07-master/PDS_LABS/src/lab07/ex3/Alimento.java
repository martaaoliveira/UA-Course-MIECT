package lab07.ex3;

public abstract class Alimento {
    protected static StringBuffer buffer = new StringBuffer();
    public abstract double getWeight();
    public abstract void draw();
}