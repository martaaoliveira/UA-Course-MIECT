package clientSide.entities;

import java.util.List;
import java.util.ArrayList;

import clientSide.stubs.*;


/**
 * Referee class simulates the Referee life cycle 
 */

public class Referee extends Thread {
    
    /**
     * Referee state
     */
    private int refereetState;
    /**
     * Position of the rope in the trial
     */
    private int position;   


    /**
     * Reference to the playground
     */
    private final PlaygroundStub playgroundStub;
    /**
     * Reference to the referee site
     */
    private final RefereeSiteStub refereeSiteStub;
    /**
     * Reference to the bench
     */
    private final BenchStub benchStub;

    /**
     * To indicate if the match has concluded or not
     */
    private boolean end_match;


    /**
     * List of Coach IDs.
     */
    private List<Integer> coachIDs;

    /**
     * List of Contestant IDs.
     */
    private List<Integer> contestantIDs;

    /**
     * Referee constructor
     * @param name name of thre thread
     * @param refereetState state of the referee
     * @param playgroundStub reference of the playground stub
     * @param refereeSiteStub reference of the referee site stub
     * @param benchStub reference of the bench stub
     */
    public Referee(String name,int refereetState, PlaygroundStub playgroundStub, RefereeSiteStub refereeSiteStub, BenchStub benchStub) {
        super(name);    
        this.refereetState = refereetState;
        this.playgroundStub = playgroundStub;
        this.refereeSiteStub = refereeSiteStub;
        this.benchStub= benchStub;
        this.coachIDs = new ArrayList<>();
        this.contestantIDs = new ArrayList<>();
        this.end_match=false;
        position = 0;
    }

    
    /**
     * Adds a coach ID to the list of managed coaches.
     * @param coachID The coach's ID to add.
     */
    public void addCoachID(int coachID) {
        this.coachIDs.add(coachID);
    }

    /**
     * Adds a contestant ID to the list.
     * @param contestantID The contestant's ID to add.
     */
    public void addContestantID(int contestantID) {
        this.contestantIDs.add(contestantID);
    }

        /**
     * Adds a coach ID to the list of managed coaches.
     * @param index The coach's ID to add.
     */
    public int getCoachID(int index) {
        if (index >= 0 && index < this.coachIDs.size()) {
            return this.coachIDs.get(index);
        } else {
            // Handle the case when index is out of bounds
            return -1; // Or any other appropriate value or throw an exception
        }
    }
    

    /**
     * returns the contestant ID of the game 
     * @return List contestantID 
     */
    public List<Integer> getContestantID() {
       return this.contestantIDs;
    }

    /**
     * Set the referee state
     * @param state state of the referee
     */
    public void setrefereeState (int state)
    {
        refereetState = state;
    }
    
    /**
     * Get the referee state
     * @return refereetState returns state of the referee
     */
    public int getrefereeState ()
    {
        return refereetState;
    }


    /**
     * lifecycle of the Referee
     */
    @Override
	public void run() {
        
        do{
            refereeSiteStub.announceNewGame();
            do{
                benchStub.callTrial();
                playgroundStub.startTrial();
                position=playgroundStub.assertTrialDecision(); 
            }while(playgroundStub.game_done()==false);

            end_match= refereeSiteStub.declareGameWinner(position);

            benchStub.EndGame(end_match);

        }while(!refereeSiteStub.getEndOfMatch());

        refereeSiteStub.declareMatchWinner();
   
    }

}