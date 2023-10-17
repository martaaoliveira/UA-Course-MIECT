package lab05.ex3;

public class Place {
    private String local;
    private float x;
    private float y;

    public Place(String local, float x, float y){
        this.local = local;
        this.x = x;
        this.y = y;
    }

    //getters
    public String getLocal() {
        return local;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
