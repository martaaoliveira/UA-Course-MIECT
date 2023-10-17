package lab09.ex2;

public abstract class Handler {
    Handler sucessor=null;
    public Handler setSucessor(Handler sucessor){
        this.sucessor = sucessor;
        return this;
    }

    public void handleRequest(String request){
        if (sucessor != null) 
            sucessor.handleRequest(request);
        else
            System.out.println("No one can cook that!");
    }
    
}
