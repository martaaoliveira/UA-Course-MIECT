package lab02.T1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class WSGenerator {
    public static void main(String[] args){
        //since I couldn't find any proper options parsing library, I'll just assume they have to be in that order
        String usage="Usage: java WSGenerator -i <input> -s <size> (-o <output>)";
        String input="";
        String output="";
        int size=0;
        FileReader fr;
        BufferedReader br;
        FileWriter fw;
        SoupMatrix soup;
        List<Word> words=new ArrayList<Word>();
        String line;
        

        OptionsParser oPar=new OptionsParser("i:!s:!o:");
        try{
            oPar.parse(args);
        }catch(OptionsException e){
            System.err.println(usage);
            throw e;
        }
        input=oPar.getArgument("-i");
        if(!input.endsWith(".txt")){
            String extension;
            try{
                extension=input.split("\\.")[1];
            }catch(ArrayIndexOutOfBoundsException e){
                extension="";
            }
            System.err.println("Error: Unsupported file format (."+extension+").");
            System.exit(1);
        }

        size=Integer.parseInt(oPar.getArgument("-s"));
        if(size<=0 || size>40){
            System.err.println("Error: invalid size ("+size+").");
            System.exit(1);
        }

        if(oPar.isChosen("-o")){
            output=oPar.getArgument("-o");
        }else{
            output=input.split("\\.")[0]+"_result."+input.split("\\.")[1];
            //since I only had one practical class, I has to assume that, like in 1.1, no output option meant that a default name is to be used
        }
        if(!output.endsWith(".txt")){
            String extension;
            try{
                extension=input.split("\\.")[1];
            }catch(ArrayIndexOutOfBoundsException e){
                extension="";
            }
            System.err.println("Error: Unsupported file format (."+extension+").");
            System.exit(1);
        }

        try{
            //opening
            fr=new FileReader(input);
            br=new BufferedReader(fr);
            fw=new FileWriter(output);

            //read lines
            while((line=br.readLine())!=null){
                String[] splitLine=line.split(" |,|;");
                for(String word:splitLine){
                    if(!Validator.isValidWord(word)){
                        System.err.println("Error: invalid word ("+word+").");
                        System.exit(1);
                    }else{
                        words.add(new Word(word));
                    }
                }
            }
            soup=new SoupMatrix(size);
            soup.fill();
            soup.fill(words);
            fw.write(soup.toString());
            for(Word word:words){
                fw.append(word.getWord()+" \n");
            }
            //closing
            fw.close();
            br.close();
            fr.close();
        }catch(Exception e){
            System.err.println("Error: "+e.getMessage());
            System.exit(1);
        }
    }
}
