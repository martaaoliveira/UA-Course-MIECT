package serverSide.sharedRegion;

import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.main.SimulPar;

public class GeneralRepoInterface {
    
    
    /**
	 * Reference to the general repository.
	 */

	private final GeneralRepo repos;

	/**
	 * Instantiation of an interface to the general repository.
	 *
	 * @param repos reference to the general repository
	 */

	public GeneralRepoInterface(GeneralRepo repos) {
		this.repos = repos;
	}


	/**
	 * process and reply incoming messages
	 * @param inMessage incoming message
	 * @return outMessage outgoing message
	 * @throws MessageException exceptions for wrong parameters
	 */
    public Message processAndReply(Message inMessage) throws MessageException {
		Message outMessage = null; // outgoing message

		/* validation of the incoming message */

		switch (inMessage.getMsgType()) {
			case MessageType.SETNFIC:
				if (inMessage.getLogFName() == null)
					throw new MessageException("Invalid file name!", inMessage);
				break;
			case MessageType.SETPOSITIONROPE:
				break;
			case MessageType.SETCOACHSTATE:
				if ((inMessage.getCoachId() < 0) || (inMessage.getCoachId() >= SimulPar.C))
					throw new MessageException("Invalid coach id!", inMessage);
				else if ((inMessage.getCoachState() < 0) || (inMessage.getCoachState() > 2))
					throw new MessageException("Invalid coach state!", inMessage);
				break;
			case MessageType.SETOUTCOMEDETAILS:
				break;
			
			case MessageType.SETCONTESTANTSTATESTRENGTH:
				if ((inMessage.getContestantId() < 0) || (inMessage.getContestantId() > SimulPar.P))
					throw new MessageException("Invalid contestant id!", inMessage);
				else if ((inMessage.getContestantState() < 0) || (inMessage.getContestantState() > 6))
					throw new MessageException("Invalid contestant state!", inMessage);
				//else if ((inMessage.getStrength() < 0) || (inMessage.getStrength() > 10) )
				//	throw new MessageException("Invalid strength!", inMessage);
				break;
			
			case MessageType.SETREFEREESTATE:
				if ((inMessage.getRefereeState() < 0) || (inMessage.getRefereeState() > 6))
					throw new MessageException("Invalid referee state!", inMessage);
				break;

			case MessageType.SETNUMBERTRIALS:
				if ((inMessage.number_trials() < 0) || (inMessage.number_trials() > 6))
					throw new MessageException("Invalid number of trials!", inMessage);
				break;
			//case MessageType.SETOUTCOMEDETAILS:
			case MessageType.SETREFEREESTATEANDNUMBERGAMES:
				if ((inMessage.getRefereeState() < 0) || (inMessage.getRefereeState() > 6))
					throw new MessageException("Invalid referee state!", inMessage);
				else if ((inMessage.number_trials() < 0) || (inMessage.number_trials() > 6))
					throw new MessageException("Invalid number of games!", inMessage);
				break;

			case MessageType.SETREFEREESTATEANDMATCHWINNER:
				if ((inMessage.getRefereeState() < 0) || (inMessage.getRefereeState() > 6))
					throw new MessageException("Invalid referee state!", inMessage);
				else if ((inMessage.number_games() < 0) || (inMessage.number_games() > 3))
					throw new MessageException("Invalid match winner!", inMessage);
				else if ((inMessage.getscoreTeam1()<0) || (inMessage.getscoreTeam1()>3))
					throw new MessageException("Invalid score team 1!", inMessage);
				else if ((inMessage.getscoreTeam2()<0) || (inMessage.getscoreTeam2()>3))
					throw new MessageException("Invalid score team 2!", inMessage);{
					
				}
				break;


			case MessageType.SHUT: // check nothing
				break;
			default:
				throw new MessageException("Invalid message type!", inMessage);
		}
	

		/* processing */

		switch (inMessage.getMsgType()) {
			case MessageType.SETNFIC:
				repos.initSimul(inMessage.getLogFName());
				outMessage = new Message(MessageType.NFICDONE);
				break;
			case MessageType.SETCOACHSTATE:
				repos.setCoachState(inMessage.getCoachId(), inMessage.getCoachState());
				outMessage = new Message(MessageType.SACK);
				break;
			
			case MessageType.SETPOSITIONROPE:
				repos.setPositionRope(inMessage.position_rope());
				outMessage = new Message(MessageType.SACK);
				break;
			
			
			case MessageType.SETCONTESTANTSTATESTRENGTH:
				repos.setContestantStateAndStrenght(inMessage.getContestantId(), inMessage.getContestantState(), inMessage.getStrength(), inMessage.getteam());
				outMessage = new Message(MessageType.SACK);
				break;
			
			case MessageType.SETREFEREESTATEANDNUMBERGAMES:
				repos.setRefereeStateAndNumberGames(inMessage.getRefereeState(),inMessage.number_games());
				outMessage = new Message(MessageType.SACK);
				break;

			case MessageType.SETREFEREESTATEANDMATCHWINNER:
				repos.setRefereeStateAndMatchWinner(inMessage.getRefereeState(), inMessage.getmatchWinner(), inMessage.getscoreTeam1(),inMessage.getscoreTeam2());
				outMessage = new Message(MessageType.SACK);
				break;

			case MessageType.SETREFEREESTATE:
				repos.setRefereeState(inMessage.getRefereeState());
				outMessage = new Message(MessageType.SACK);
				break;

			case MessageType.SETOUTCOMEDETAILS:
				repos.setOutcomeDetails(inMessage.getOutComeDetails());
				outMessage = new Message(MessageType.SACK);
				break;

			case MessageType.SETNUMBERTRIALS:
				repos.set_nr_trials(inMessage.number_trials());
				outMessage = new Message(MessageType.SACK);
				break;
			case MessageType.SHUT:
				repos.shutdown();
				outMessage = new Message(MessageType.SHUTDONE);
				break; 
			}
			return (outMessage);
		}

	}
