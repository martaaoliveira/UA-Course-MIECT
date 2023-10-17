package lab05.ex1;

public class PlasticBag extends ContainerFactory {

    public PlasticBag(Portion p) {super(p);}

    @Override
    public String toString() {
        return "PlasticBag with portion = " + super.portion.toString();
    }
    
}
