package lab10.ex1;

public class Cliente extends Observer {

    public Cliente (String name) {super(name);}

    public void checkProducts()
    {
        for (Produto prod: prodList)
            if(prod.getState().equals(State.AUCTION))
                System.out.println(prod.toString());
    }

    public void bid(Produto prod, double price)
    {
        if (prod.bid(this, price));
        else System.out.println("Bid from " + this.getName() + " was not high enough or product is not for auction");
    }
    
    public void update (Produto prod)
    {
        System.out.println(this.getName() + " : " + prod.toString());
    }
    
}
