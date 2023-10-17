package lab08.ex2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Company {

	public static User user;
	private List<Employee> emps = new ArrayList<>();

	private SocialSecurity ss ;
	private Insurance ins ;
	private Parking park ;


	public void admitEmployee(Person p, double salary) {
		Employee e = new Employee(p, salary);
		emps.add(e);
		ss=new SocialSecurity();
		ins=new Insurance();
		park=new Parking();
		Services facade = new Services(ss, ins, park);
		facade.setAvg(avgSalary());
		facade.RegisterEmployee(e);
	}
	
	public void paySalaries(int month) {
		for (Employee e : emps) {
			BankAccount ba = e.getBankAccount();
			ba.deposit(e.getSalary());
		}
	}
	
	public List<Employee> employees() {
		return Collections.unmodifiableList(emps);
	}

	private double avgSalary(){
		double total=0;
		double c=0;
		for (Employee e : emps){
			total+=e.getSalary();
			c++;
		}
		return total/c;
	}
}