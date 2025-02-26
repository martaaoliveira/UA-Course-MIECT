package clientSide.entities;
import clientSide.stubs.*;

/**
 * Contestant class represents the contestant life cycle
 */

public class Contestant  extends Thread  {
    /**
     * Contestant identification
     */
    private int contestantId;
    /**
     * Contestant strength
     */
    private int strength;
    
    /**
     * Contestant state
     */
    private int contestantState;
    /**
     * Reference to the playground
     */
    private final PlaygroundStub playgroundStub;
    /**
     * Reference to the bench
     */
    private final BenchStub benchStub;
    /**
     * Reference to the referee site
     */
    private final RefereeSiteStub refereeSiteStub;
    /**
     * Flag to indicate if the contestant is playing
     */
    private boolean playing;
    /**
     * Team identification
     */
    private int teamID;



    /**
     * Contestant constructor
     * @param name thread name
     * @param contestantId identification of the contestant
     * @param teamId    identification of the team
     * @param strength  strength of the contestant
     * @param playgroundStub reference of the playground stub
     * @param benchStub reference of the bench stub
     * @param refereeSiteStub   reference of the referee site stub
     */

    public Contestant(String name, int contestantId, int teamId, int strength, PlaygroundStub playgroundStub,BenchStub benchStub,RefereeSiteStub refereeSiteStub)  {
        super(name);
        this.contestantId = contestantId;
        contestantState = ContestantState.SEAT_AT_THE_BENCH;
        this.strength = strength;
        this.teamID = teamId;
        this.playgroundStub = playgroundStub;
        this.benchStub = benchStub;
        this.playing=false;
        this.refereeSiteStub=refereeSiteStub;
    }

    /**
     * Set contestant identification
     * @param id identification of the contestant
     */
    public void setcontestantId (int id)
    {
        contestantId = id;
    }
    
    /**
     * Get contestant ID
     * @return contestantId return the identification of the contestant
     */
    public int getcontestantId ()
    {
       return contestantId;
    }
    /**
     * Get team ID
     * @return teamID returns the identification of the team
     */
    public int getTeamID ()
    {
       return this.teamID;
    }


    /**
     * Set if that player is going to play
     * @param playing boolean flag that indicates if the player is going to be playing
     */
    public void setplaying(boolean playing){
        this.playing=playing;
    }
    /**
     * Get if the player is going to play
     * @return boolean flag that indicates if the player is going to playing
     */
    public boolean getplaying(){
        return this.playing;
    }


    /**
     * Set contestant state
     * @param state state of the contestant
     */
    public void setContestantState (int state)
    {
        contestantState = state;
    }
 
    /**
     * Get contestant state
     * @return contestantState returns state of the contestant
     */
    public int getContestantState ()
    {
       return contestantState;
    }
    

    /**
     * Get the strength of the contestant
     * @return strength of the contestant
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Set the strength of the contestant
     * @param strength defines the strength of the contestant
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }
    
    /**
     * Contestant pulls the rope
     */
    public  void pullRope() {

        try
        {   
        Thread.sleep ((long) (1 + 100 * Math.random ()));
        }
        catch (InterruptedException e) {
        }

    }

    /**
     * life cycle of the contestant.
     */
    @Override
    public void run() {
        benchStub.seatDown();
        while(!refereeSiteStub.getEndOfMatch()){
            benchStub.followCoachAdvice();
            playgroundStub.getReady();
            pullRope();  
            playgroundStub.amDone();
            benchStub.seatDown();
           
        }
    }

    
}
