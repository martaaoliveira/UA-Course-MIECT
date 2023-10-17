package lab07.ex2;
//Remove vogais. 
public class VowelFilter extends Decorator {
    public VowelFilter(ReaderInterface reader) {
        super(reader);
    }
    @Override
    public String next() {
        return getVowels(reader.next());
        
    }

    private String getVowels(String texto) {
        return texto.replaceAll("[aeiouAEIOU]", "");
    }
    
    // private boolean isVowel(char letra) {
    //     return "aeiouAEIOU".indexOf(letra) != -1;
    // }
}
    

