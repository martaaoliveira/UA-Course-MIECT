package clientSide.stubs;


import clientSide.entities.*;

import commInfra.*;
import genclass.GenericIO;

/**
 * stub to the Bench stub
 * it instantiates a remote reference to the bench stub
 */

public class BenchStub {
	/**
	 * name of the platform of the bench server
	 */
    private String serverHostName;

	/**
	 * port number for listening the service requests
	 */

    private int serverPortNumb;

	/**
	 * instantiation of the bench stub
	 * @param serverHostName
	 * @param serverPortNumb
	 */

    public BenchStub(String serverHostName, int serverPortNumb) {
        this.serverHostName = serverHostName;
        this.serverPortNumb = serverPortNumb;
    }

	/**
	 * call the trial, it is called by the referee
	 */
    public void callTrial() {
        ClientCom com; // communication channel
		Message outMessage, inMessage; // incoming e outcoming message

        com = new ClientCom(serverHostName, serverPortNumb);

        while (!com.open()) // waits for a connection to be established
		{
			try {
				Thread.currentThread().sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

        // forma 4 (type, state)
		outMessage = new Message(MessageType.REQCALLTRIAL,((Referee) Thread.currentThread()).getrefereeState());
		//GenericIO.writelnString("Sending message to server Call Trial"+ outMessage.toString());

        com.writeObject(outMessage);
		inMessage = (Message) com.readObject();
		// if (inMessage == null) {
        //     GenericIO.writelnString("No message received from server.");
        // } else {
        //     GenericIO.writelnString("Received message from server: " + inMessage.toString());
        // }
		if (inMessage.getMsgType() != MessageType.CALLTRIALDONE) {
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid message type!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}if(inMessage.getRefereeState()< RefereeState.START_OF_A_GAME || inMessage.getRefereeState()> RefereeState.WAIT_ALL_SAB){
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": callTrial  - Invalid state!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

		com.close();
		((Referee) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());


    }

	/**
	 * call the contestants, it is called by the coach
	 */
    public void callContestants() {
        ClientCom com; // communication channel
		Message outMessage, inMessage; // incoming e outcoming message

        com = new ClientCom(serverHostName, serverPortNumb);

        while (!com.open()) // waits for a connection to be established
		{
			try {
				Thread.currentThread().sleep((long) (10));
			} catch (InterruptedException e) {

        	}
		}


        // forma 3 (type, id, state)
		outMessage = new Message(MessageType.REQCALLCONTESTANTS, ((Coach) Thread.currentThread()).getcoachId(),((Coach) Thread.currentThread()).getcoachState());
		//GenericIO.writelnString("Sending message to server Call Contestants"+ outMessage.toString());
		com.writeObject(outMessage);
		inMessage = (Message) com.readObject();
		// if (inMessage == null) {
        //     GenericIO.writelnString("No message received from server ReqCallContestants.");
        // } else {
        //     GenericIO.writelnString("Received message from server Req CallContestants " + inMessage.toString());
        // }
		if ((inMessage.getMsgType() != MessageType.CALLCONTESTANTSDONE)) {
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid message type!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}
		if(inMessage.getCoachId()<0 || inMessage.getCoachId()>1){
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid coach id!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}if(inMessage.getCoachState()< CoachState.WAIT_FOR_REFEREE_COMMAND || inMessage.getCoachState()> CoachState.WATCH_TRIAL){
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": callContestants  - Invalid state!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}


        com.close();
		//GenericIO.writelnString("Coach "+ ((Coach) Thread.currentThread()).getcoachId()+ "Called Contestants");
		((Coach) Thread.currentThread()).setcoachState(((Coach) Thread.currentThread()).getcoachId(),inMessage.getCoachState());
    }

	/**
	 * follow the coach advice, it is called by the contestants
	 */

	public void followCoachAdvice() {
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);
		Message outMessage, inMessage;

		while (!com.open()) // waits for a connection to be established
		{
			try {
				Thread.currentThread().sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

		//forma 3
		outMessage = new Message(MessageType.REQFOLLOWCOACHADVICE, ((Contestant) Thread.currentThread()).getcontestantId(), ((Contestant) Thread.currentThread()).getContestantState(),((Contestant) Thread.currentThread()).getStrength());
		//GenericIO.writelnString("Sending message to server Follow coach advice "+ outMessage.toString());

		com.writeObject(outMessage);
		inMessage = (Message) com.readObject();

		if (inMessage.getMsgType() != MessageType.FOLLOWCOACHADVICEDONE) {
			GenericIO.writelnString("Invalid message type!");
			System.exit(1);
		}if(inMessage.getContestantState()< ContestantState.SEAT_AT_THE_BENCH || inMessage.getContestantState()> ContestantState.DO_YOUR_BEST){
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": followCoachAdvice  - Invalid state!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}if(inMessage.getContestantId()<0 || inMessage.getContestantId()>10){
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid contestant follow coach advice id!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}


		com.close();
		((Contestant) Thread.currentThread()).setContestantState(inMessage.getContestantState());

	}

	/**
	 * seat down, it is called by the contestants

	 */
	public void seatDown() {
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);
		Message outMessage, inMessage;

		while (!com.open()) // waits for a connection to be established
		{
			try {
				Thread.currentThread().sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

		//forma 3
		outMessage = new Message(MessageType.REQSEATDOWN, ((Contestant) Thread.currentThread()).getcontestantId(), ((Contestant) Thread.currentThread()).getContestantState(),((Contestant) Thread.currentThread()).getStrength());
		//GenericIO.writelnString("Sending message SeatDown to server: " + outMessage.toString()+ "contestant"+ ((Contestant) Thread.currentThread()).getcontestantId());

		com.writeObject(outMessage);
		inMessage = (Message) com.readObject();
		// if (inMessage == null) {
        //     GenericIO.writelnString("No message received from server.");
        // } else {
        //     GenericIO.writelnString("Received message from server: " + inMessage.toString());
        // }
		if (inMessage.getMsgType() != MessageType.SEATDOWNDONE) {
			GenericIO.writelnString("Invalid message type!");
			System.exit(1);
		}if(inMessage.getContestantState()< ContestantState.SEAT_AT_THE_BENCH || inMessage.getContestantState()> ContestantState.DO_YOUR_BEST){
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": seatdown  - Invalid state!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}if(inMessage.getContestantId()<0 || inMessage.getContestantId()>10){
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": setDown Invalid contestant id!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}


		//((Contestant) Thread.currentThread()).setStrength(inMessage.getStrength());
		com.close();
		((Contestant) Thread.currentThread()).setContestantState(inMessage.getContestantState());
		((Contestant) Thread.currentThread()).setStrength(inMessage.getStrength());

	}

	/**
	 * get ready, it is called by the referee
	 * @param endOfMatch indficates if the math has ended
	 */
	public void EndGame(boolean endOfMatch) {
		ClientCom com = new ClientCom(serverHostName, serverPortNumb);
		Message outMessage, inMessage;


		while (!com.open()) // waits for a connection to be established
		{
			try {
				Thread.currentThread().sleep((long) (10));
			} catch (InterruptedException e) {
			}
		}

		//forma 4
		outMessage = new Message(MessageType.REQENDGAME,((Referee) Thread.currentThread()).getrefereeState(),endOfMatch);
		//GenericIO.writelnString("Sending message to server EndGame"+ outMessage.toString());

		com.writeObject(outMessage);

		inMessage = (Message) com.readObject();

		if (inMessage.getMsgType() != MessageType.ENDGAMEDONE) {
			GenericIO.writelnString("Invalid message type!");
			System.exit(1);
		}if(inMessage.getRefereeState()< RefereeState.START_OF_A_GAME || inMessage.getRefereeState()> RefereeState.WAIT_ALL_SAB){
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": EndGame  - Invalid referee state!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}


		com.close();
		((Referee) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());


	}

	/**
	 * operation end of work
	 * @param contestantID identification of the contestant
	 */
	public void endOperation(int contestantID) {
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
		// form 4 (type, id)
		outMessage = new Message(MessageType.ENDOPCONTESTANT, contestantID);
		com.writeObject(outMessage);
		inMessage = (Message) com.readObject();
		if (inMessage.getMsgType() != MessageType.ENDOPDONECONTESTANT) {
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid message type!");
			GenericIO.writelnString(inMessage.toString());
			System.exit(1);
		}
		if (inMessage.getContestantId() != contestantID) {
			GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid contestant id Endoperation!");
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
