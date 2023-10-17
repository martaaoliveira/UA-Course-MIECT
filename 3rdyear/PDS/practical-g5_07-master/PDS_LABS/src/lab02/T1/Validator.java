package lab02.T1;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection of useful validation methods.
 */
public class Validator {
    private Validator(){}

    /**
     * Checks if the given string is a valid word soup line.
     * @param line the line to be checked
     * @param size the size of the word soup
     * @return true if the line is valid, false otherwise
     */
    public static boolean isValidLine(String line,int size){
        if(line.isEmpty() || line.length()!=size){
            return false;
        }else if(line.matches("[A-Z]+")){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isValidWord(String word){
        if(word.isEmpty() || word.length()<3){
            return false;
        }else if(word.matches("[A-Za-z]*[a-z][A-Za-z]*")){ //check if the word contains at least one lowercase letter
            return true;
        }else{
            return false;
        }
    }

    /**
     * When a word is a substring of another word, the shorter word is discarded
     */
    public static List<Word> removeSubstrings(List<Word> words){
        List<Word> result=new ArrayList<>();
        for(Word word:words){
            boolean isSubstring=false;
            for(Word other:words){
                if(word!=other && other.getWord().contains(word.getWord())){
                    isSubstring=true;
                    break;
                }
            }
            if(!isSubstring){
                result.add(word);
            }
        }
        return result;
    }
}
