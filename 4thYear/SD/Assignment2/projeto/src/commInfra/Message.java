package commInfra;

import java.io.Serializable;


/**
 *   Internal structure of the exchanged messages.
 *
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */
public class Message implements Serializable {
	
	/**
	 * Serialization key.
	*/

	private static final long serialVersionUID = 2021L;
	
	/**
	 * Message type.
	 */
	private int msgType = -1;
	/**
	 * Name of the logging file.
	 */
	private String fName = null;
	/**
	 * contestant identification
	 */
	private int contestantId=-1;
	/**
	 * contestant state
	 */
	private int contestantState=-1;
	/**
	 * contestant strength
	 */
	private int contestantStrenght;
	/**
	 * coach identification
	 */
    private int coachId=-1;
	/**
	 * coach state
	 */
    private int coachState=-1;
	/**
	 * referee state
	 */
    private int refereeState=-1;
	/**
	 * end of match flag
	 */
	private boolean endOfMatch=false;

	/**
	 * game done flag
	 */
	private boolean game_done=false;
	/**
	 * outcome details
	 */
	private String OutComeDetails="";
	/**
	 * position of the rope
	 */
	private int position_rope=0;
	/**
	 * number of trials done
	 */
	private int number_trials=0;
	/**
	 * number of games done
	 */
	private int number_games=0;
	/**
	 * winner of the match
	 */
	private int matchWinner=0;
	/**
	 * score of team 1
	 */
	private int scoreTeam1=0;
	/**
	 * score of team 2
	 */
	private int scoreTeam2=0;
	/**
	 * team1 initialization
	 */
	private int team=-1;
	/**
	 * get the team number
	 * @return team
	 */
	public int getteam() {
		return team;
	}

	/**
	 * get the team number that won the match
	 * @return match winner
	 */
	public int getmatchWinner() {
		return matchWinner;
	}

	/**
	 * gets the score for team1
	 * @return scoreTeam1
	 */
	public int getscoreTeam1() {
		return scoreTeam1;
	}

	/**
	 * gets the score for team 2
	 * @return scoreTeam2
	 */
	public int getscoreTeam2() {
		return scoreTeam2;
	}

	/**
	 * gets the contestantID
	 * @return contestantID
	 */
	public int getContestantId() {
		return contestantId;
	}

	/**
	 * gets the contestant state
	 * @return contestantState
	 */
	public int getContestantState() {
		return contestantState;
	}

	/**
	 * get the strength of the contestant
	 * @return contestantStrength
	 */
	public int getStrength() {
		return contestantStrenght;
	}

	/**
	 * gets the state of the coach
	 * @return coachState
	 */
	public int getCoachState() {
		return coachState;
	}

	/**
	 * gets the identification of the coach
	 * @return	coachId
	 */
	public int getCoachId() {
		return coachId;
	}

	/**
	 * get the state of the referee
	 * @return refereeState
	 */
	public int getRefereeState() {
		return refereeState;
	}

	/**
	 * flag to see if the game has ended
	 * @return game_done
	 */
	public boolean game_done() {
		return game_done;
	}

	/**
	 * flag to see if the match has ended
	 * @return endOfMatch
	 */
	public boolean getEndOfMatch() {
		return endOfMatch;
	}

	/**
	 * get the position of the rope
	 * @return position_rope
	 */
	public int position_rope() {
		return position_rope;
	}

	/**
	 * gets the number of trials
	 * @return nnumber_trials
	 */
	public int number_trials() {
		return number_trials;
	}

	/**
	 * gets the number of games
	 * @return number_games
	 */
	public int number_games() {
		return number_games;
	}

	/**
	 * gets the outcome details
	 * @return OutcomeDetails
	 */
	public String getOutComeDetails(){
		return OutComeDetails;
	}


	/**
	 * Message instantiation (form 1).
	 *
	 * @param type message type
	 */
	public Message(int type) {
		msgType = type;
	}

	/**
	 * Message instantiation (form 2).
	 *
	 * @param type message type
	 * @param name name of the logging file or String OutcomeDetails from generalRepo 
	 */
	public Message(int type, String name) {
		msgType = type;
		if ((msgType == MessageType.SETOUTCOMEDETAILS)) {
			OutComeDetails = name;
		}
		else{
			fName = name;
		}
		
	}

	/**
	 * Message instantiation(form 3), used for contestants
	 * @param type indicates the type of message
	 * @param id indicates the contestant identification
	 * @param state	indicates the contestant state
	 * @param strength indicates the contestant strength
	 */
	public Message(int type, int id, int state, int strength) {
		msgType = type;
		if((msgType == MessageType.REQSEATDOWN) || (msgType == MessageType.SEATDOWNDONE)){
			contestantId=id;
			contestantState=state;
			contestantStrenght=strength;
		}

		else if(msgType == MessageType.REQGETREADY || msgType == MessageType.GETREADYDONE){
			contestantId=id;
			contestantState=state;
			contestantStrenght=strength;
		}
		else if(msgType == MessageType.REQFOLLOWCOACHADVICE || msgType == MessageType.FOLLOWCOACHADVICEDONE){
			contestantId=id;
			contestantState=state;
			contestantStrenght=strength;
		}
	}



	/**
	 * Message instantiation (form 4), used for players and coaches.
	 *
	 * @param type  message type
	 * @param id    entity identification
	 * @param state entity state
	 */

