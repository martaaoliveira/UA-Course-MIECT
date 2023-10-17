package lab07.ex2;
import java.text.Normalizer;

//Remove acentuação e pontuação. Inclui os métodos:
public class NormalizationFilter extends Decorator {
    
    public NormalizationFilter(ReaderInterface reader) {
        super(reader);
    }
    @Override
    public String next(){
        String a = Normalizer.normalize(reader.next(), Normalizer.Form.NFD);
        return a.replaceAll("[^\\p{ASCII}]", "");
    }
    
}
