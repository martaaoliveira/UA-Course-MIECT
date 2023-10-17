package lab10.ex3;

public interface CentralBankMediator {
    void transfer(Bank fromBank, Bank toBank, double amount);
}
