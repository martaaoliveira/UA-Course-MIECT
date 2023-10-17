package lab08.ex2;

//o funcionario impede a empresa de ter acesso aos metodos withdraw e balance 
public class Proxy implements BankAccount{
    
    private BankAccount bankAccount;
    
    public Proxy(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
    
    @Override
    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }
   
    @Override
    public boolean withdraw(double amount) {
        //if its owner, withdraw, else return false
        if(Company.user.equals(User.OWNER)){
            return bankAccount.withdraw(amount);
        }
        else return false;
    }

    @Override
    public double balance() {
        //if its owner, return balance, else return 0
        if(Company.user.equals(User.OWNER)){
            return bankAccount.balance();
        }
        else return 0;
    }    
}
