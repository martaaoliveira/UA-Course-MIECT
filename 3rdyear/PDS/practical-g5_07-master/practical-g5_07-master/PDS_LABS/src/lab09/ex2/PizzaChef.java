package lab09.ex2;

public class PizzaChef extends Handler 
{   
    @Override
    public void handleRequest(String request){
    if(request.equals("Pasta Carbonara")){
        System.out.println(" Starting to cook "+request+ "Out in 10 minutes");
    }else{
        System.out.println("PizzaChef:  I can't cook that! ");
        super.handleRequest(request);
    }
} 
}
