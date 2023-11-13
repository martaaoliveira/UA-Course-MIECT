import java.util.List;

public class Symbol {
    private String name;
    private String type;
  

    public Symbol(String name, String type) {
        this.name = name;
        this.type = type;

    }

    

    public Symbol(String name) {
        this.name = name;
    }


    public String getName() { return name; }
    public String getType() { return type; }

    public void setType(String type) {
        this.type = type;
    }

    

    public String toString(){
        return name;
    }
   
}
