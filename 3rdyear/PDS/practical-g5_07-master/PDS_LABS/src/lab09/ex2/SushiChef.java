package lab09.ex2;



public class SushiChef extends Handler {
 //One restaurant has various chefs each one has a different responsibility. The chef treats the plate if it is a sushi plate else sends the request to the next chef
 @Override  
 public void handleRequest(String request){
        if(request.equals("Sushi nigiri and sashimi")){
            System.out.println(" Starting to cook "+request+ "Out in 14 minutes");
        }else{
            System.out.println("SushiChef:  I can't cook that! ");
            if (sucessor != null) 
            super.handleRequest(request);
        }
    }
}
