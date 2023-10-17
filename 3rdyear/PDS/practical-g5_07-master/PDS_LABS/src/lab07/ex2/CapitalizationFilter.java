package lab07.ex2;
//puts in capital letters the first and last letter of the text and the rest of it in lower case
public class CapitalizationFilter extends Decorator {
   
    
    public CapitalizationFilter(ReaderInterface reader) {
        super(reader);
    }
    
    @Override
    public String next(){
        return capitalize(reader.next());
    }
    
    
    public String capitalize(String texto) {
        String s = "";
        s=s+Character.toUpperCase(texto.charAt(0));
        for (int i=1; i<texto.length()-1;i++){
            s=s+Character.toLowerCase(texto.charAt(i));
        }
        s=s+Character.toUpperCase(texto.charAt(texto.length()-1));
        return s;
    }
    
}
