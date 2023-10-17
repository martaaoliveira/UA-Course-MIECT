package lab07.ex1;

import java.util.*;

public class Employee implements EmployeeInterface {

    private String name;

    public Employee (String name) {this.name = name;}

    public String name() {return name;}

    @Override
    public void start (Date date)
    {
        System.out.println("\n" + name + " started on " + date.toString());
    }

    @Override
    public void terminate (Date date)
    {
        System.out.println("\n" + name + " terminated on " + date.toString());
    }

    @Override
    public void work()
    {
        System.out.println("\n" + name + " is working!");
    }
    
}
