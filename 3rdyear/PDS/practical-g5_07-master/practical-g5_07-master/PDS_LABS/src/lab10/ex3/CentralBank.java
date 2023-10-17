package lab10.ex3;

public class CentralBank implements CentralBankMediator {

    @Override
    public void transfer(Bank fromBank, Bank toBank, double amount){
        if(fromBank.getBalance() >= amount) {
            fromBank.withdraw(amount);
            toBank.deposit(amount);
            System.out.println("Transação concluída: " + amount + " transferidos de " + fromBank.getName() + " para " + toBank.getName());
        } else {
            System.out.println("Transação falhou: Saldo insuficiente no " + fromBank.getName());
        }
    }
}