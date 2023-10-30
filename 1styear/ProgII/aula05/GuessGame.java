
import static java.lang.System.*;

public class GuessGame {

  private int randomNum, numAttempts=0,min, max;
  int [] attempt = new int[0];
  
  
 

  public GuessGame(int min, int max) {
    assert max>min : "Intervalo de valores não é valido ";
    randomNum=(int)(Math.random()*(max-min)+min);
    this.min=min;
    this.max=max;
  }

  public int min() {
    return min;
  }

  public int max() {
    return max;
  }

  public boolean validAttempt(int n) {
    return (n<=max && n>=min);
  }

  public boolean finished() {
   for(int i=0;i<attempt.length;i++){
   if (randomNum==attempt[i]) return true;
}
return false;
  }

  public boolean attemptIsHigher() {
    if(attempt[attempt.length-1]>randomNum) return true; 
    else return false;
  }

  public boolean attemptIsLower() {
    if(attempt[attempt.length-1]<randomNum) return true;
		else return false;
  }

  public void play(int n) {
	  
	  
	 int [] a2 = new int [attempt.length+1]; //porque +1?
	  
	  
    assert n<=max && n>=min : "Número introduzido não pertence ao intervalo.";
    if(finished()) return;
    
    for(int i=0;i<attempt.length;i++){
			a2[i]=attempt[i];
		}
		a2[attempt.length]=n;
		attempt=a2;
		numAttempts++;
    
  }

  public int numAttempts() {
    return this.numAttempts; 
  }

  /**
   * Simple tests of the GuessGame class.
   * This method tests the functionality of the GuessGame class.
   * It should be used by the programmer to test and debug the class.
   * It is not meant to be called in client programs.
   *
   * To run from the command line, use:
   *   java -ea GuessGame
   */
  public static void main(String[] args) {
    requireEA();
    out.println("Starting tests.");
    GuessGame game = new GuessGame(1, 10);
    // initial tests:
    assert !game.finished() : "game should not be finished yet";
    assert game.min() == 1;
    assert game.max() == 10;
    assert game.numAttempts() == 0 : "there should be no attempts yet";
    for(int i = -10; i <= 20; i++) {
      assert game.validAttempt(i) == (i >= 1 && i <= 10);
    }
    // trying all wrong answers:
    int nplay = 0; // how may times play was called
    for(int n = 1; n <= 10; n++) {
      if (n != game.randomNum) {
        game.play(n); // make a wrong guess
        nplay++;
        assert game.numAttempts() == nplay;
        assert !game.finished() : "game should not be finished yet";
        assert (n < game.randomNum) == game.attemptIsLower();
        assert (n > game.randomNum) == game.attemptIsHigher();
      }
    }
    
    // make the right guess:
    game.play(game.randomNum);
    nplay++;
    assert game.finished() : "game should be finished now";
    assert game.numAttempts() == nplay;
    out.println("No error detected!");
  }

  /** Check if program is being run with -ea, exit if not. */
  static void requireEA() {
    boolean ea = false;
    assert ea = true; // assert with a side-effect, on purpose!
    if (!ea) {
      err.println("This program must be run with -ea!");
      exit(1);
    }
  }

}

