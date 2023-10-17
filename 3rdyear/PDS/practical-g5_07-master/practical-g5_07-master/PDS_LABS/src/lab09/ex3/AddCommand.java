package lab09.ex3;
import java.util.Collection;

// Concrete Command
class AddCommand<T> implements Command<T>{
    private Collection<T> col;
    private T lastElement;


    public AddCommand(Collection<T> col) { 
        this.col = col; 
    }

    public boolean execute(T element) { 
        boolean res = this.col.add(element);
        if(res){
            this.lastElement = element;
        }
        return res; 
    }

    public boolean undo(){
        boolean res = this.col.remove(lastElement);
        if(!res){
            System.out.println("Theres nothing to undo");
            return false;
        }else return res; 

       
    }
}