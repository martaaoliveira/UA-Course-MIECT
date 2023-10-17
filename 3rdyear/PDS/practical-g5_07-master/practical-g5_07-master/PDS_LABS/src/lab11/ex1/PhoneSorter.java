package lab11.ex1;
import java.util.List;

public class PhoneSorter {
    private SortingStrategy strategy;

    public PhoneSorter(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Phone> sortPhones(List<Phone> phones) {
        return strategy.sort(phones);
    }
}
