package lab02.T1;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Processes options similar to getopt().</p>
 * <p>Due to the lack of a proper options parsing library, I've decided to do it myself.</p>
 * <p>It's not foolproof, but it works.</p>
 * <p>This class is better documented to test the javadoc generation and because I expect to reuse it a lot.</p>
 * 
 * <h4>What it can do:</h4>
 *  <ul>
 *  <li>parses options regardless of order</li>
 *  <li>if an order is mandatory, checks if it's present</li>
 *  <li>if an option has an argument, checks if the argument is present (doesn't treat other options as arguments)</li>
 *  <li>allows to get an option, its argument, or whether it was chosen</li>
 *  <li>supports long options (untested)
 * </ul>
 * <h4> What it can't do:</h4>
 * <ul>
 * <li>doesn't check if an option is repeated</li>
 * <li>requires options to be separated by spaces (doesn't work with -abc)</li>
 * </ul>
 * <h4>Requires:</h4>
 * <ul>
 * <li>Option.java</li>
 * <li>LongOption.java (if long options are used)</li>
 * <li>OptionsException.java</li>
 * </ul>
 */
public class OptionsParser {
    List<Option> options=new ArrayList<>();
    /**
     * Parses the options.
    * @param opts format: "a:!b:c!" (and and c are mandatory, a and b have arguments)
    */
    public OptionsParser(String opts){
        char option;
        boolean isMandatory;
        boolean hasArgument;
        for(int i=0;i<opts.length();i++){
            isMandatory=false;
            hasArgument=false;
            option=opts.charAt(i);
            if(i+1<opts.length() && opts.charAt(i+1)==':'){
                hasArgument=true;
                i++;
            }
            if(i+1<opts.length() && opts.charAt(i+1)=='!'){
                isMandatory=true;
                i++;
            }
            options.add(new Option(option, hasArgument, isMandatory));
        }
    }

    /**
     * Parses both long and short options.
     * @param short_opts format: "a:!b:c!" (and and c are mandatory, a and b have arguments)
     * @param long_opts format: "[long1:!,long2:,long3!]" (long1 and long3 are mandatory, long 1 and long2 have arguments)
     */
    public OptionsParser(String short_opts,String[] long_opts){
        this(short_opts);
        boolean isMandatory;
        boolean hasArgument;
        for(String long_opt:long_opts){
            isMandatory=false;
            hasArgument=false;
            if(long_opt.endsWith(":")){
                hasArgument=true;
                long_opt=long_opt.substring(0,long_opt.length()-1);
            }else if(long_opt.endsWith(":!")){
                isMandatory=true;
                hasArgument=true;
                long_opt=long_opt.substring(0,long_opt.length()-2);
            }else if(long_opt.endsWith("!")){
                isMandatory=true;
                long_opt=long_opt.substring(0,long_opt.length()-1);
            }
        }
    }

    /**
     * Reads the arguments.
     */
    public void parse(String[] args) throws OptionsException{
        Option op;
        for(int i=0;i<args.length;i++){
            if((op=getOption(args[i]))==null){
                break;
            }else{
                if(op.hasArgument()){
                    if(i+1<args.length && !args[i+1].startsWith("-") && !args[i+1].startsWith("--")){
                        op.setArgument(args[i+1]);
                        i++;
                    }else{
                        throw new OptionsException("Option "+args[i]+" requires an argument");
                    }
                }
                op.select();
            }
        }
        verifyMandatory();
    }

    /**
     * Returns an option's argument.
     * @return argument (String) ("" if it doesn't have one)
     * @throws OptionsException if the option doesn't exist or has no argument
     * @param option option (String)
     */
    public String getArgument(String option) throws OptionsException{
        Option op=getOption(option);
        if(op==null){
            throw new OptionsException("Option "+option+" doesn't exist");
        }else{
            if(op.hasArgument()){
                return op.getArgument();
            }else{
                throw new OptionsException("Option "+option+" doesn't have an argument");
            }
        }
    }

    /**
     * Verifies if an option was chosen.
     * @return true if the option was chosen, false otherwise
     * @throws OptionsException if the option doesn't exist
     * @param option option (String)
     */
    public boolean isChosen(String option) throws OptionsException{
        Option op=getOption(option);
        if(op==null){
            throw new OptionsException("Option "+option+" doesn't exist");
        }else{
            return op.isChosen();
        }
    }

    /**
     * Verifies if all mandatory options were chosen (because there's non-option data after the options).
     */
    public void verifyMandatory(){
        for(Option o:options){
            if(o.isMandatory() && !o.isChosen()){
                throw new OptionsException("Mandatory option "+o.getName()+" was not chosen");
            }
        }
    }
    /**
     * Returns an option from the list.
     * @param option option (String)
     * @return The option with the given name, or null if it doesn't exist
     */
    private Option getOption(String name){
        for(Option op: options){
            if(op.equals(name)){
                return op;
            }
        }
        return null;
    }
}
