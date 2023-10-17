package lab10.ex3;

public class Main {
    //implement a main method to test your code

    public static void main(String[] args) {
        CentralBankMediator centralBank = new CentralBank();
        Bank bank1 = new Bank("Banco 1", 250, centralBank);
        Bank bank2 = new Bank("Banco 2", 1000, centralBank);
        Bank bank3 = new Bank("Banco 3", 1000, centralBank);

        bank1.transfer(bank2, 500);
        bank2.transfer(bank3, 250);
        bank3.transfer(bank1, 500);
        bank1.transfer(bank2, 100);
    }
}
