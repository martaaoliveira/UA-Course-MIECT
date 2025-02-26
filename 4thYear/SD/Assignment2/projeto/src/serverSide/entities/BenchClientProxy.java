package serverSide.entities;
import java.util.List;
import clientSide.entities.*;
import commInfra.Message;
import commInfra.ServerCom;
import commInfra.MessageException;
import genclass.GenericIO;
import serverSide.sharedRegion.BenchInterface;

public class BenchClientProxy extends Thread implements CoachCloning, ContestantCloning, RefereeCloning {
    
    /**
     * identification of the coach
     */
    private int coachId;
    /**
     * state of the coach
     */
    private int coachState;
    /**
     * identification of the contestant
     */
    private int contestantId;
    /**
     * state of the contestant
     */
    private int contestantState;
    /**
     * identification of the team
     */
    private int teamId;
    /**
     * if a constestant is playing or sitting on the bench
     */
    private boolean playing;

    /**
     * strength of the constestant
     */
    private int strength;

    /**
	 * Number of instantiayed threads.
	*/

	private static int nProxy = 0;
	
    /**
     * List of team members
     */
    private List<Integer> teamMemberIDs; // Use a List to manage team members
        /**
     * List of Coach IDs.
     */
    private List<Integer> coachIDs;

    /**
     * List of Contestant IDs.
     */
    private List<Integer> contestantIDs;


    /**
     * state of the referee
     */
    private int refereetState;

    /**     
	 * Communication channel.
	*/
	private ServerCom sconi;


    /**
     * Bench interface
     */
    private BenchInterface benchInterface;


    
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
     * Get the team members list
     * @return teamMembers
     */
    public List<Integer> getTeamMemberIDs() {
        return teamMemberIDs;
    }


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
     * set contestant id
     * @param id contestant id
     */
    @Override
    public void setcontestantId(int id) {
        this.contestantId=id;
    }

    /**
     * get contestant id
     * @return contestant id
     */
    @Override
    public int getcontestantId() {
       return contestantId;
    }

    /**
     * set if a contstant is playing or not
     * @param playing boolean to check if a constestant is playing or not
     */
    @Override
    public void setplaying(boolean playing) {
       this.playing=playing;
    }

    /**
     * get if a constestant is playing or not
     */
    @Override
    public boolean getplaying() {
        return playing; 
    }

    /**
     * set contestant state
     * @param state contestant state
     */
    @Override
    public void setContestantState(int state) {
        contestantState=state;
    }

    /**
     * get contestant state
     * @return contestant state
     */
    @Override
    public int getContestantState() {
       return contestantState;
    }


    /**
     * get strength of the constestant
     * @return strength of the constestant
     */
    @Override
    public int getStrength() {
        return strength;
    }

    /**
     * set strength of the constestant
     * @param strength strength of the constestant
     */
    @Override
    public void setStrength(int strength) {
       this.strength=strength;
    }


    /**
     * set coach id
     *  @param id coach id
     */
    @Override
    public void setcoachId(int id) {
        coachId=id;
    }

    /**
     * get coach id
     * @return coach id
     */
    @Override
    public int getcoachId() {
        return coachId;
    }

    /**
     * set coach state
     * @param coachID coach id
     * @param state coach state
     */
    @Override
    public void setcoachState(int coachID, int state) {
            coachState = state;
    }

    /**
     * get coach state
     * @return coach state
     */
    @Override
    public int getcoachState() {
        return coachState;
    }

    /**
     * get the team id
     * @return team id
     */
    @Override
    public int getTeamID() {
       return teamId;
    }



    /**
     * instantiation of the bench client proxy
     * @param sconi communication channel instantiation
     * @param benchInterface interface for the bench
     */
    public BenchClientProxy(ServerCom sconi, BenchInterface benchInterface) {
		super("BenchProxy" + BenchClientProxy.getProxyId());
		this.sconi = sconi;
		this.benchInterface = benchInterface;
	}

    /**
     * get proxy id
     * @return proxy id
     */
    private static int getProxyId() {
		Class<?> cl = null; // representation of the BarberShopClientProxy object in JVM
		int proxyId; // instantiation identifier

		try {
			cl = Class.forName("serverSide.entities.BenchClientProxy");
		} catch (ClassNotFoundException e) {
			GenericIO.writelnString("Data type BenchClientProxy was not found!");
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
			outMessage = benchInterface.processAndReply(inMessage); // process it
		} catch (MessageException e) {
			GenericIO.writelnString("Thread " + getName() + ": " + e.getMessage() + "!");
			GenericIO.writelnString(e.getMessageVal().toString());
			System.exit(1);
		}
		sconi.writeObject(outMessage); // send service reply
		sconi.close(); // close the communication channel
	}









}
