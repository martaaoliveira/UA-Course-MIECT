package lab02.T1;
public class LongOption extends Option {
    private String name;
    public LongOption(String name, boolean hasArgument, boolean isMandatory){
        super(hasArgument, isMandatory);
        this.name=name;
    }

    public String getLongName(){
        return this.name;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof String){
            return ( ((String)(o)).equals("--s"+name) || ((String)(o)).equals(name) );
        }else if(o instanceof LongOption){
            return ((LongOption) o).name.equals(this.name);
        }else{
            return false;
        }
    }
}
