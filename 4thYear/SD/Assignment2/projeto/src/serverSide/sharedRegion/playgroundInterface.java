package serverSide.sharedRegion;

import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.PlaygroundClientProxy;
import serverSide.main.SimulPar;

public class playgroundInterface {


    private final playground playground;

    /**
     * playground interface constructor
     * @param playground playground
     */
    public playgroundInterface(playground playground) {
        this.playground = playground;
    }

    /**
     * process and reply the incoming messages
     * @param inMessage incoming message
     * @return outgoing message
     * @throws MessageException if the incoming message is not valid
     */
    public Message processAndReply(Message inMessage) throws MessageException {
        Message outMessage = null; // outgoing message

        /* validation of the incoming message */

        switch (inMessage.getMsgType()) {
        case MessageType.REQSTARTTRIAL:
            if ((inMessage.getRefereeState() < 0 ) || (inMessage.getRefereeState() > 6))
                throw new MessageException("Invalid Referee state!", inMessage);
            break;

        case MessageType.REQGETREADY:
            if ((inMessage.getContestantId() < 0) || (inMessage.getContestantId() > SimulPar.P))
                throw new MessageException("Invalid contestant id Get Readdy!", inMessage);
            else if ((inMessage.getContestantState() < 0) || (inMessage.getContestantState() > 6))
                throw new MessageException("Invalid contestant state Get Ready!", inMessage);
            break;

        case MessageType.REQINFORMREFEREE:
            if ((inMessage.getCoachId() < 0) || (inMessage.getCoachId() >= SimulPar.C))
                throw new MessageException("Invalid Coach id!", inMessage);
            break;

        case MessageType.REQAMDONE:
            if ((inMessage.getContestantId() < 0) || (inMessage.getContestantId() > SimulPar.P))
                throw new MessageException("Invalid contestant id Am done!", inMessage);
            else if ((inMessage.getContestantState() < 0) || (inMessage.getContestantState() > 6))
                throw new MessageException("Invalid contestant state Am Done!", inMessage);
            break;

        case MessageType.REQASSERTTRIALDECISION:
            if ((inMessage.getRefereeState() < 0) || (inMessage.getRefereeState() > 6))
                throw new MessageException("Invalid contestant state Assert Trial Decision!", inMessage);
            break;

        case MessageType.REQREVIEWNOTES:
            if ((inMessage.getCoachId() < 0) || (inMessage.getCoachId() >= SimulPar.C))
                throw new MessageException("Invalid coach id!", inMessage);
            else if ((inMessage.getCoachState() < 0) || (inMessage.getCoachState() > 6))
                throw new MessageException("Invalid coach state!", inMessage);
            break;

        case MessageType.REQGAMEDONE:
            if ((inMessage.getRefereeState() < 0) || (inMessage.getRefereeState() > 6))
                throw new MessageException("Invalid referee state!", inMessage);
            break;

        case MessageType.ENDOPCOACH:
            if ((inMessage.getCoachId() < 0) || (inMessage.getCoachId() >= SimulPar.C))
                throw new MessageException("Invalid coach id!", inMessage);
            break;

        case MessageType.SHUT: // check nothing
            break;

        default:
            throw new MessageException("Invalid message type in validaion playground!", inMessage);
        }

        /* processing */

        switch (inMessage.getMsgType()) {
        case MessageType.REQSTARTTRIAL:
            ((PlaygroundClientProxy) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());
            playground.startTrial();
            outMessage = new Message(MessageType.STARTTRIALDONE,
                    ((PlaygroundClientProxy) Thread.currentThread()).getrefereeState());
            break;

        case MessageType.REQGETREADY:
        	//GenericIO.writelnString("Processing message Get Ready");
			((PlaygroundClientProxy) Thread.currentThread()).setStrength(inMessage.getStrength());
            ((PlaygroundClientProxy) Thread.currentThread()).setcontestantId(inMessage.getContestantId());
            ((PlaygroundClientProxy) Thread.currentThread()).setContestantState(inMessage.getContestantState());
            playground.getReady();
            outMessage = new Message(MessageType.GETREADYDONE,
                    ((PlaygroundClientProxy)Thread.currentThread()).getcontestantId(),
                    ((PlaygroundClientProxy) Thread.currentThread()).getContestantState(), ((PlaygroundClientProxy) Thread.currentThread()).getStrength());

            //GenericIO.writelnString("Message Get Ready processed");

            break;

        case MessageType.REQINFORMREFEREE:
            ((PlaygroundClientProxy) Thread.currentThread()).setcoachId(inMessage.getCoachId());
            ((PlaygroundClientProxy) Thread.currentThread()).setcoachState(inMessage.getCoachId(), inMessage.getCoachState());
            playground.informReferee();
            outMessage = new Message(MessageType.INFORMREFEREEDONE,
                    ((PlaygroundClientProxy)Thread.currentThread()).getcoachId(),
                    ((PlaygroundClientProxy) Thread.currentThread()).getcoachState());
            break;

        case MessageType.REQAMDONE:
            ((PlaygroundClientProxy) Thread.currentThread()).setcontestantId(inMessage.getContestantId());
            ((PlaygroundClientProxy) Thread.currentThread()).setContestantState(inMessage.getContestantState());
            playground.amDone();
            outMessage = new Message(MessageType.AMDONEDONE,
                    ((PlaygroundClientProxy)Thread.currentThread()).getcontestantId(),
                    ((PlaygroundClientProxy) Thread.currentThread()).getContestantState());
            break;

        case MessageType.REQASSERTTRIALDECISION:
            ((PlaygroundClientProxy) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());
            //GenericIO.writelnString("Processing message  AssertTrialDecision");

            int position_rope = playground.assertTrialDecision();

            outMessage = new Message(MessageType.ASSERTTRIALDECISIONDONE,
                    ((PlaygroundClientProxy) Thread.currentThread()).getrefereeState(), position_rope);

            //GenericIO.writelnString("message  AssertTrialDecision processed");

            break;


        case MessageType.REQREVIEWNOTES:
            ((PlaygroundClientProxy) Thread.currentThread()).setcoachId(inMessage.getCoachId());
            ((PlaygroundClientProxy) Thread.currentThread()).setcoachState(inMessage.getCoachId(), inMessage.getCoachState());
            playground.reviewNotes();
            outMessage = new Message(MessageType.REVIEWNOTESDONE,
                    ((PlaygroundClientProxy)Thread.currentThread()).getcoachId(),
                    ((PlaygroundClientProxy) Thread.currentThread()).getcoachState());
            break;

        case MessageType.REQGAMEDONE:
            //GenericIO.writelnString("Processing message  GameDone");

            ((PlaygroundClientProxy) Thread.currentThread()).setrefereeState(inMessage.getRefereeState());
            boolean game_done= playground.game_done();
            outMessage = new Message(MessageType.GAMEDONEDONE,
                ((PlaygroundClientProxy) Thread.currentThread()).getrefereeState(),game_done);

            //GenericIO.writelnString("message  GameDone processed");

            break;

        case MessageType.SHUT:
            playground.shutdown();
            outMessage = new Message(MessageType.SHUTDONE);
            break;
        }

        return (outMessage);
    }

}
