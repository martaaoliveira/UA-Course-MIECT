package lab02.T1;

import java.util.List;
import java.util.Random;

/**
 * A matrix for the word soup
 * Can be used both for the soup and the solution.
 * 
 */
public class SoupMatrix {
    private char[][] matrix;
    private boolean[][] isOccupied;
    Random r=new Random();
    int size;

    /**
     * Creates a new matrix and sets default values.
     * @param size the matrix's size
     */
    public SoupMatrix(int size){
        this.matrix=new char[size][size];
        this.isOccupied=new boolean[size][size];
        this.size=size;
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                this.matrix[row][col]='.';
                this.isOccupied[row][col]=false;
            }
        }
    }
    /**
     * @param row
     * @param col
     * @return the letter at the given position (default is '.')
     * @throws IndexOutOfBoundsException
     */
    public char charAt(int row, int col) throws IndexOutOfBoundsException{
        return this.matrix[row][col];
    }

    /**
     * Sets the letter at the given position.
     * @param row
     * @param col
     * @param letter
     * @throws outOfBoundsException
     */
    public void setLetter(int row, int col, char letter) throws IndexOutOfBoundsException{
        this.matrix[row][col]=letter;
    }

    public char getLetter(int row, int col) throws IndexOutOfBoundsException{
        return this.matrix[row][col];
    }

    /**
     * Fils the matrix with random letters.
     */
    public void fill(){
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                this.matrix[row][col]=(char)(r.nextInt(26)+'A');
            }
        }
    }

    /**
     * Adds the words randomly.
     * @param words list of words
     */
    public void fill(List<Word> words) throws InvalidWordSoup{
        int row;
        int col;
        Direction dir;
        int attempts;
        for(Word w:words){
            if(w.length()>size){
                throw new InvalidWordSoup("Word "+w.getWord()+" is too long ("+w.length()+").");
            }
            attempts=1;
            while(true){
                if(attempts>1000){
                    throw new InvalidWordSoup("Too many attempts to add word "+w);
                }
                attempts++;
                dir=Direction.values()[r.nextInt(8)];
                row=r.nextInt(size);
                col=r.nextInt(size);
                if(isValid(row,col,dir,w.length(),w)){
                    addWord(row,col,dir,w,w.length());
                    break;
                }
            }
        }
    }
    /**
     * Checks if the given word can be added to the matrix.
     * @param row
     * @param col
     * @param dir direction
     * @param length remaining length of the word
     * @param word the word
     * @return true if the word can be added
     */
    private boolean isValid(int row, int col, Direction dir ,int length, Word word){
        if(length==0){
            return true;
        }else{
            if(row<0 || row>=size || col<0 || col>=size){
                return false;
            }else if(isOccupied[row][col] && matrix[row][col]!=Character.toUpperCase(word.charAt(word.length()-length))){ //so it's possible to overlap, as unlikely as it is
                return false;
            }else{
                switch(dir){
                    case UP:
                        return isValid(row-1,col,dir,length-1,word);
                    case DOWN:
                        return isValid(row+1,col,dir,length-1,word);
                    case LEFT:
                        return isValid(row,col-1,dir,length-1,word);
                    case RIGHT:
                        return isValid(row,col+1,dir,length-1,word);
                    case UPLEFT:
                        return isValid(row-1,col-1,dir,length-1,word);
                    case UPRIGHT:
                        return isValid(row-1,col+1,dir,length-1,word);
                    case DOWNLEFT:
                        return isValid(row+1,col-1,dir,length-1,word);
                    case DOWNRIGHT:
                        return isValid(row+1,col+1,dir,length-1,word);
                    default:
                        return false;
                }
            }
        }
    }

    /**
     * Adds the word to the matrix.
     * @param row
     * @param col
     * @param dir
     * @param word
     * @param length remaining length of the word
     */
    private void addWord(int row, int col, Direction dir, Word word,int length){
        if(length!=0){
            matrix[row][col]=Character.toUpperCase(word.charAt(word.length()-length));
            isOccupied[row][col]=true;
            switch(dir){
                case UP:
                    addWord(row-1,col,dir,word,length-1);
                    break;
                case DOWN:
                    addWord(row+1,col,dir,word,length-1);
                    break;
                case LEFT:
                    addWord(row,col-1,dir,word,length-1);
                    break;
                case RIGHT:
                    addWord(row,col+1,dir,word,length-1);
                    break;
                case UPLEFT:
                    addWord(row-1,col-1,dir,word,length-1);
                    break;
                case UPRIGHT:
                    addWord(row-1,col+1,dir,word,length-1);
                    break;
                case DOWNLEFT:
                    addWord(row+1,col-1,dir,word,length-1);
                    break;
                case DOWNRIGHT:
                    addWord(row+1,col+1,dir,word,length-1);
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                sb.append(matrix[row][col]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * toString but with spaces between the letters to look more like a square
     */
    public String prettyToString() {
        StringBuilder sb=new StringBuilder();
        for(int row=0;row<size;row++){
            for(int col=0;col<size;col++){
                sb.append(matrix[row][col]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
