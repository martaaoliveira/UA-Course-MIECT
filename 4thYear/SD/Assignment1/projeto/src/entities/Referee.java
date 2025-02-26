package entities;

import java.util.List;
import java.util.ArrayList;

import sharedRegion.playground;
import sharedRegion.refereeSite;
import sharedRegion.Bench;

/**
 * Referee class simulates the Referee life cycle.
 */
public class Referee extends Thread {

    /**
     * Referee state.
     */
    private int refereeState;

    /**
     * Position of the rope in the trial.
     */
    private int position;

    /**
     * List of Coach IDs.
     */
    private List<Integer> coachIDs;

    /**
     * List of Contestant IDs.
     */
    private List<Integer> contestantIDs;

    /**
     * Reference to the playground.
     */
    private final playground playground;

    /**
     * Reference to the referee site.
     */
    private final refereeSite refereeSite;

    /**
     * Reference to the bench.
     */
    private final Bench bench;

    private boolean end_match;

    /**
     * Referee constructor.
     * @param name
     * @param refereeState
     * @param playground
     * @param refereeSite
     * @param bench
     */
    public Referee(String name, int refereeState, playground playground, refereeSite refereeSite, Bench bench) {
        super(name);
        this.refereeState = refereeState;
        this.playground = playground;
        this.refereeSite = refereeSite;
        this.bench = bench;
        this.coachIDs = new ArrayList<>();
        this.contestantIDs = new ArrayList<>();
        this.end_match = false;
        this.position = 0;
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
     * @param coachID The coach's ID to add.
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
     * Set the referee state.
     * @param state New state of the referee.
     */
    public void setRefereeState(int state) {
        this.refereeState = state;
    }

    /**
     * Get the referee state.
     * @return The current state of the referee.
     */
    public int getRefereeState() {
        return this.refereeState;
    }
    /**
     * lifecycle of the Referee
     */
    @Override
	public void run() {
        
        do{
            refereeSite.announceNewGame();
            do{
                bench.callTrial();
                playground.startTrial();
                position=playground.assertTrialDecision(); 
            }while(playground.game_done()==false);

            end_match= refereeSite.declareGameWinner(position);

            bench.EndGame(end_match);

        }while(!refereeSite.getEndOfMatch());

        refereeSite.declareMatchWinner();
   
    }

}