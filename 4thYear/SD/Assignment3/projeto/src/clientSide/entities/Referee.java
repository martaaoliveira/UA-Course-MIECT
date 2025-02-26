package clientSide.entities;

import java.util.List;

import genclass.GenericIO;
import interfaces.BenchInterface;
import interfaces.PlaygroundInterface;
import interfaces.RefereeSiteInterface;
import interfaces.ReturnBoolean;

import java.rmi.RemoteException;
import java.util.ArrayList;



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
    private final PlaygroundInterface playgroundStub;
    /**
     * Reference to the referee site
     */
    private final RefereeSiteInterface refereeSiteStub;
    /**
     * Reference to the bench
     */
    private final BenchInterface benchStub;

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
    public Referee(String name,int refereetState, PlaygroundInterface playgroundStub, RefereeSiteInterface refereeSiteStub, BenchInterface benchStub) {
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
        int position=0;
        do{
            announceNewGame();
            do{
                callTrial();
                startTrial();
                position=assertTrialDecision(); 
            }while(game_done()==false);

            end_match= declareGameWinner(position);

            EndGame(end_match);

        }while(!getEndOfMatch());

        declareMatchWinner();
   
    }

    /**
     * operation announce new game
     * referee announces that a game is going to take place
     */
    private void announceNewGame() {
    int ret=-1;
    try
    { 
        ret=refereeSiteStub.announceNewGame();
    }
    catch (RemoteException e)
    { GenericIO.writelnString ("Referee  remote exception on announceNewGame: " + e.getMessage ());
    System.exit (1);
    }
    refereetState=ret;
    }

    /**
     * operation call trial
     * inform that a trial is taking place
     */
    private void callTrial() {
        int ret=-1;
        try
        { 
            ret=benchStub.callTrial();
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Referee  remote exception on callTrial: " + e.getMessage ());
        System.exit (1);
        }
        refereetState=ret;
    }

    /**
     * operation start trial
     * inform that a trial is going to start
     */
    private void startTrial() {
        int ret=-1;
        try
        { 
            ret=playgroundStub.startTrial();
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Referee  remote exception on startTrial: " + e.getMessage ());
        System.exit (1);
        }
        refereetState=ret;

    }

    /**
     * operation assert trial decision
     * referee tells the decision of the trial
     * @return rope position
     */
    private int assertTrialDecision() {
        int ret=-1;
        try
        { 
            ret=playgroundStub.assertTrialDecision();
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Referee  remote exception on assertTrialDecision: " + e.getMessage ());
        System.exit (1);
        }

        return ret;
    
    }

    /**
     * operation game done
     * Tells if a game is done or not
     * @return true if the game has ended
     */
    private boolean game_done() {
        boolean ret=false;
        try
        { 
            ret=playgroundStub.game_done();
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Referee  remote exception on game_done: " + e.getMessage ());
        System.exit (1);
        }
        return ret;
    }

    /**
     * operation declare game winner
     * Informs of who won the game
     * @param position_rope position of the rope
     * @return true if the game has ended
     */
    private boolean declareGameWinner(int position_rope) {
        ReturnBoolean ret=null;
        try
        { 
            ret=refereeSiteStub.declareGameWinner(position_rope);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Referee  remote exception on declareGameWinner: " + e.getMessage ());
        System.exit (1);
        }
        refereetState=ret.getIntStateVal();
        return ret.getBooleanVal();
    }

    /**
     * operation end game
     * tells if the game has ended
     * @param end_match if true match has ended
     */
    private void EndGame(boolean end_match) {
        int ret=-1;
        try
        { 
            ret=benchStub.EndGame(end_match);
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Referee  remote exception on EndGame: " + e.getMessage ());
        System.exit (1);
        }
        refereetState=ret;
    }

    /**
     * operation get end of match
     * to check if the match has ended
     * @return true if game has ended
     */
    private boolean getEndOfMatch() {
        boolean ret=false;
        try
        { 
            ret=refereeSiteStub.getEndOfMatch();
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Referee  remote exception on getEndOfMatch: " + e.getMessage ());
        System.exit (1);
        }
        return ret;
    }

    /**
     * operation declare Match winner
     * declares the match winner based on the number of games won
     */
    private void declareMatchWinner() {
        int ret=-1;
        try
        { 
            ret=refereeSiteStub.declareMatchWinner();
        }
        catch (RemoteException e)
        { GenericIO.writelnString ("Referee  remote exception on declareMatchWinner: " + e.getMessage ());
        System.exit (1);
        }
        refereetState=ret;
    }

}