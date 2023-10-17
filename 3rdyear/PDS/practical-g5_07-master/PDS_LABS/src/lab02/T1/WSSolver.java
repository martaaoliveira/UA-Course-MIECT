package lab02.T1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class WSSolver {
    public static void main(String[] args){
        String input;
        String output;
        FileReader fr;
        BufferedReader br;
        FileWriter fw;
        int lineNum=0;
        int size;
        String line;
        SoupMatrix soup;
        Solver s;
        List<Word> words=new ArrayList<Word>();

        //check arguments
        if(args.length==1){
            input=args[0];
            if(!input.endsWith(".txt")){ //if there's ever support for more extensions, it's just a matter of adding to the regex
                System.err.println("Error: invalid file extension.");
                System.exit(1);
            }
            output=input.split("\\.")[0]+"_result."+input.split("\\.")[1];
        }else if(args.length==2){
            input=args[0];
            output=args[1];
            if(!input.endsWith(".txt") || !output.endsWith(".txt")){ //if there's ever support for more extensions, it's just a matter of adding to the regex
                System.err.println("Error: invalid file extension.");
                System.exit(1);
            }
        }else{
            System.err.println("Usage: java SolverMain <input> <output>");
            return;
        }
        

        try{
            fr=new FileReader(input);
            br=new BufferedReader(fr);
            fw=new FileWriter(output);
            
            //read lines
            line=br.readLine();
            if(line==null){
                System.err.println("Error: empty file.");
                System.exit(1);
            }
            size=line.toCharArray().length;
            if(size<=0 || size>40){
                System.err.println("Error: invalid size("+size+").");
                System.exit(1);
            }
            soup=new SoupMatrix(size);
            if(!Validator.isValidLine(line,size)){
                System.err.println("Error: invalid line ("+line+").");
                System.exit(1);
            }else{
                for(int i=0;i<size;i++){
                    soup.setLetter(lineNum,i,line.charAt(i));
                }
            }
            lineNum++;
            for(;lineNum<size;lineNum++){
                line=br.readLine();
                if(line==null){
                    System.err.println("Error: invalid file.");
                    System.exit(1);
                }
                if(!Validator.isValidLine(line,size)){
                    System.err.println("Error: invalid line ("+line+").");
                    System.exit(1);
                }else{
                    for(int i=0;i<size;i++){
                        soup.setLetter(lineNum,i,line.charAt(i));
                    }
                }
            }
            //read words
            while((line=br.readLine())!=null){
                String[] splitLine=line.split(" |,|;");
                for(String word:splitLine){
                    if(!Validator.isValidWord(word)){
                        System.err.println("Error: invalid word.");
                        System.exit(1);
                    }else{
                        words.add(new Word(word.toUpperCase()));
                    }
                }
            }
            
            s=new Solver(soup,words,size);
            s.solve();
            fw.write(s.toString());

            //close
            br.close();
            fr.close();
            fw.close();
        }catch(Exception e){
            System.err.println("Error: "+e.getMessage());
            return;
        }
    }
}
