package entities;
import java.util.ArrayList;
import java.util.List;

import sharedRegion.Bench;
import sharedRegion.playground;
import sharedRegion.refereeSite;
/**
 * Contestant class represents the contestant life cycle
 */

public class Contestant  extends Thread implements Comparable<Contestant> {
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
    private final playground playground;
    /**
     * Reference to the bench
     */
    private final Bench bench;
    /**
     * Reference to the referee site
     */
    private final refereeSite refereeSite;
    /**
     * Flag to indicate if the contestant is playing
     */
    private boolean playing;
    /**
     * Team identification
     */
    private int teamID;

    private  Contestant[] allContestants;

    
    /**
     * Contestant constructor
     * @param name
     * @param contestantId
     * @param teamId
     * @param strength
     * @param teamID
     * @param playground
     * @param bench
     * @param refereeSite
     */

    public Contestant(String name, int contestantId, int teamID, int strength,playground playground,Bench bench,refereeSite refereeSite,Contestant[] allContestants)  {
        super(name);
        this.contestantId = contestantId;
        contestantState = ContestantState.SEAT_AT_THE_BENCH;
        this.strength = strength;
        this.teamID = teamID;
        this.playground = playground;
        this.bench = bench;
        this.playing=false;
        this.refereeSite=refereeSite;
        this.allContestants = allContestants; // Store the array locally

        
    }

    /**
     * Set contestant identification
     * @param id
     */
    public void setcontestantId (int id)
    {
        contestantId = id;
    }
    
    /**
     * Get contestant ID
     * @return contestantId
     */
    public int getcontestantId ()
    {
       return contestantId;
    }
    /**
     * Get team ID
     * @return teamID
     */
    public int getTeamID ()
    {
       return this.teamID;
    }


    /**
     * Set if that player is going to play
     * @param playing
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
     * @param state
     */
    public void setContestantState (int state)
    {
        contestantState = state;
    }
 
    /**
     * Get contestant state
     * @return contestantState
     */
    public int getContestantState ()
    {
       return contestantState;
    }
    /**
     * Compare the strength of two contestants
     */
    @Override
    public int compareTo(Contestant other) {
        return Integer.compare(this.strength, other.strength);
    }
    

    /**
     * Get the strength of the contestant
     * @return strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Set the strength of the contestant
     * @param strength
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Retorna o concorrente correspondente ao ID fornecido.
     * @param id O ID do concorrente a ser buscado.
     * @return O concorrente correspondente ao ID fornecido, ou null se não for encontrado.
     */
    public Contestant getContestantById(int id) {
        if (allContestants != null) {
            for (Contestant contestant : allContestants) {
                if (contestant.getcontestantId() == id) {
                    return contestant;
                }
            }
        }
        return null; // Se não encontrou o concorrente com o ID fornecido
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
        bench.seatDown();
        while(!refereeSite.getEndOfMatch()){
            bench.followCoachAdvice();
            playground.getReady();
            pullRope();  
            playground.amDone();
            bench.seatDown();
           
        }
    }

    
}