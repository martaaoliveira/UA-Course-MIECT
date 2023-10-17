package lab06.ex1;

import java.util.*;

public class Database 
{
    private Vector<Employee> employees;     // Store the employees

    public Database()
    {
        employees = new Vector<>();
    }

    public boolean addEmployee (Employee employee)
    {
        // Code to add employee
        for (Employee emp : employees) {
            if (emp.equals(employee)) {
                System.err.println("ERROR! Employee is already registered!");
                return false;
            }
        }
        this.employees.add(employee);
        return true;

    }

    public void deleteEmployee (long emp_num)
    {
        // Code to delete employee
        for (Employee emp : employees) {
            if (emp.getEmpNum() == emp_num) {
                this.employees.remove(emp);
                break;
            }
        }
    }

    public Employee[] getAllEmployees()
    {
        // Code to retrieve collection
        Employee[] array = new Employee[employees.size()];
        return employees.toArray(array);
    }

}
