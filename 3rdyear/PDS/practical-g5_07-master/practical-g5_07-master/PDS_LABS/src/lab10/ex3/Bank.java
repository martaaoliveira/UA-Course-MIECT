package lab10.ex3;

public class Bank {
    private String name;
    private double balance;
    private CentralBankMediator mediator;

    public Bank(String name, double balance, CentralBankMediator mediator){
        this.name = name;
        this.balance = balance;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void transfer(Bank toBank, double amount) {
        mediator.transfer(this, toBank, amount);
    }
}