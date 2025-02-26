package clientSide.entities;

import java.util.List;

import interfaces.*;

import java.rmi.*;

import genclass.GenericIO;


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
    private final RefereeSiteInterface refereeSiteStub; 
    /**
     * Reference to the bench
     */
    private final BenchInterface benchStub;
    /**
     * Reference to the playground
     */
    private final PlaygroundInterface playgroundStub;

    /**
     * Coach constructor
     * @param name thread name
     * @param coachID coach identification
     * @param teamMemberIDs team members identification
     * @param refereeSiteStub refereeSiteStub reference
     * @param benchStub BenchStub reference
     * @param playgroundStub playgroundStub reference
     */
    public Coach(String name, int coachID,int teamID,List<Integer> teamMemberIDs,RefereeSiteInterface refereeSiteStub,BenchInterface benchStub,PlaygroundInterface playgroundStub) {
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

        callContestants();
        while(!getEndOfMatch()){
            informReferee(); 
            reviewNotes();  
            callContestants();

        }
                 
  }


    /**
     * Call contestants to play
     */
	private void callContestants() {
		int ret=-1;
		 try
	      { 
			ret=benchStub.callContestants(coachID);
	      }
	      catch (RemoteException e)
	      { GenericIO.writelnString ("Coach " + coachID + " remote exception on Call Contestants: " + e.getMessage ());
	        System.exit (1);
	      }
		 coachState=ret;
	}

    /**
     * Operation get end of match
     * check if match has ended
     * @return true if match has ended
     */
    private boolean getEndOfMatch() {
		boolean ret=false;
		 try
	      { 
			ret=refereeSiteStub.getEndOfMatch();
	      }
	      catch (RemoteException e)
	      { GenericIO.writelnString ("Coach " + coachID + " remote exception on getEndOfMatch: " + e.getMessage ());
	        System.exit (1);
	      }
		 return ret;
	}

    /**
     * Operation informReferee
     * Inform referee of Coach decisions
     */
    private void informReferee() {
		int ret=-1;
		 try
	      { 
			ret=playgroundStub.informReferee(coachID);
	      }
	      catch (RemoteException e)
	      { GenericIO.writelnString ("Coach " + coachID + " remote exception on informReferee: " + e.getMessage ());
	        System.exit (1);
	      }
		 coachState=ret;
	}

    /**
     * Operation review notes
     * Coach reviises his notes
     */
    private void reviewNotes() {
		int ret=-1;
		 try
	      { 
			ret=playgroundStub.reviewNotes(coachID);
	      }
	      catch (RemoteException e)
	      { GenericIO.writelnString ("Coach " + coachID + " remote exception on reviewNotes: " + e.getMessage ());
	        System.exit (1);
	      }
		 coachState=ret;
	}

	

}
