package lab07.ex2;
//Separa em palavras
public class TermFilter extends Decorator {
    private String termo;   
    public TermFilter(ReaderInterface reader) {
        super(reader);
    }

    @Override
    public String next() {
        String a = super.next();
        a = a.replaceAll("[^a-zA-Z0-9]", " ");
        return a;
    }
}
