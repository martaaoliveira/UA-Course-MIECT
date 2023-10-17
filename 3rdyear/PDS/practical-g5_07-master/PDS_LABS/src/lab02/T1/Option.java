package lab02.T1;

import java.util.Objects;

public class Option {
    private char name;
    protected boolean hasArgument;
    protected boolean isMandatory;
    protected boolean isChosen;
    protected String argument;

    public Option(char name, boolean hasArgument, boolean isMandatory){
        this.name=name;
        this.hasArgument=hasArgument;
        this.isMandatory=isMandatory;
        this.isChosen=false;
        this.argument=null;
    }

    /**
     * For long options
     */
    protected Option(boolean hasArgument, boolean isMandatory){
        this.hasArgument=hasArgument;
        this.isMandatory=isMandatory;
        this.isChosen=false;
        this.argument=null;
    }

    public String getArgument(){
        return this.argument;
    }

    public void setArgument(String argument){
        if(hasArgument){
            this.argument=argument;
            isChosen=true;
        }
    }

    public char getName(){
        return this.name;
    }

    /**
     * Marks as chosen (for if it hasn't got an argument)
     */
    public void select(){
        isChosen=true;
    }

    public boolean hasArgument(){
        return this.hasArgument;
    }

    public boolean isMandatory(){
        return this.isMandatory;
    }

    public boolean isChosen(){
        return this.isChosen;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof String){
            return ( ((String)(o)).equals("-"+name) || ( ((String)(o)).charAt(0)==name ) );
        }else if(o instanceof Option){
            return ((Option)(o)).getName()==name;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return Objects.hash(name,hasArgument,isMandatory);
    }

    @Override
    public String toString(){
        return "Option: "+name+", hasArgument: "+hasArgument+", isMandatory: "+isMandatory+", isChosen: "+isChosen+", argument: "+argument;
    }
}
