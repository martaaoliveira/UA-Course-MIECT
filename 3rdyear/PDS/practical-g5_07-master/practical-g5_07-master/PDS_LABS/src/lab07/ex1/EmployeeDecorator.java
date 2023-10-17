package lab07.ex1;

import java.util.*;

public abstract class EmployeeDecorator implements EmployeeInterface 
{
    protected EmployeeInterface emp;

    public EmployeeDecorator(EmployeeInterface emp) {
        this.emp = emp;
    }

    public void start(Date date) {emp.start(date);}
    public void terminate(Date date) {emp.terminate(date);}
    public void work() {emp.work();}
    public String name() {return emp.name();}

}
