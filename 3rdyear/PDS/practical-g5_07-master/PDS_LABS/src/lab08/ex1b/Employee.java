package lab08.ex1b;

class Employee  {

private double salary;
private Person p;

	public Employee(Person p, double s) {
		this.p=p;
		salary=s;
	}

	public double getSalary() {
		return salary;
	}

	public BankAccount getBankAccount() {
        return p.getBankAccount();
    }
}