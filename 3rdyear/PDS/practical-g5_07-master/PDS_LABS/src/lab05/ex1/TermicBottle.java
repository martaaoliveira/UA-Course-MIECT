package lab05.ex1;

public class TermicBottle extends ContainerFactory {

    public TermicBottle(Portion p) {super(p);}

    @Override
    public String toString() {
        return "TermicBottle with portion = " + super.portion.toString();
    }
    
}
