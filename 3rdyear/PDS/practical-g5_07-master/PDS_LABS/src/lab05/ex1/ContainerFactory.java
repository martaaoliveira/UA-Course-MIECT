package lab05.ex1;

public class ContainerFactory {

    protected Portion portion;

    public ContainerFactory (Portion p) {this.portion = p;}

    public static ContainerFactory create (Portion port)
    {
        if(port.getTemperature() == Temperature.Cold) {
            if (port.getState() == State.Liquid) 
                return new PlasticBottle(port);              
            else
                return new PlasticBag(port);
        }
        else {
            if (port.getState() == State.Liquid)
                return new TermicBottle(port);
            else    
                return new Tupperware(port);
        }
    }
    
}
