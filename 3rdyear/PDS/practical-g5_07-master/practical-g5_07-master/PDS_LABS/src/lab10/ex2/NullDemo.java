package lab10.ex2;

public class NullDemo {
    public static void main(String[] args) {
    
        Employee emp = EmployeeFactory.getCostumer("Mac");
        Employee emp2 = EmployeeFactory.getCostumer("Janela");
        Employee emp3 = EmployeeFactory.getCostumer("Linux");
        Employee emp4 = EmployeeFactory.getCostumer("Mack");

        System.out.println(emp.getName());
        System.out.println(emp2.getName());
        System.out.println(emp3.getName());
        System.out.println(emp4.getName());
    }

}
