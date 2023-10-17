package lab02.T1;

import java.util.List;

/**
 * Solves a word soup.
 */
public class Solver {
    SoupMatrix soup;
    SoupMatrix solution;
    int size;
    List<Word> words;

    /**
     * Creates a new solver.
     * @param soup the word soup that was read
     * @param words the list of words that were read
     * @param size the size of the word soup
     */
    public Solver(SoupMatrix soup, List<Word> words,int size){
        this.soup=soup;
        this.words=words;
        this.size=soup.size;
        this.solution=new SoupMatrix(size);
    }

    /**
     * Solves the word soup.
     * @throws ImpossibleWordSoup if the word soup is impossible to solve
     */
    public void solve() throws InvalidWordSoup{
        //I've interpreted input requirement number 9 as "if a word is contained in another word, remove it without raising an error", which is probably to avoid problems with requirement number 8 (words can't be found twice)
        words=Validator.removeSubstrings(words);
        List<Word> words_copy=words; //so as to keep the original order
        words_copy.sort((w1,w2)->w2.length()-w1.length());
        //for every word, try all positions and directions
        for(Word word:words_copy){
            for(int row=0;row<size;row++){
                for(int col=0;col<size;col++){
                    for(Direction dir:Direction.values()){ 
                        if(dir!=Direction.NONE && search(word,row,col,dir,word.length())){ //if it was found
                            if(word.isFound()){ //if it was found before
                                throw new InvalidWordSoup("Word found twice.");
                            }else{
                                word.setFound(row, col, dir);
                            }
                        }
                    }
                }
            }
            if(!word.isFound()){
                throw new InvalidWordSoup("Word not found("+word.getWord()+").");
            }
        }
    }
    /**
     * Searches for a word in the word soup.
     * @param word
     * @param row row
     * @param col column
     * @param dir direction of the word
     * @param length remaining length of the word
     * @return
     */
    private boolean search(Word word, int row, int col, Direction dir, int length){
        if(length==0){
           return true; 
        }else if(row<0 || row>=size || col<0 || col>=size){
            return false;
        }else if(soup.getLetter(row,col)==word.charAt(word.length()-length)){
            boolean ret=false;
            switch(dir){
                case UP:
                    ret=search(word,row-1,col,dir,length-1);
                    break;
                case DOWN:
                    ret=search(word,row+1,col,dir,length-1);
                    break;
                case LEFT:
                    ret=search(word,row,col-1,dir,length-1);
                    break;
                case RIGHT:
                    ret=search(word,row,col+1,dir,length-1);
                    break;
                case UPLEFT:
                    ret=search(word,row-1,col-1,dir,length-1);
                    break;
                case UPRIGHT:
                    ret=search(word,row-1,col+1,dir,length-1);
                    break;
                case DOWNLEFT:
                    ret=search(word,row+1,col-1,dir,length-1);
                    break;
                case DOWNRIGHT:
                    ret=search(word,row+1,col+1,dir,length-1);
                    break;
                default:
                    return false;
            }
            if(ret){
                solution.setLetter(row, col, word.charAt(word.length()-length));
            }
            return ret;
        }else{
            return false;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(Word word: words){
            sb.append(word.toString());
        }
        sb.append("\n");
        sb.append(solution.prettyToString());
        return sb.toString();
    }
}
