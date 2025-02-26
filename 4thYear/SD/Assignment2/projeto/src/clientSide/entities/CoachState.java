package clientSide.entities;

/**
 * Definition of the internal states of the Coach during his life cycle.
*/


public final class CoachState {
    /**
     * Coach is waiting for the referee command to call a trial
     */
    public static final int WAIT_FOR_REFEREE_COMMAND = 0;
    /**
     * Coach is assembling his team
     */
    public static final int ASSEMBLE_TEAM = 1;
    /**
     * Coach is watching the trial
     */
    public static final int WATCH_TRIAL = 2;

    /**
     * It can not be instantiated.
     */
    private CoachState() {
    }
}
