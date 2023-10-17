package lab10.ex1;

import java.util.ArrayList;
import java.util.List;

public class Produto {

    private static int counter = 0;
    private int id;
    private String description;
    private double price;
    private State state;

    private List<Observer> observers = new ArrayList<>();
    private Observer atual;

    public Produto (String description, double price) 
    {
        this.id = counter++;
        this.description = description;
        this.price = price;
        state = State.STOCK;
    }

    public void attach(Observer obs)
    {
        if (observers.contains(obs));
        else observers.add(obs);
    }

    public void remove (Observer obs)
    {
        if (observers.contains(obs))
            observers.remove(obs);
    }

    public void removeAll ()
    {
        observers.clear();
    }

    private void notifyObservers ()
    {
        for (Observer obs: observers)
            obs.update(this);
    }

    public boolean bid (Observer obs, double prod_price)
    {
        attach(obs);
        if (prod_price > price && state.equals(State.AUCTION))
        {
            atual = obs;
            setPrice(prod_price);
            return true;
        }
        return false;
    }

    public int getID() {return id;}
    public double getPrice() {return price;}
    public State getState() {return state;}
    public Observer getAtual() {return atual;}

    public void setPrice(double prod_price)
    {
        this.price = prod_price;
        notifyObservers();
    }

    public void setState(State st)
    {
        this.state = st;
        notifyObservers();
    }

}
