package serverSide.sharedRegion;

import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import genclass.GenericIO;
import serverSide.entities.BenchClientProxy;
import serverSide.main.SimulPar;


public class BenchInterface {

    /**
	 * Reference to the bench.
	 */

	private final Bench bench;

	/**
	 * Instantiation of an interface to the bench.
	 *
	 * @param bench reference to the bench
	 */

	public BenchInterface(Bench bench) {
		this.bench = bench;
	}

	/**
	 * Processing of the incoming messages.
	 *
	 * Validation, execution of the corresponding method and generation of the
	 * outgoing message.
	 *
	 * @param inMessage service request
	 * @return outMessage service reply
	 * @throws MessageException if the incoming message is not valid
	 */

	public Message processAndReply(Message inMessage) throws MessageException {
		Message outMessage = null; // outgoing message

		/* validation of the incoming message */

		switch (inMessage.getMsgType()) {
		case MessageType.REQCALLTRIAL:
			if ((inMessage.getRefereeState() < 0) || (inMessage.getRefereeState() > 6))
				throw new MessageException("Invalid referee state!", inMessage);
			break;

		case MessageType.REQCALLCONTESTANTS:
			if ((inMessage.getCoachId() < 0) || (inMessage.getCoachId() >= SimulPar.C))
				throw new MessageException("Invalid coach id!", inMessage);
			else if ((inMessage.getCoachState() < 0) || (inMessage.getCoachState() > 3))
				throw new MessageException("Invalid coach state!", inMessage);
			break;

		case MessageType.REQFOLLOWCOACHADVICE:
			if ((inMessage.getContestantId() < 0) || (inMessage.getContestantId() > SimulPar.P)){
				GenericIO.writeString("Contestant ID"+ inMessage.getContestantId());
				throw new MessageException("Invalid contestant id Bench Interface Req Follow Coach Advice!", inMessage);

			}
			else if ((inMessage.getContestantState() < 0) || (inMessage.getContestantState() > 3))
				throw new MessageException("Invalid contestant state!", inMessage);
			break;

		case MessageType.REQSEATDOWN:
			if ((inMessage.getContestantId() < 0) || (inMessage.getContestantId() > SimulPar.P))
				throw new MessageException("Invalid contestant id Bench Interface Seat Down!", inMessage);
			else if ((inMessage.getContestantState() < 0) || (inMessage.getContestantState() > 3))
				throw new MessageException("Invalid contestant state!", inMessage);
			break;

		case MessageType.REQENDGAME:
			if ((inMessage.getRefereeState() < 0) || (inMessage.getRefereeState() > 6))
				throw new MessageException("Invalid referee state!", inMessage);
			break;

		case MessageType.SHUT: // check nothing

			break;

		default:
			throw new MessageException("Invalid message type!", inMessage);
		}

		/* processing */

		switch (inMessage.getMsgType()) {
		case MessageType.REQCALLTRIAL:
			((BenchClientProxy) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());
			bench.callTrial();
			outMessage = new Message(MessageType.CALLTRIALDONE,
					((BenchClientProxy) Thread.currentThread()).getrefereeState());
			break;

		case MessageType.REQCALLCONTESTANTS:
			((BenchClientProxy) Thread.currentThread()).setcoachId(inMessage.getCoachId());
			((BenchClientProxy) Thread.currentThread()).setcoachState(inMessage.getCoachId(), inMessage.getCoachState());
			//GenericIO.writelnString("Processing message Call Contestants");

			bench.callContestants();

			outMessage = new Message(MessageType.CALLCONTESTANTSDONE,
					((BenchClientProxy)Thread.currentThread()).getcoachId(),
					((BenchClientProxy) Thread.currentThread()).getcoachState());

			//GenericIO.writelnString("Sending message Call Contestants");

			break;

		case MessageType.REQFOLLOWCOACHADVICE:
			((BenchClientProxy) Thread.currentThread()).setcontestantId(inMessage.getContestantId());
			((BenchClientProxy) Thread.currentThread()).setContestantState(inMessage.getContestantState());
			((BenchClientProxy) Thread.currentThread()).setStrength(inMessage.getStrength());
			
			//GenericIO.writelnString("Processing message Req follow coach advice");

			bench.followCoachAdvice();

			outMessage = new Message(MessageType.FOLLOWCOACHADVICEDONE,
					((BenchClientProxy) Thread.currentThread()).getcontestantId(),
					((BenchClientProxy) Thread.currentThread()).getContestantState(),((BenchClientProxy) Thread.currentThread()).getStrength());
			//GenericIO.writelnString("Sending message Follow Coach Advice");

			break;

		case MessageType.REQSEATDOWN:
			//GenericIO.writelnString("Processing message SeatDown");
			((BenchClientProxy) Thread.currentThread()).setcontestantId(inMessage.getContestantId());
			((BenchClientProxy) Thread.currentThread()).setContestantState(inMessage.getContestantState());
			((BenchClientProxy) Thread.currentThread()).setStrength(inMessage.getStrength());

			bench.seatDown();

			outMessage = new Message(MessageType.SEATDOWNDONE,
					((BenchClientProxy) Thread.currentThread()).getcontestantId(),
					((BenchClientProxy) Thread.currentThread()).getContestantState(),((BenchClientProxy) Thread.currentThread()).getStrength() );

			//GenericIO.writelnString("Sending message SeatDown");

			break;



		case MessageType.REQENDGAME:
			((BenchClientProxy) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());
			bench.EndGame(inMessage.getEndOfMatch());

			outMessage = new Message(MessageType.ENDGAMEDONE,
				((BenchClientProxy) Thread.currentThread()).getrefereeState());

			break;

		case MessageType.SHUT:
			bench.shutdown();
			outMessage = new Message(MessageType.SHUTDONE);
		}

		return (outMessage);
	}

}
