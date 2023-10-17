package lab06.ex1;

public class Adapter {

    private Database database;
    private Registos registos;

    public Adapter() 
    {
        this.database = new Database();
        this.registos = new Registos();
    }

    /* Um método para adicionar um empregado. */
    public boolean addEmployee(Employee emp)
    {
        return this.database.addEmployee(emp);
    }

    public void addEmpregado(Empregado emp)
    {
        this.registos.insere(emp);
    }

    /*  Um método para remover um empregado, dado o número de funcionário. */
    public void removeEmployee(int numEmp) {
        this.database.deleteEmployee(numEmp);
        this.registos.remove(numEmp);
    }


    /* Um método para verificar se um empregado existe na empresa, dado o número do empregado. */
    public boolean EmployeeExists(int numEmp)
    {
        if (this.registos.isEmpregado(numEmp))
            System.out.println("Empregado pertence à companhia");
        else {
            for (Employee emp : this.database.getAllEmployees()) {
                if (emp.getEmpNum() == numEmp){
                    System.out.println("Employee belongs to company!");
                    return true;
                }
            }
        }
        System.out.println("O empregado não existe!");
        return false;
    }

    /* Um método para imprimir os registos de todos os funcionários. */
    public void printAllEmployees() 
    {
        for (Employee empDB : this.database.getAllEmployees()) {
            System.out.println(empDB.getName());
        }
        for (Empregado empRG : this.registos.listaDeEmpregados()) {
            System.out.println(empRG.nome() + " " + empRG.apelido());
        }
    }
    
}
