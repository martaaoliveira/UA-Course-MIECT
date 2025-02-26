package serverSide.entities;

import java.util.List;
import clientSide.entities.CoachCloning;
import clientSide.entities.ContestantCloning;
import clientSide.entities.RefereeCloning;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.ServerCom;
import genclass.GenericIO;
import serverSide.sharedRegion.refereeSiteInterface;

public class RefereeSiteClientProxy extends Thread implements RefereeCloning, CoachCloning, ContestantCloning{


    private int refereetState;

    /**
	* Number of instantiayed threads.
	*/

	private static int nProxy = 0;
	/**
	 * Communication channel.
	 */

	private ServerCom sconi;
	/**
	 * Referee identification.
	 */
	private int contestantId;
	/**
	 * Contestant state.
	 */
	private int contestantState;
	/**
	 * check if a contestant is playing or not
	 */
	private boolean playing;
	/**
	 * Contestant strength.
	 */
	private int strength;
	/**
	 * Coach identification.
	 */
	private int coachID;
	/**
	 * Coach state.
	 */
	private int coachState;
	/**
	 * Team identification.
	 */
	private int teamID;
	/**
	 * list of the contestants per team
	 */
    private List<Integer> teamMemberIDs; // Use a List to manage team members

	/**
	 * refereeSite Interface
	 */
    private refereeSiteInterface refereeSiteInterface;

	
	/**
     * List of Coach IDs.
     */
    private List<Integer> coachIDs;

	/**
     * List of Contestant IDs.
     */
    private List<Integer> contestantIDs;




	/**
     * set referee state
     * @param state referee state
     */
    @Override
    public void setrefereeState (int state)
    {
        refereetState = state;
    }

	/**
     * get referee state
     * @return referee state
     */
    @Override
    public int getrefereeState ()
    {
        return refereetState;
    }


	/**
	 * get the identification of the team members
	 * @return teamMemberIDs
	 */
	@Override
	public List<Integer> getTeamMemberIDs() {
		return teamMemberIDs;
	}

	/**
	 * add coach identification to the list
	 * @param coachID The coach's ID to add.
	 */
	@Override
	public void addCoachID(int coachID) {
		this.coachIDs.add(coachID);
	}

	/**
	 * add contestant identification to the list
	 * @param contestantID The contestant's ID to add.
	 */
	@Override
	public void addContestantID(int contestantID) {
		this.contestantIDs.add(contestantID);
	}

	/**
	 * gets the coach identification
	 * @param index The coach's ID to add.
	 * @return coach identification
	 */
	@Override
	public int getCoachID(int index) {
		if (index >= 0 && index < this.coachIDs.size()) {
            return this.coachIDs.get(index);
        } else {
            // Handle the case when index is out of bounds
            return -1; // Or any other appropriate value or throw an exception
        }	}

	/**
	 * gets the contestant identification
	 * @return list of the contestants identification
	 */
	@Override
	public List<Integer> getContestantID() {
		return this.contestantIDs;
	}

	/**
	 * instantiation of the referee site client proxy
	 * @param sconi comunication channel
	 * @param refereeSiteInterface interface for the referee site
	 */
    public RefereeSiteClientProxy(ServerCom sconi, refereeSiteInterface refereeSiteInterface) {
		super("RefereeProxy" + RefereeSiteClientProxy.getProxyId());
		this.sconi = sconi;
		this.refereeSiteInterface = refereeSiteInterface;
	}

	/**
	 * get the proxy id
	 * @return proxy id
	 */
    private static int getProxyId() {
		Class<?> cl = null; 
		int proxyId; // instantiation identifier

		try {
			cl = Class.forName("serverSide.entities.RefereeSiteClientProxy");
		} catch (ClassNotFoundException e) {
			GenericIO.writelnString("Data type RefereeSiteClientProxy was not found!");
			e.printStackTrace();
			System.exit(1);
		}
		synchronized (cl) {
			proxyId = nProxy;
			nProxy += 1;
		}
		return proxyId;
	}

	/**
	 * life cycle of the thread
	 */

    @Override
	public void run() {
		Message inMessage = null, // service request
				outMessage = null; // service reply

		/* service providing */

		inMessage = (Message) sconi.readObject(); // get service request
		try {
			outMessage = refereeSiteInterface.processAndReply(inMessage); // process it
		} catch (MessageException e) {
			GenericIO.writelnString("Thread " + getName() + ": " + e.getMessage() + "!");
			GenericIO.writelnString(e.getMessageVal().toString());
			System.exit(1);
		}
		sconi.writeObject(outMessage); // send service reply
		sconi.close(); // close the communication channel
	}

	/**
	 * set the contestant identification
	 * @param id contestant identification
	 */
	@Override
	public void setcontestantId(int id) {
		this.contestantId = id;
	}

	/**
	 * get the contestants identification
	 * @return contestantId
	 */
	@Override
	public int getcontestantId() {
		return contestantId;
	}

	/**
	 * flag to set if a player is going to play or sit on the bench
	 * @param playing set a player to go and play
	 */
	@Override
	public void setplaying(boolean playing) {
		this.playing=playing;
	}

	/**
	 * flag to see if a contestant is playing or not
	 * @return playing variable, if true the contestant is playing
	 */
	@Override
	public boolean getplaying() {
		return playing; 
	}

	/**
	 * set the contestant state
	 * @param state state of the contestant
	 */
	@Override
	public void setContestantState(int state) {
		contestantState=state;
		}

	/**
	 * get the state of the contestant
	 * @return state of the contestant
	 */
	@Override
	public int getContestantState() {
		return contestantState;	
	}

	/**
	 * get the contestant Strength
	 * @return state of the contestant
	 */
	@Override
	public int getStrength() {
		return strength;
	}

	/**
	 * sets the strength of the contestant
	 * @param strength strength of the contestant
	 */
	@Override
	public void setStrength(int strength) {
		this.strength = strength;	
	}

	/**
	 * sets the coach identification
	 */
	@Override
	public void setcoachId(int id) {
		coachID = id;
	}

	/**
	 * sets the coach identification
	 * @return coach identification
	 */
	@Override
	public int getcoachId() {
		return coachID;		
	}

	/**
	 * sets the coach state
	 * @param coachID identification of the coach
	 * @param state state of the coach
	 */
	@Override
	public void setcoachState(int coachID, int state) {
		coachState = state;		
	}

	/**
	 * get the state of the coach
	 * @return state of the coach
	 */
	@Override
	public int getcoachState() {
		return coachState;	
	}

	/**
	 * get the identification of the team
	 * @return team identification
	 */
	@Override
	public int getTeamID() {
		return teamID;	
	}


}
