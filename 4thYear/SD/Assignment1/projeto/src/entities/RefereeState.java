package entities;

/**
 * Definition of the internal states of the referee during his life cycle.
 */
public final class RefereeState {
    /**
     * The referee starts the match.
     */
    public static final int START_OF_THE_MATCH = 0;
    /**
     * The referee starts an new game.
     */
    public static final int START_OF_A_GAME = 1;
    /**
     * The referee is waiting for all the teams to be ready to play
     */
    public static final int TEAMS_READY = 2;
    /**
     *The referee is waiting for trial conclusion, that is, to all players finish playing.
     */
    public static final int WAIT_FOR_TRIAL_CONCLUSION = 3;
    /**
     * The referee ends the game.
     */
    public static final int END_OF_A_GAME = 4; 
    /**
     *  The referee ends the match.
     */
    public static final int END_OF_THE_MATCH = 5;

    /**
     * Internal state that wait for all the players to be seated before announcing new game/trial.
    */
    public static final int WAIT_ALL_SAB=6;

    /**
     * It can not be instantiated.
     */
    private RefereeState() {
    }
}
