package lab09.ex2;

public class BurgerChef extends Handler {

    @Override
    public void handleRequest(String request){
        if(request.equals("Veggie Burger")){
            System.out.println(" Starting to cook "+request+ "Out in 10 minutes");
        }else{
            System.out.println("BurgerChef:  I can't cook that! ");
            super.handleRequest(request);
        }
    }   
    
}
