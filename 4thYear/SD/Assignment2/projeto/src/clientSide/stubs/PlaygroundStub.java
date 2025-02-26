package clientSide.stubs;

import clientSide.entities.Coach;
import clientSide.entities.CoachState;
import clientSide.entities.Contestant;
import clientSide.entities.ContestantState;
import clientSide.entities.Referee;
import clientSide.entities.RefereeState;
import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;
import genclass.GenericIO;


/**
 * bench stub for the playground
 * it instantiates a remote reference to the playground server
 */
public class PlaygroundStub {
    /**
	 * name of the platform of the bench server
     */
    private String serverHostName = null;

    /**
     * port number of the bench server
     */
    private int serverPortNumb;

    public PlaygroundStub(String hostName, int port) {
        serverHostName = hostName;
        serverPortNumb = port;
    }

    /**
     * function to start the trial, it is called by the referee
     */
    public void startTrial() {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!com.open()) {   // tries to establish connection until it is successful
            try {
                Thread.sleep((long) (10));
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }
        //forma 4
        outMessage = new Message(MessageType.REQSTARTTRIAL,((Referee) Thread.currentThread()).getrefereeState());
        //GenericIO.writelnString("Sending message to server Start Trial"+ outMessage.toString());

        com.writeObject(outMessage);   // sends the message
        inMessage = (Message) com.readObject();   // reads response
		// if (inMessage == null) {
        //     GenericIO.writelnString("No message received from server Start Trial.");
        // } else {
        //     GenericIO.writelnString("Received message from server StartTrial: " + inMessage.toString());
        // }
        if (inMessage.getMsgType() != MessageType.STARTTRIALDONE) {
            System.err.println("Invalid message type!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }

        if (inMessage.getRefereeState() < RefereeState.START_OF_A_GAME || inMessage.getRefereeState() > RefereeState.WAIT_ALL_SAB) {
            System.err.println("Invalid referee state!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }

        com.close();
        ((Referee) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());

    }

    /**
     * function that tell the contestant to get ready, it is called by the coach
     */
    public void getReady() {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!com.open()) {
            try {
                Thread.sleep((long) (10));
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }


        //forma 3
        outMessage = new Message(MessageType.REQGETREADY,((Contestant) Thread.currentThread()).getcontestantId(), ((Contestant) Thread.currentThread()).getContestantState(),((Contestant) Thread.currentThread()).getStrength());
		//GenericIO.writelnString("Sending message to server Get Ready"+ outMessage.toString());

        com.writeObject(outMessage);

        inMessage = (Message) com.readObject();
        if (inMessage.getMsgType() != MessageType.GETREADYDONE) {
            System.err.println("Invalid message type!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }if(inMessage.getContestantId()<0 || inMessage.getContestantId()>10){
            System.err.println("Invalid contestant id!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }if(inMessage.getContestantState()<ContestantState.SEAT_AT_THE_BENCH || inMessage.getContestantState()>ContestantState.DO_YOUR_BEST){
            System.err.println("Invalid contestant state!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }


        com.close();
        ((Contestant) Thread.currentThread()).setContestantState(inMessage.getContestantState());

    }
    /**
     * function that is called by the coach to inform the referee
     */
    public void informReferee() {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;
        int coachId = ((Coach) Thread.currentThread()).getcoachId();
        //GenericIO.writelnString("Coach id in inform Referee"+ coachId);

        while (!com.open()) {
            try {
                Thread.sleep((long) (10));
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }

        //forma 3
        outMessage = new Message(MessageType.REQINFORMREFEREE, ((Coach) Thread.currentThread()).getcoachId(),((Coach) Thread.currentThread()).getcoachState());
        //GenericIO.writelnString("Sending message inform referee to server: " + outMessage.toString()+ "coach"+ coachId);


        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        // if (inMessage == null) {
        //     GenericIO.writelnString("No message received from server informReferee.");
        // } else {
        //     GenericIO.writelnString("Received message from server informReree: " + inMessage.toString());
        // }
        if (inMessage.getMsgType() != MessageType.INFORMREFEREEDONE) {
            System.err.println("Invalid message type!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }if(inMessage.getCoachId()<0 || inMessage.getCoachId()>1){
            System.err.println("Invalid coach id!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }if(inMessage.getCoachState()<CoachState.WAIT_FOR_REFEREE_COMMAND || inMessage.getCoachState()>CoachState.WATCH_TRIAL){
            System.err.println("Invalid Coach state id!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }

        com.close();
        ((Coach) Thread.currentThread()).setcoachState(coachId,inMessage.getCoachState());

    }
    /**
     * a player tells when he's done playing
     */
    public void amDone() {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!com.open()) {
            try {
                Thread.sleep((long) (10));
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }

        //forma 3
        outMessage = new Message(MessageType.REQAMDONE, ((Contestant) Thread.currentThread()).getcontestantId(), ((Contestant) Thread.currentThread()).getContestantState());

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        if (inMessage.getMsgType() != MessageType.AMDONEDONE) {
            System.err.println("Invalid message type!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }if(inMessage.getContestantId()<0 || inMessage.getContestantId()>10){
            System.err.println("Invalid contestant id!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }if(inMessage.getContestantState()<ContestantState.SEAT_AT_THE_BENCH || inMessage.getContestantState()>ContestantState.DO_YOUR_BEST){
            System.err.println("Invalid contestant state!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }


        com.close();
    }


    /**
     * referee calls the trial decision
     * @return returns position of the rope at the end of the trial
     */
    public int assertTrialDecision() {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!com.open()) {
            try {
                Thread.sleep((long) (10));
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }

        //forma 4
        outMessage = new Message(MessageType.REQASSERTTRIALDECISION,((Referee) Thread.currentThread()).getrefereeState());
		//GenericIO.writelnString("Sending message to server AssertTrialDecision"+ outMessage.toString());

        com.writeObject(outMessage);

        inMessage = (Message) com.readObject();

        if (inMessage.getMsgType() != MessageType.ASSERTTRIALDECISIONDONE) {
            System.err.println("Invalid message type!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }
        if(inMessage.getRefereeState()< RefereeState.START_OF_A_GAME || inMessage.getRefereeState()> RefereeState.WAIT_ALL_SAB){
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + "Invalid state!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        com.close();

        //GenericIO.writelnString(" Position Rope AssertTrialDecision"+ inMessage.position_rope());

        return inMessage.position_rope();
    }


    /**
     * Coach review its notes
     */
    public void reviewNotes() {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        int coachId = ((Coach) Thread.currentThread()).getcoachId();


        while (!com.open()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }
        //forma 3
        outMessage = new Message(MessageType.REQREVIEWNOTES, ((Coach) Thread.currentThread()).getcoachId(),((Coach) Thread.currentThread()).getcoachState());
        //GenericIO.writelnString("Sending message to server Review Notes"+ outMessage.toString());

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
        if (inMessage.getMsgType() != MessageType.REVIEWNOTESDONE) {
            System.err.println("Invalid message type!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }if(inMessage.getCoachId()<0 || inMessage.getCoachId()>1){
            System.err.println("Invalid coach id!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }if(inMessage.getCoachState()<CoachState.WAIT_FOR_REFEREE_COMMAND || inMessage.getCoachState()>CoachState.WATCH_TRIAL){
            System.err.println("Invalid Coach state id!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }
        com.close();

        ((Coach) Thread.currentThread()).setcoachState(coachId,inMessage.getCoachState());

    }


    /**
     * Indicates if the game is done
     * @return true if the game is done, false otherwise
     */
    public boolean game_done() {
        ClientCom com = new ClientCom(serverHostName, serverPortNumb);
        Message inMessage, outMessage;

        while (!com.open()) {
            try {
                Thread.sleep((long) (10));
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted.");
                System.exit(1);
            }
        }
        //forma 4
        outMessage = new Message(MessageType.REQGAMEDONE,((Referee) Thread.currentThread()).getrefereeState(),false);
		//GenericIO.writelnString("Sending message to server Game Done"+ outMessage.toString());

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
        
        if (inMessage.getMsgType() != MessageType.GAMEDONEDONE) {
            System.err.println("Invalid message type!");
            System.err.println(inMessage.toString());
            System.exit(1);
        }if(inMessage.getRefereeState()< RefereeState.START_OF_A_GAME || inMessage.getRefereeState()> RefereeState.WAIT_ALL_SAB){
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": callTrial  - Invalid state!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }


        com.close();
        ((Referee) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());
        
        //GenericIO.writelnString("Game Done flag is " +inMessage.game_done());

        return inMessage.game_done();
    }

    /**
     * operation end of work
     * @param coachId identification of the coach
     */
    public void endOperation(int coachId) {
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
		// forma 4 (type, id)
		outMessage = new Message(MessageType.ENDOPCOACH, coachId);
		com.writeObject(outMessage);
		inMessage = (Message) com.readObject();
		if (inMessage.getMsgType() != MessageType.ENDOPDONECOACH) {
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid message type!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}
		if (inMessage.getCoachId() != coachId) {
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid coach id!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}
		com.close();
	}

    /**
     * operation server shutdown
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



}
