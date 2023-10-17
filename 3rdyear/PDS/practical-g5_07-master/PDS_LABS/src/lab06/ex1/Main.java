package lab06.ex1;

import java.util.List;

public class Main {

    public static void main (String[] args) {
        Empregado e1 = new Empregado("Maria", "Marques", 12345, 500);
        Empregado e2 = new Empregado("José", "Silva", 67890, 750);
        Empregado e3 = new Empregado("Bernardo", "Tavares", 54321, 564);
        Empregado e4 = new Empregado("Rodolfo", "Verano", 10293, 324);

        Registos reg = new Registos();
        reg.insere(e1);
        reg.insere(e2);
        reg.insere(e3);
        reg.insere(e4);
        reg.isEmpregado(12345);
        reg.isEmpregado(54321);
        reg.remove(54321);
        reg.isEmpregado(12345);

        List<Empregado> emp = reg.listaDeEmpregados();
        for (Empregado empregado : emp) {
            System.out.println(empregado.nome() + " " + empregado.apelido() + " " + empregado.salario());
        }

        Employee emp1 = new Employee("Ruben Neves", 1, 500);
        Employee emp2 = new Employee("Diogo Jota", 2, 1000);
        Employee emp3 = new Employee("Joao Moutinho", 3, 199);

        Database db = new Database();
        db.addEmployee(emp1);
        db.addEmployee(emp2);
        db.addEmployee(emp3);
        for (Employee e: db.getAllEmployees()) {
            System.out.println(e.getName() + " " + e.getEmpNum() + " " + e.getSalary());
        }

        db.deleteEmployee(2);
        for (Employee e : db.getAllEmployees()) {
            System.out.println(e.getName() + " " + e.getEmpNum() + " " + e.getSalary());
        }

        Adapter adaptee = new Adapter();

        adaptee.printAllEmployees();
        adaptee.addEmployee(emp1);

        Empregado e5 = new Empregado("Jacinto", "Lopes", 1001, 1001.11);
        adaptee.addEmpregado(e5);
        adaptee.removeEmployee(3);
        adaptee.printAllEmployees();
        System.out.println(adaptee.EmployeeExists(e1.codigo()));
        System.out.print(adaptee.EmployeeExists(e4.codigo()));

    }
    
    
}
