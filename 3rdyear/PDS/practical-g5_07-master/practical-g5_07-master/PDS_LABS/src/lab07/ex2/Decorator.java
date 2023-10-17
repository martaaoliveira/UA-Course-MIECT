package lab07.ex2;

public class Decorator implements ReaderInterface {
    protected ReaderInterface reader;
    String a=" ";
    
    Decorator(ReaderInterface reader){
        this.reader=reader;
    }
    @Override
    public boolean hasNext() {
        return true;
    }
    @Override
    public String next() {
        return " ";
    }
}
