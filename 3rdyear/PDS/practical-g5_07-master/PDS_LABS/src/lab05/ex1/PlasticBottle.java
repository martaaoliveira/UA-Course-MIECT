package lab05.ex1;

public class PlasticBottle extends ContainerFactory {

    public PlasticBottle (Portion port) {super(port);}

    @Override
    public String toString() {
        return "PlasticBottle with portion = " + super.portion.toString();
    }
    
}
