package entities;

import java.util.List;
import sharedRegion.Bench;
import sharedRegion.playground;
import sharedRegion.refereeSite;
/**
 * Coach class represents the coach entity 
 * it's used to simulate the Coach life cycle
 */

public class Coach extends Thread {
    /**
     * Coach identification
     */
    private int coachID;
    /**
     * Coach state
     */
    private int coachState;
    /**
     * Team identification
     */
    private final int teamID; 
    /**
     * List of team members
     */
    private List<Integer> teamMemberIDs; // Use a List to manage team members

    /**
     * Reference to the referee site
     */
    private final refereeSite refereeSite; 
    /**
     * Reference to the bench
     */
    private final Bench bench;
    /**
     * Reference to the playground
     */
    private final playground playground;

    /**
     * Coach constructor
     * @param name
     * @param coachID
     * @param teamID
     * @param teamMembers
     * @param refereeSite
     * @param bench
     * @param playground
     */
    public Coach(String name, int coachID,int teamID, List<Integer> teamMemberIDs,refereeSite refereeSite,Bench bench,playground playground) {
        super(name);
        this.coachID = coachID;
        this.teamID = teamID;
        coachState = CoachState.WAIT_FOR_REFEREE_COMMAND;
        this.teamMemberIDs = teamMemberIDs; // Initialize the coach's team with IDs
        this.bench = bench;
        this.refereeSite=refereeSite;
        this.playground = playground;
    
    }

    /**
     * Set the coach identification
     * @param id
     */
    public void setcoachId (int id)
    {
        coachID = id;
    }
 
    /**
     * Get the coach identification
     * @return coachID
     */
    public int getcoachId()
    {
       return coachID;
    }


    /**
     * Set the coach state
     * @param coachID
     * @param state
     */
    public void setcoachState(int coachID,int state)
    {
        coachState = state;
    }
 
    /**
     * Get the coach state
     * @return coachState
     */
    public int getcoachState ()
    {
       return coachState;
    }

    /**
     * get the team ID
     * @return teamID
     */
    public int getTeamID() {
        return teamID;
    }
    /**
     * Get the team members list
     * @return teamMembers
     */
    public List<Integer> getTeamMemberIDs() {
        return teamMemberIDs;
    }


    /**
     * Coach life cycle
     */
    @Override
	public void run() {

        bench.callContestants();
        while(!refereeSite.getEndOfMatch()){
            playground.informReferee(); 
            playground.reviewNotes();  
            bench.callContestants();

        }
                 
    }

}
