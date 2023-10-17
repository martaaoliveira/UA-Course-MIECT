package lab05.ex2;

public class Cake {
    private Shape shape = Shape.Circle; //forma default
    private String cakeLayer;
    private int numCakeLayers;
    private Cream midLayerCream;
    private Cream topLayerCream;
    private Topping topping;
    private String message;

    public Cake(int numCakeLayers, String message){
        this.numCakeLayers=numCakeLayers;
        this.message=message;
    }
    public Cake(int numCakeLayers, String message, Shape shape){
        this.numCakeLayers=numCakeLayers;
        this.message=message;
        this.shape=shape;
    }
    public void addLayer(){ //add one layer
        numCakeLayers++;
    }
    public void rmLayer(){  //remove one layer
        numCakeLayers--;
    }

    public void addCreamLayer(Cream cream){
        midLayerCream=cream;
    }
    public void addTopLayer(Cream cream){
        topLayerCream=cream;
    }

    public void addTopping(Topping topping){
        this.topping=topping;
    }
    //setters
    public void setCakeLayer(String layer){
        cakeLayer=layer;
    }
    public void setNumCakeLayers(int numCakeLayers){ //set num of layers to specific number
        this.numCakeLayers=numCakeLayers;
    }
    public void setShape(Shape shape){ //set cake shape to specic shape
        this.shape=shape;
    }
    public void setMessage(String m){ //set cake message
        this.message=m;
    }

    @Override
    public String toString(){
        if(numCakeLayers==1)return cakeLayer+" cake with "+numCakeLayers+" layers, topped with " +topLayerCream+" cream and "+topping+". Message says: \""+message+"\".";
        return cakeLayer+" cake with "+numCakeLayers+" layers and "+midLayerCream+" cream, topped with " +topLayerCream+" cream and "+topping+". Message says: \""+message+"\".";
    }
}
