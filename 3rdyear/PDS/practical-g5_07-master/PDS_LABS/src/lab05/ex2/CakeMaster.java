package lab05.ex2;

public class CakeMaster{ //Director
    private CakeBuilder cbuilder;

    public CakeMaster(){}

    public void setCakeBuilder(CakeBuilder cbuilder){
        this.cbuilder=cbuilder;
    }

    public void createCake(String m){
        createCake(Shape.Circle, 1, m);

    }
    public void createCake(int nlayers){
        createCake(Shape.Circle, nlayers, "default");

    }
    public void createCake(Shape shape){
        createCake(shape, 1, "default");

    }
    public void createCake(Shape shape, String m){
        createCake(shape, 1, m);

    }
    public void createCake(int nlayers, String m){
        createCake(Shape.Circle, nlayers,m);

    }
    public void createCake(Shape shape,int nlayers){
        createCake(shape, nlayers, "default");
    }
    public void createCake(Shape shape, int nlayers, String m){
        //inizializar o bolo
        cbuilder.CreateCake();
        cbuilder.addMessage(m);       //adicionar mensagens 
        for(int i=1; i<nlayers; i++){ //adicionar as layers pedidas
            cbuilder.addCakeLayer();
        }
        cbuilder.setCakeShape(shape); //adicionar a forma do bolo
        //criar o bolo
        if(nlayers!=1)cbuilder.addCreamLayer();
        cbuilder.addTopLayer();
        cbuilder.addTopping();
    }
    public Cake getCake(){
        return cbuilder.getCake();
    }


    
}