	 //coaches and players here
	public Message(int type, int id, int state) {
		msgType = type;
		// coach 
		if ((msgType == MessageType.SETCOACHSTATE) 
		|| (msgType == MessageType.REQCALLCONTESTANTS) || (msgType == MessageType.CALLCONTESTANTSDONE)
		|| (msgType == MessageType.REQREVIEWNOTES) || (msgType == MessageType.REVIEWNOTESDONE)
		|| (msgType == MessageType.REQINFORMREFEREE) || (msgType == MessageType.INFORMREFEREEDONE)
		|| (msgType == MessageType.REQENDOFMATCHCOACH))
		{
			coachId = id;
			coachState=state;
		}

		// contestant 
		if ((msgType == MessageType.REQAMDONE) || (msgType == MessageType.AMDONEDONE))
		{
			contestantId = id;
			contestantState=state;
		}

		if(msgType == MessageType.REQDECLAREGAMEWINNER){
			refereeState=id;
			position_rope=state;
		}


		if (msgType == MessageType.REQENDOFMATCHCONTESTANT){
			this.contestantState=state;
			this.contestantId=id;
		}



		if ((msgType == MessageType.SETREFEREESTATEANDNUMBERGAMES))
		{
			refereeState = id;
			number_games=state;
			
		}


		if((msgType == MessageType.ASSERTTRIALDECISIONDONE) ){
			refereeState = id;
			position_rope=state;
			number_trials++;
		}

	}

	/**
	 * Message instantiation (form 5)
	 * @param type indicates message type
	 * @param id indicates referee state
	 * @param game_done	indicates if the game is done or not
	 */
	public Message(int type, int id, boolean game_done) {
		msgType = type;

		if(msgType == MessageType.REQGAMEDONE  || msgType == MessageType.GAMEDONEDONE){
			refereeState = id;
			this.game_done=game_done;			
		}

		if(msgType == MessageType.DECLAREGAMEWINNERDONE){
			refereeState = id;
			endOfMatch=game_done;
		}

		if(msgType == MessageType.REQENDGAME || (msgType == MessageType.ENDGAMEDONE)){
			refereeState = id;
			endOfMatch=game_done;
		}

		if(msgType == MessageType.ENDOFMATCHREFEREEDONE){
			refereeState = id;
			endOfMatch=game_done;
		}






	}

	/**
	 * message instantiantion(form 6)
	 * @param type message type
	 * @param id referee state
	 * @param state contestant state
	 * @param game_done check if the game is done
	 */
	public Message(int type, int id, int state, boolean game_done) {
		msgType = type;

		if(msgType == MessageType.ENDOFMATCHCOACHDONE){
			refereeState = id;
			endOfMatch=game_done;			
		}

		if(msgType == MessageType.ENDOFMATCHCONTESTANTDONE){
			contestantId = id;
			contestantState=state;
			endOfMatch=game_done;
		}


	}
	/**
	 * Message instantiation (form 7).
	 *
	 * @param type message type
	 * @param id   entity identification or referee state 
	 */

	//receives an id or state or position of the rope or number of trials
	public Message(int type, int id) {
		msgType = type;
		// referee
		if ((msgType == MessageType.REQCALLTRIAL) || (msgType == MessageType.CALLTRIALDONE)
		|| (msgType == MessageType.REQSTARTTRIAL) || (msgType == MessageType.STARTTRIALDONE)
		|| (msgType == MessageType.REQANNOUNCENEWGAME) || (msgType == MessageType.ANNOUNCENEWGAMEDONE)
		|| (msgType == MessageType.REQDECLAREGAMEWINNER) || (msgType == MessageType.DECLAREGAMEWINNERDONE)
		|| (msgType == MessageType.SETREFEREESTATE)
		|| (msgType == MessageType.REQASSERTTRIALDECISION))
		
		{
			refereeState = id;
		}

		else if (msgType == MessageType.REQENDOFMATCHREFEREE){
			refereeState=id;

		}

		else if(msgType == MessageType.REQDECLAREMATCHWINNER || (msgType == MessageType.DECLAREMATCHWINNERDONE)){
			refereeState = id;
		}

		//endoperation contestant
		else if((msgType == MessageType.ENDOPCONTESTANT) || (msgType == MessageType.ENDOPDONECONTESTANT))
		
		{
			contestantId = id;
		}

		//endoperation coach
		else if((msgType == MessageType.ENDOPCOACH) || (msgType == MessageType.ENDOPDONECOACH))

		{
			coachId = id;
		}

		//setPositionRope
		else if((msgType == MessageType.SETPOSITIONROPE))

		{
			position_rope = id;
		}

		//setPositionRope
		else if((msgType == MessageType.SETNUMBERTRIALS))

		{
			number_trials = id;
		}
	}

	/**
	 * Message instantiation (form 8).
	 *
	 * @param type  message type
	 * @param state_id indicates the referee state
	 * @param matchWinner indicates the winner of the match
	 * @param scoreTeam1 indicates the score of team 1
	 * @param scoreTeam2 indicates the score of team 2
	 */

	public Message(int type, int state_id, int matchWinner , int scoreTeam1, int scoreTeam2 ) {
		msgType = type;
		if((msgType == MessageType.SETREFEREESTATEANDMATCHWINNER))

		{
			refereeState = state_id;
			this.matchWinner = matchWinner;
			this.scoreTeam1 = scoreTeam1;
			this.scoreTeam2 = scoreTeam2;
		}

		//outMessage = new Message(MessageType.SETCONTESTANTSTATESTRENGTH, id, state, strength, team);

		else if((msgType == MessageType.SETCONTESTANTSTATESTRENGTH))

		{
			contestantId = state_id;
			contestantState=matchWinner;
			contestantStrenght=scoreTeam1;
			team=scoreTeam2;
		}
	}

	

	/**
	 * Getting message type.
	 *
	 * @return message type
	 */
	public int getMsgType() {
		return (msgType);
	}

	/**
	 * Getting name of logging file.
	 *
	 * @return name of the logging file
	 */

	public String getLogFName() {
		return (fName);
	}


}
