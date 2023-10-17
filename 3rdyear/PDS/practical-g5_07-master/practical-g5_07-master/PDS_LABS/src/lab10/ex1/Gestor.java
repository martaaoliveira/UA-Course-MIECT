package lab10.ex1;

import java.util.Hashtable;

public class Gestor extends Observer {

    Hashtable<Produto, State> states = new Hashtable<Produto, State>();
    
    public Gestor (String name)
    {
        super(name);
        initializeStates();
    }

    public void getNotifications(Produto prod)
    {
        prod.attach(this);
    }

    public void startAuction (Produto prod, int time)
    {
        getNotifications(prod);
        prod.setState(State.AUCTION);
    }

    public void goSales (Produto prod)
    {
        prod.setState(State.SALES);
    }

    public void goStock (Produto prod)
    {
        prod.setState(State.STOCK);
    }

    public void endAuction (Produto prod)
    {
        if (prod.getAtual() == null)
            goStock(prod);
        else goSales(prod);
        prod.removeAll();
    }

    private void initializeStates()
    {
        for (Produto prod: prodList)
            states.put(prod, prod.getState());
    }

    @Override
    public void update(Produto prod)
    {
        if (!prod.getState().equals(states.get(prod)))
        {
            System.out.println(this.getName() + ": Product " + prod.getID() + " has changed state to " + prod.getState());
            states.put(prod, prod.getState());
        }
    }

}
