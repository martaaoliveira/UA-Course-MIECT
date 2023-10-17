package lab09.ex3;
import java.util.ArrayList;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
    
        Collection<String> col = new ArrayList<>();
        RemoteControl<String> remoteControl = new RemoteControl<>();
        Command<String> remove = new RemoveCommand<>(col);
        
        System.out.println("Add Command");
        remoteControl.setCommand(new AddCommand<>(col));
        remoteControl.execute("Hello");
        System.out.println(col);
        remoteControl.execute("World");
        System.out.println(col);
       
        System.out.println("AddCommand: UNDO");
        remoteControl.undo();
        System.out.println(col);
        

        remoteControl.setCommand(remove);   
        System.out.println("Remove Command");
        remove.execute("Hello");
        System.out.println(col);
        remoteControl.undo();
        System.out.println(col);
        remoteControl.undo();
        System.out.println(col);
        
    }
}
