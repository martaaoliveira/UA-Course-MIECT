package lab10.ex1;

import java.util.ArrayList;

public abstract class Observer {
    protected ArrayList<Produto> prodList;
    private String name;    

    public Observer (String name) {
        this.name = name;
        prodList = new ArrayList<>();
    }

    public abstract void update (Produto prod);

    public void getNotifications(Produto prod) {
        prod.attach(this);
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return name;
    }

}
