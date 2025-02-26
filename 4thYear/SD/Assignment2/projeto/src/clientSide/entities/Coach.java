package clientSide.entities;

import java.util.List;
import clientSide.stubs.*;

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
    private final RefereeSiteStub refereeSiteStub; 
    /**
     * Reference to the bench
     */
    private final BenchStub benchStub;
    /**
     * Reference to the playground
     */
    private final PlaygroundStub playgroundStub;

    /**
     * Coach constructor
     * @param name thread name
     * @param coachID coach identification
     * @param teamMemberIDs team members identification
     * @param refereeSiteStub refereeSiteStub reference
     * @param benchStub BenchStub reference
     * @param playgroundStub playgroundStub reference
     */
    public Coach(String name, int coachID,int teamID,List<Integer> teamMemberIDs,RefereeSiteStub refereeSiteStub,BenchStub benchStub,PlaygroundStub playgroundStub) {
        super(name);
        this.coachID = coachID;
        this.teamID = teamID;
        coachState = CoachState.WAIT_FOR_REFEREE_COMMAND;
        this.benchStub = benchStub;
        this.refereeSiteStub=refereeSiteStub;
        this.playgroundStub = playgroundStub;
        this.teamMemberIDs = teamMemberIDs; // Initialize the coach's team with IDs

    
    }

    /**
     * Set the coach identification
     * @param id identification of the coach
     */
    public void setcoachId (int id)
    {
        coachID = id;
    }
 
    /**
     * Get the coach identification
     * @return coachID coach identification
     */
    public int getcoachId()
    {
       return coachID;
    }


    /**
     * Set the coach state
     * @param coachID identification of the coach
     * @param state state of the coach
     */
    public void setcoachState(int coachID,int state)
    {
        coachState = state;
    }
 
    /**
     * Get the coach state
     * @return coachState state of the coach
     */
    public int getcoachState ()
    {
       return coachState;
    }

    /**
     * get the team ID
     * @return teamID team identification
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

        benchStub.callContestants();
        while(!refereeSiteStub.getEndOfMatch()){
            playgroundStub.informReferee(); 
            playgroundStub.reviewNotes();  
            benchStub.callContestants();

        }
                 
    }

}
