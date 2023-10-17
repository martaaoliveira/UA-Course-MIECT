package lab10.ex2;

public class Programmer extends Employee{
    //implement methods from employee
    public Programmer(String name) { 
        this.name = name; 
    }

    @Override public String getName() {
        return name;
    }
    
}
