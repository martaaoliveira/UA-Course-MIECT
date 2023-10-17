package lab10.ex1;

public class ex1 {

    public static void main(String[] args){
        Produto p1 = new Produto("Bounty", 100);
        Produto p2 = new Produto("M&Ms", 200);
        Produto p3 = new Produto("Twix", 300);
        Produto p4 = new Produto("Snickers", 400);
        Produto p5 = new Produto("KitKat", 500);

        Cliente c1 = new Cliente("Cliente 1");
        Cliente c2 = new Cliente("Cliente 2");
        Cliente c3 = new Cliente("Cliente 3");
        Gestor g1 = new Gestor("Lara Matos");
        
        c1.bid(p1, 150); //not on auction error msg expected
        g1.startAuction(p1, 200); //start auction for all products
        g1.startAuction(p2, 200);
        g1.startAuction(p3, 200);
        g1.startAuction(p4, 200);
        g1.startAuction(p5, 200);
        c1.bid(p1,150);         //c1 actually bids on p1
        c2.bid(p1, 200);        //c2 bids on ALL EXCEPT P4
        c2.bid(p2, 250);
        c2.bid(p3, 350);
        c2.bid(p5, 550);
        c3.bid(p5, 550);        //c3 bids the same ammount on p5 as c2
        g1.endAuction(p1);            // End all products auction
        g1.endAuction(p2);
        g1.endAuction(p3);
        g1.endAuction(p4);
        g1.endAuction(p5);
        c1.bid(p1, 1);         //c1 tries to bid on p1 and p4
        c1.bid(p4, 1);

    }
    
}
