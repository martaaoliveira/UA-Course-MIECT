package lab09.ex3;
import java.util.Collection;
// Concrete Command
class RemoveCommand<T> implements Command<T>{
    private Collection<T> col;
    private T lastElement;

    public RemoveCommand(Collection<T> col) { 
        this.col = col; 
    }

    public boolean execute(T element) { 
        boolean res = this.col.remove(element);
        if(res){
            this.lastElement = element;
        }
        return res; 
    }

    public boolean undo(){
        
            boolean res = this.col.add(lastElement);
           if(!res){
                System.out.println("Nothing to undo");
                return false;
           }
            return res;
    }
    
}