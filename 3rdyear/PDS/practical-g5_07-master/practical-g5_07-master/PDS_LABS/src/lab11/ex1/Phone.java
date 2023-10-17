package lab11.ex1;

public class Phone {
    private String cpu;
    private double price;
    private int memory;
    private String camera;
    private String type;

    public Phone(String cpu, double price, int memory, String camera,String type){
        this.cpu = cpu;
        this.price = price;
        this.memory = memory;
        this.camera = camera;
        this.type = type;
    
    }
    public String getType() {
        return type;
    }
    public String getCpu(){
        return cpu;
    }

    public double getPrice(){
        return price;
    }

    public int getMemory(){
        return memory;
    }

    public String getCamera(){
        return camera;
    }

    @Override
    public String toString ()  {
        return "Phone{" +
        "cpu='" + cpu + '\'' +
        ", price=" + price +
        ", memory=" + memory +
        ", camera='" + camera + '\'' +
        ", type='" + type + '\'' +
        '}';
}

    

}
    
