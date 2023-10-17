package lab09.ex3;


class RemoteControl<T> {
    private Command<T> command;
    
    public void setCommand(Command command) {
        this.command = command;
    }
    public boolean execute(T element) {
        return command.execute(element);
    }

    public boolean undo(){
        return command.undo();
    }
}








