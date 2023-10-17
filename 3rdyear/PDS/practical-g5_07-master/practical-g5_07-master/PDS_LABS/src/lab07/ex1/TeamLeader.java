package lab07.ex1;

public class TeamLeader extends EmployeeDecorator 
{
    public TeamLeader(EmployeeInterface emp) {
        super(emp);
    }

    @Override
    public void work ()
    {
        emp.work();
        System.out.print(" as TeamLeader");
    }

    public void plan ()
    {
        System.out.println("\n" + emp.name() + " planned!");
    }

}
