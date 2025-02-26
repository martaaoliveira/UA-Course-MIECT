package serverSide.sharedRegion;

import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.RefereeSiteClientProxy;
import serverSide.main.SimulPar;


public class refereeSiteInterface {
    /**
	 * Reference to the refereeSite.
	 */

	private final refereeSite refereeSite;

	/**
	 * Instantiation of an interface to the bar.
	 *
	 * @param refereeSite reference to the bar
	 */

	public refereeSiteInterface(refereeSite refereeSite) {
		this.refereeSite = refereeSite;
	}

	/**
	 * Processing of the incoming messages.cd 
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
		case MessageType.REQANNOUNCENEWGAME:
			if ((inMessage.getRefereeState() < 0) || (inMessage.getRefereeState() > 6))
				throw new MessageException("Invalid referee state!", inMessage);
			break;
		case MessageType.REQDECLAREGAMEWINNER:
			if ((inMessage.getRefereeState() < 0) || (inMessage.getRefereeState() > 6))
				throw new MessageException("Invalid referee state!", inMessage);
			break;

		case MessageType.REQDECLAREMATCHWINNER:
			if ((inMessage.getRefereeState() < 0) || (inMessage.getRefereeState() > 6))
				throw new MessageException("Invalid referee state!", inMessage);
			break;
		case MessageType.REQENDOFMATCHREFEREE:
			if ((inMessage.getRefereeState() < 0) || (inMessage.getRefereeState() > 6)){
				//GenericIO.writelnString("Referee State "+ inMessage.getRefereeState());
				throw new MessageException("Invalid referee state!", inMessage);
			}

			break;

		case MessageType.REQENDOFMATCHCOACH:
			if ((inMessage.getCoachId() < 0) || (inMessage.getCoachId() > SimulPar.C))
			throw new MessageException("Invalid Coach ID!", inMessage);
			break;



		case MessageType.REQENDOFMATCHCONTESTANT:
			if ((inMessage.getContestantId() < 0) || (inMessage.getContestantId() > 10))
			throw new MessageException("Invalid Contestant id! End of Match", inMessage);
			if ((inMessage.getContestantState() < 0) || (inMessage.getContestantState() > 3))
			throw new MessageException("Invalid Contestant State End of Match", inMessage);
			break;
		case MessageType.ENDOPREFEREE:
			if ((inMessage.getRefereeState() < 0) || (inMessage.getRefereeState() > 6))
				throw new MessageException("Invalid referee state!", inMessage);{
			}
		break;

		case MessageType.SHUT: // check nothing
			break;
		default:
			throw new MessageException("Invalid message type!", inMessage);
		}

		/* processing */

		switch (inMessage.getMsgType()) {
			case MessageType.REQANNOUNCENEWGAME:
				((RefereeSiteClientProxy) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());
				refereeSite.announceNewGame();
				// form 6 (type, id , state, )
				outMessage = new Message(MessageType.ANNOUNCENEWGAMEDONE,((RefereeSiteClientProxy) Thread.currentThread()).getrefereeState());
				break;
			case MessageType.REQDECLAREGAMEWINNER:
				((RefereeSiteClientProxy) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());
				boolean end_match=refereeSite.declareGameWinner(inMessage.position_rope());
				// form 4 (type, id , state)
				outMessage = new Message(MessageType.DECLAREGAMEWINNERDONE,((RefereeSiteClientProxy) Thread.currentThread()).getrefereeState(),end_match);
				break;

			case MessageType.REQDECLAREMATCHWINNER:
				((RefereeSiteClientProxy) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());
				refereeSite.declareMatchWinner();
				outMessage = new Message(MessageType.DECLAREMATCHWINNERDONE,((RefereeSiteClientProxy) Thread.currentThread()).getrefereeState());
				break;

			case MessageType.REQENDOFMATCHCOACH:
				RefereeSiteClientProxy coachProxy = (RefereeSiteClientProxy) Thread.currentThread();
				//GenericIO.writelnString("Processing Coach end of match");
				coachProxy.setcoachState(coachProxy.getcoachId(), inMessage.getCoachState());
				boolean can_end_match=refereeSite.getEndOfMatch();
				outMessage = new Message(MessageType.ENDOFMATCHCOACHDONE, coachProxy.getcoachId(), coachProxy.getcoachState(),can_end_match);
				//GenericIO.writelnString("End of Match Coach processing done " + outMessage.toString());

				break;

			case MessageType.REQENDOFMATCHREFEREE:
				RefereeSiteClientProxy RefereeProxy = (RefereeSiteClientProxy) Thread.currentThread();
				//GenericIO.writelnString("Processing Referee end of match");
				RefereeProxy.setrefereeState(RefereeProxy.getrefereeState());
				end_match=refereeSite.getEndOfMatch();
				outMessage = new Message(MessageType.ENDOFMATCHREFEREEDONE, RefereeProxy.getrefereeState(),end_match);
				//GenericIO.writelnString("End of Match Coach processing done " + outMessage.toString());

			break;
			case MessageType.REQENDOFMATCHCONTESTANT:
				RefereeSiteClientProxy ContestantProxy = (RefereeSiteClientProxy) Thread.currentThread();
				//GenericIO.writelnString("Processing Coach end of match");
				ContestantProxy.setContestantState(ContestantProxy.getContestantState());
				end_match=refereeSite.getEndOfMatch();
				outMessage = new Message(MessageType.ENDOFMATCHCONTESTANTDONE, ContestantProxy.getcontestantId(), ContestantProxy.getContestantState(),end_match);
				//GenericIO.writelnString("End of Match Contestant processing done " + outMessage.toString());

				break;
			case MessageType.ENDOPREFEREE:
				((RefereeSiteClientProxy) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());
				//refereeSite.endOperation();
				outMessage = new Message(MessageType.ENDOPDONEREFEREE,((RefereeSiteClientProxy) Thread.currentThread()).getrefereeState());
				break;

			case MessageType.SHUT:
				refereeSite.shutdown();
				outMessage = new Message(MessageType.SHUTDONE);
				break;

		}

		return (outMessage);
	}
}
