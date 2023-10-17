package lab09.ex2;



public class PastaChef extends Handler {
    //implement the pastchef class here
    @Override
    public void handleRequest(String request){
        if(request.equals("Pasta Carbonara")){
            System.out.println(" Starting to cook "+request+ "Out in 10 minutes");
        }else{
            System.out.println("PastaChef:  I can't cook that! ");
            super.handleRequest(request);
        }
    }    

}
