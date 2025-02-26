package clientSide.stubs;
import genclass.GenericIO;
import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;


/**
* GeneralRepo stub
* it instantiates a remote reference to the General Repo stub
*/


public class GeneralRepoStub {
    private String serverHostName = null;
    private int serverPortNumb;

    public GeneralRepoStub(String hostName, int port) {
        this.serverHostName = hostName;
        this.serverPortNumb = port;
    }


    /**
	 * Operation initialization of the simulation.
	 *
	 * @param fileName logging file name
	 */

	public void initSimul(String fileName) {
		ClientCom com; // communication channel
		Message outMessage; // outgoing message
		Message inMessage; // incoming message

		com = new ClientCom(serverHostName, serverPortNumb);
		while (!com.open()) {
			try {
				Thread.sleep((long) (1000));
			} catch (InterruptedException e) {
			}
		}

        //forma 2 
		outMessage = new Message(MessageType.SETNFIC, fileName);
		com.writeObject(outMessage);
		inMessage = (Message) com.readObject();
		if (inMessage.getMsgType() != MessageType.NFICDONE) {
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid message type!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}
		com.close();
	}

	/**
	 * Operation server shutdown.
	 *
	 */

	public void shutdown() {
		ClientCom com; // communication channel
		Message outMessage, // outgoing message
				inMessage; // incoming message

		com = new ClientCom(serverHostName, serverPortNumb);
		while (!com.open()) {
			try {
				Thread.sleep((long) (1000));
			} catch (InterruptedException e) {
			}
		}
		// forma 1 (type)
		outMessage = new Message(MessageType.SHUT);
		com.writeObject(outMessage);
		inMessage = (Message) com.readObject();
		if (inMessage.getMsgType() != MessageType.SHUTDONE) {
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid message type!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}
		com.close();
	}

	/**
	 * Set referee state.
	 *
	 * @param state referee state
	 */
    public void setRefereeState(int state) {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;
        
        while (!com.open()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }


        //forma 4 (type, state)

        outMessage = new Message(MessageType.SETREFEREESTATE, state);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
        
        if (inMessage.getMsgType() != MessageType.SACK) {
            System.err.println("Invalid message type!");
            System.exit(1);
        }
        
        com.close();
    }

    /**
     * Set the outcome of the match
     * @param details details of the match
     */
    public void setOutcomeDetails(String details) {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;
    
        while (!com.open()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }
        
        //forma 2 
        outMessage = new Message(MessageType.SETOUTCOMEDETAILS, details);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
    
        if (inMessage.getMsgType() != MessageType.SACK) {
            System.err.println("Invalid message type!");
            System.exit(1);
        }
    
        com.close();
    }
    /**
     * set referee state and number of games
     * @param state state of the referee
     * @param number_games number of games
     */
    public void setRefereeStateAndNumberGames(int state, int number_games) {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;
    
        while (!com.open()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }
        
        //forma 3 
        outMessage = new Message(MessageType.SETREFEREESTATEANDNUMBERGAMES, state, number_games);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
    
        if (inMessage.getMsgType() != MessageType.SACK) {
            System.err.println("Invalid message type!");
            System.exit(1);
        }
    
        com.close();
    }

    /**
     * set the state of the referee and the winner of the match
     * @param state state of the referee
     * @param matchWinner winner of the match
     * @param scoreTeam1 score of team 1
     * @param scoreTeam2 score of team 2
     */
    public void setRefereeStateAndMatchWinner(int state, int matchWinner, int scoreTeam1, int scoreTeam2) {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;
    
        while (!com.open()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }
        //GenericIO.writelnString("Sending message to server Matchwinner"+ matchWinner);

        //forma 5
        outMessage = new Message(MessageType.SETREFEREESTATEANDMATCHWINNER, state, matchWinner, scoreTeam1, scoreTeam2);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
    
        if (inMessage.getMsgType() != MessageType.SACK) {
            System.err.println("Invalid message type!");
            System.exit(1);
        }
    
        com.close();
    }

    /**
     * sets the position of the rope
     * @param position_rope
     */
    public void setPositionRope(int position_rope) {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!com.open()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }

        //forma 4
        outMessage = new Message(MessageType.SETPOSITIONROPE, position_rope);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        if (inMessage.getMsgType() != MessageType.SACK) {
            System.err.println("Invalid message type!");
            System.exit(1);
        }

        com.close();
    }


    /**
     * defines the number of trials
     * @param number_trials number of trials played
     */
    public void set_nr_trials(int number_trials) {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!com.open()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }

        //forma 4 
        outMessage = new Message(MessageType.SETNUMBERTRIALS, number_trials);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        if (inMessage.getMsgType() != MessageType.SACK) {
            System.err.println("Invalid message type!");
            System.exit(1);
        }

        com.close();
    }


    /**
     * set coach state
     * @param coachId identification of the coach
     * @param state state of the coach
     */
    public void setCoachState(int coachId, int state) {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!com.open()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }

        //forma 3 
        outMessage = new Message(MessageType.SETCOACHSTATE, coachId, state);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        if (inMessage.getMsgType() != MessageType.SACK) {
            System.err.println("Invalid message type!");
            System.exit(1);
        }

        com.close();
    }


    /**
     * set the state of the contestant and its strength
     * @param id identification of the contestant
     * @param state state of the contestant
     * @param strength strength of the contestant
     * @param team  team of the contestant
     */
    public void setContestantStateAndStrenght(int id, int state, int strength, int team) {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!com.open()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }

        //forma 5
        outMessage = new Message(MessageType.SETCONTESTANTSTATESTRENGTH, id, state, strength, team);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        if (inMessage.getMsgType() != MessageType.SACK) {
            System.err.println("Invalid message type!");
            System.exit(1);
        }


        com.close();
    }



}
