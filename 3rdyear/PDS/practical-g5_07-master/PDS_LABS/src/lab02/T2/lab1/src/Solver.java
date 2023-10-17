package lab02.T2.lab1.src;
public class Solver {
    private int x;
    private int y;
    private Directions direction;

    //constructor
    public Solver(int x, int y, Directions direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }


    @Override
    public String toString() {
        return String.format("%2d,%2d   %s", x,y,direction);
    }


    //getter e setters
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public Directions getDirection() {
        return direction;
    }
    public void setDirection(Directions direction) {
        this.direction = direction;
    }


}
