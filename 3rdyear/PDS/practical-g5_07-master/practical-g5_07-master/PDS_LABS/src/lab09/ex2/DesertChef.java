package lab09.ex2;

public class DesertChef extends Handler{
    @Override
    public void handleRequest(String request){
        if(request.equals("Strawberry ice cream and waffles dessert")){
            System.out.println(" Starting to cook "+request+ "Out in 10 minutes");
        }else{
            System.out.println("DesertChef:  I can't cook that! ");
            super.handleRequest(request);
        }
    }
}
