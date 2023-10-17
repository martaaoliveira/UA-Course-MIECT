package lab02.T1;
/**
 * A word soup's word and its associated information.
 */
public class Word {
    private String str;
    private int row;
    private int col;
    private Direction direction;
    private boolean isFound;

    /**
     * Create a new word.
     * @param word The word itself.
     */
    public Word(String word){
        this.str=word;
        this.col=-1;
        this.row=-1;
        this.direction=Direction.NONE;
        this.isFound=false;
    }

    /**
     * @return the word itself. 
     */
    public String getWord() {
        return str;
    }

    public int length(){
        return str.length();
    }

    public boolean isFound(){
        return isFound;
    }

    /**
     * Sets the word's position and direction and marks it as found.
     * @param row the row
     * @param col the column
     * @param direction the direction
     */
    public void setFound(int row, int col, Direction direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.isFound = true;
    }


    @Override
    public String toString() {
        return String.format("%-15s %-6d %d,%d   %s%n", str, str.length(), row+1, col+1, direction);
    }

    /**
     * Returns the letter at the given index.
     * @param n the letter's index
     * @return char
     * @throws IndexOutOfBoundsException if n is out of bounds
     */
    public char charAt(int n) throws IndexOutOfBoundsException {
        return str.charAt(n);
    }
}
