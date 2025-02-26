package clientSide.stubs;
import clientSide.entities.*;
//import entities.*;
//import main.*;
import commInfra.*;
import genclass.GenericIO;

/**
 * stub to the referee site stub
 * it instantiates a remote reference to the RefereeSite server
 */
public class RefereeSiteStub {

    /**
     * name of the platform of the refereeSite server
     */
    private String serverHostName;

    /**
     * port number of the refereeSite server
     */
    private int serverPortNumber;

    /**
     * refereeSite constructor
     * @param serverHostName name of the platform of the refereeSite server
     * @param serverPortNumber port number of the refereeSite server
     */
    public RefereeSiteStub(String serverHostName, int serverPortNumber) {
        this.serverHostName = serverHostName;
        this.serverPortNumber = serverPortNumber;
    }


    /**
     * Operation announceNewGame
     * Method called by the referee announce a new game
     */
    public void announceNewGame() {
        ClientCom com;
        Message inMessage, outMessage; // ingoing and outgoing messages instanciation
        com = new ClientCom(serverHostName, serverPortNumber);

        while(!com.open()) {
            try {
                Thread.currentThread ().sleep ((long) (10));
            } catch (InterruptedException e) {}
        }

        //forma 4
        outMessage = new Message(MessageType.REQANNOUNCENEWGAME, ((Referee)Thread.currentThread()).getrefereeState());
        //GenericIO.writelnString("Sending message announceNewGame to server: " + outMessage.toString());
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();


        // if (inMessage == null) {
        //     GenericIO.writelnString("No message received from server.");
        // } else {
        //     GenericIO.writelnString("Received message from server: " + inMessage.toString());
        // }

        if(inMessage.getMsgType() != MessageType.ANNOUNCENEWGAMEDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": announceNewGame - Invalid message type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        com.close();
        ((Referee)Thread.currentThread()).setrefereeState(inMessage.getRefereeState());
        //((Referee)Thread.currentThread()).setNrGames(inMensage.getNrGames())

    }

    /**
     * Operation declareGameWinner
     * It is called by the referee to Declare the game winner based on the position of the rope
     * @param position position of the rope
     */
    public boolean declareGameWinner(int position) {
        ClientCom com;
        Message inMessage, outMessage; // ingoing and outgoing messages instanciation
        com = new ClientCom(serverHostName, serverPortNumber);

        while(!com.open()) {
            try {
                Thread.currentThread ().sleep ((long) (10));
            } catch (InterruptedException e) {}
        }

        //forma 3
        outMessage = new Message(MessageType.REQDECLAREGAMEWINNER, ((Referee)Thread.currentThread()).getrefereeState(),position);
        com.writeObject(outMessage);   // sends the message
		//GenericIO.writelnString("Sending message to server DeclareGameWinner"+ outMessage.toString());

        inMessage = (Message) com.readObject();
        if(inMessage.getMsgType() != MessageType.DECLAREGAMEWINNERDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": declareGameWinner - Invalid message type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        if(inMessage.getRefereeState()< RefereeState.START_OF_A_GAME || inMessage.getRefereeState()> RefereeState.WAIT_ALL_SAB){
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": declareGameWinner - Invalid state!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }


        com.close();
        ((Referee)Thread.currentThread()).setrefereeState(inMessage.getRefereeState());
        return inMessage.getEndOfMatch();

    }

    /**
     * Operation declareMatchWinner
     * It is called by the referee to declare the match winner based on the number of games won
     */
    public void declareMatchWinner() {
        ClientCom com;
        Message inMessage, outMessage; // ingoing and outgoing messages instanciation
        com = new ClientCom(serverHostName, serverPortNumber);

        while(!com.open()) {
            try {
                Thread.currentThread ().sleep ((long) (10));
            } catch (InterruptedException e) {}
        }


        //forma 3
        outMessage = new Message(MessageType.REQDECLAREMATCHWINNER,((Referee)Thread.currentThread()).getrefereeState());
        com.writeObject(outMessage);   // sends the message
        //GenericIO.writelnString("Sending message to server MatchWinner"+ outMessage.toString());

        inMessage = (Message) com.readObject();
        if(inMessage.getMsgType() != MessageType.DECLAREMATCHWINNERDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": declareGameWinner - Invalid message type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        if(inMessage.getRefereeState()< RefereeState.START_OF_A_GAME || inMessage.getRefereeState()> RefereeState.WAIT_ALL_SAB){
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": declareGameWinner - Invalid state!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        com.close();
        ((Referee)Thread.currentThread()).setrefereeState(inMessage.getRefereeState());

    }

    /**
     * operation getEndOfMatch, it's called by all threads
     * @return boolean endOfMatch that tells if the game has ended or not
     */
    public boolean getEndOfMatch(){
        ClientCom com; // communication channel
		Message outMessage, inMessage; // // outgoing, incoming message

		com = new ClientCom(serverHostName, serverPortNumber);
		while (!com.open()) // waits for a connection to be established
		{
			try {
				Thread.currentThread().sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

        //forma 4
        if(Thread.currentThread().getName().startsWith("Referee_")){
            outMessage = new Message(MessageType.REQENDOFMATCHREFEREE, ((Referee) Thread.currentThread()).getrefereeState());
            com.writeObject(outMessage);
            //GenericIO.writelnString("Sending message ENDOFMATCH Referee to server: " + outMessage.toString());
            inMessage = (Message) com.readObject();

            // if (inMessage == null) {
            //     GenericIO.writelnString("No message received from server EndOfMatch Referee");
            // } else {
            //     GenericIO.writelnString("Received message from server EndOfMatch Referee: " + inMessage.toString());
            // }

            if (inMessage.getMsgType() != MessageType.ENDOFMATCHREFEREEDONE) {
                GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid message type!");
                GenericIO.writelnString(inMessage.toString());
                System.exit(1);
            }


            com.close();
            //GenericIO.writelnString("ENDOFMATCH Referee Flag: " + inMessage.getEndOfMatch());
            return inMessage.getEndOfMatch();

        }
        //forma 3
        else if(Thread.currentThread().getName().startsWith("Coach_")){
            outMessage = new Message(MessageType.REQENDOFMATCHCOACH,((Coach)Thread.currentThread()).getcoachId() ,((Coach) Thread.currentThread()).getcoachState());
            com.writeObject(outMessage);
            //GenericIO.writelnString("Sending message ENDOFMATCH Coach to server: " + outMessage.toString());
            inMessage = (Message) com.readObject();

            // if (inMessage == null) {
            //     GenericIO.writelnString("No message received from server EndOfMatch Coach.");
            // } else {
            //     GenericIO.writelnString("Received message from server EndOfMatch Coach " + inMessage.toString());
            // }

            if (inMessage.getMsgType() != MessageType.ENDOFMATCHCOACHDONE) {
                GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid message type!");
                GenericIO.writelnString(inMessage.toString());
                System.exit(1);
            }


            com.close();
            //GenericIO.writelnString("ENDOFMATCH Coach Flag: " + inMessage.getEndOfMatch());

            return inMessage.getEndOfMatch();

        }
        //forma 6
        else {
            outMessage = new Message(MessageType.REQENDOFMATCHCONTESTANT,((Contestant)Thread.currentThread()).getcontestantId() ,((Contestant) Thread.currentThread()).getContestantState());
            //GenericIO.writelnString("Sending message ENDOFMATCH Contestant to server: " + outMessage.toString());
            com.writeObject(outMessage);
            inMessage = (Message) com.readObject();

            // if (inMessage == null) {
            //     GenericIO.writelnString("No message received from server EndOfMatch Contestant.");
            // } else {
            //     GenericIO.writelnString("Received message from server: EndOfMatch Contestant" + inMessage.toString());
            // }

            if (inMessage.getMsgType() != MessageType.ENDOFMATCHCONTESTANTDONE) {
                GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid message type!");
                GenericIO.writelnString(inMessage.toString());
                System.exit(1);
            }
            com.close();
            //GenericIO.writelnString("ENDOFMATCH Contestant Flag: " + inMessage.getEndOfMatch());

            return inMessage.getEndOfMatch();

        }

    }

    /**
     * operation end of work
     * @param coachId identification of the coach
     */
    public void endOperation(int coachId) {
		ClientCom com; // communication channel
		Message outMessage, // outgoing message
				inMessage; // incoming message

		com = new ClientCom(serverHostName, serverPortNumber);
		while (!com.open()) {
			try {
				Thread.sleep((long) (1000));
			} catch (InterruptedException e) {
			}
		}
		// forma 1 type
		outMessage = new Message(MessageType.ENDOPREFEREE);

		com.writeObject(outMessage);
		inMessage = (Message) com.readObject();
		if (inMessage.getMsgType() != MessageType.ENDOPDONEREFEREE) {
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid message type!");
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

		com = new ClientCom(serverHostName, serverPortNumber);
		while (!com.open()) {
			try {
				Thread.sleep((long) (1000));
			} catch (InterruptedException e) {
			}
		}
        //forma 1
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
