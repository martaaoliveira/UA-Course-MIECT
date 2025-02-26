package clientSide.entities;

import java.rmi.RemoteException;

import genclass.GenericIO;
import interfaces.BenchInterface;
import interfaces.PlaygroundInterface;
import interfaces.RefereeSiteInterface;
import interfaces.ReturnInt;

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
    private final PlaygroundInterface playgroundStub;
    /**
     * Reference to the bench
     */
    private final BenchInterface benchStub;
    /**
     * Reference to the referee site
     */
    private final RefereeSiteInterface refereeSiteStub;
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

    public Contestant(String name, int contestantId, int teamId, int strength, PlaygroundInterface playgroundStub,BenchInterface benchStub,RefereeSiteInterface refereeSiteStub)  {
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
        seatDown();
        while(!getEndOfMatch()){
            followCoachAdvice();
            getReady();
            pullRope();  
            amDone();
            seatDown();
           
        }
    }

    /**
     * Operation seat down
     * the players sit down
     */
    private void seatDown() {
        ReturnInt ret=null;
        try
        { 
            ret=benchStub.seatDown(contestantId,strength);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Contestant " + contestantId + " remote exception on seatDown: " + e.getMessage ());
        System.exit (1);
        }
        contestantState=ret.getIntStateVal();
        this.strength=ret.getIntVal();
    }


    /**
     * operation get end of match
     * checks if the match ended
     * @return true if the match has ended
     */
    private boolean getEndOfMatch() {
        boolean ret=false;
            try
            { 
            ret=refereeSiteStub.getEndOfMatch();
            }
            catch (RemoteException e)
            { GenericIO.writelnString ("Contestant " + contestantId + " remote exception on seatDown: " + e.getMessage ());
            System.exit (1);
            }
        return ret;
    }

    /**
     * Operation follow coach advice
     * contestants follows the coach advice
     *
     */
    private void followCoachAdvice() {
        int ret=-1;
        try
        { 
        ret=benchStub.followCoachAdvice(contestantId,strength);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Contestant " + contestantId + " remote exception on followCoachAdvice: " + e.getMessage ());
        System.exit (1);
        }
        contestantState=ret;
    }

    /**
     * operation get ready
     * contestants get ready to play
     */
    private void getReady() {
        int ret=-1;
        try
        { 
        ret=playgroundStub.getReady(contestantId, strength);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Contestant " + contestantId + " remote exception on getReady: " + e.getMessage ());
        System.exit (1);
        }
        contestantState=ret;
    }

    /**
     * operation am done
     * the contestant says when he's finished playing
     */
    private void amDone() {
        //int ret=-1;
        try
        { 
            playgroundStub.amDone();
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Contestant " + contestantId + " remote exception on amDone: " + e.getMessage ());
        System.exit (1);
        }
        //contestantState=ret;
    }
        


    
}
