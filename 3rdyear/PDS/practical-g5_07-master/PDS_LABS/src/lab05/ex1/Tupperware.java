package lab05.ex1;

public class Tupperware extends ContainerFactory {

    public Tupperware(Portion p) {super(p);}

    @Override
    public String toString() {
        return "Tupperware with portion = " + super.portion.toString();
    }
    
}
