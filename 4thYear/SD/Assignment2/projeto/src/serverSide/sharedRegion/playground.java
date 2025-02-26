package serverSide.sharedRegion;

import serverSide.entities.PlaygroundClientProxy;
import serverSide.main.*;
import clientSide.entities.*;
import clientSide.stubs.GeneralRepoStub;

import java.util.*;



/**
 * The Playground class implements the Playground shared region.
 * <p>
 * It is responsible for the synchronization of coaches, contestants, and the referee.
 * <p>
 * Structured as an implicit monitor, it features six synchronization points:
 * <p>
 * - Two blocking points for the referee: one where he waits until the last coach informs him that the teams are ready to start the trial, and another where he waits for the trial to conclude.
 * <p>
 * - Two blocking points for the coaches: one where they wait until their players are ready, and another where they wait for the referee to assert the decision about the trial.
 * <p>
 * - Two blocking points for the contestants: one where they wait for the referee to start the trial, and another where they wait for the referee to assert the decision about the trial.
 * <p>
 */

@SuppressWarnings("unchecked")

public class playground {
  /**
   * GeneralRepo instantiation
   */
  private final GeneralRepoStub repos;
  /**
   * Array of contestants
   */
  private final PlaygroundClientProxy [] contestants;
  /**
   * Array of coaches
   */
  private final PlaygroundClientProxy [] coach;

  /**
   * Variable to keep track of the number of players that are done pulling rope
   */
  private int done;
  /**
   * Variable to keep track of the number of players that are ready to start a new tial
   */
  private int ready;
  /**
   * Variable to keep track of the number of players that are playing
   */
  private int playing;
  /**
   * Variable to keep track of the last coach that informed the referee
   */
  private int lastCoach=0;
  /**
   * Variable to keep track of the position of the rope
   */
  private int position_rope;
  /**
   * Variable to keep track of the number of times the coach has reviewed the notes
   */
  private int reviewNotes;
  /**
   * Variable to keep track of the number of players that are going to seat
   */
  private int goSeat;
  /**
   * Variable to keep track of the number of trials
   */
  private int nr_trials;
  /**
   * Boolean to keep track if the trial was called by the referee to start
   */
  private boolean startTrial;
  /**
   * Boolean to keep track if the referee was informed by the coaches
   */
  private boolean refereeInformed;
  /**
   * Boolean to keep track if the game has ended
   */
  private boolean game_done;
  /**
   * Boolean to keep track if the referee has asserted the trial decision and communicated it to the coaches
   */
  private boolean assertTrialDecisionCoaches;
  /**
   * Boolean to keep track if the referee has asserted the trial decision and communicated it to the contestants
   */
  private boolean assertTrialDecisionContestants;

  /**
   * Boolean to keep track if all players are done playing
   */
  private boolean AllDone;

  private int nEntities = 0;

  /**
   * Priority queues per coach to manage contestant strengths
   */

  private PriorityQueue<PlaygroundClientProxy>[] priorityQueues;

    /**
     * Stores the members of each team
     */
  private ArrayList<PlaygroundClientProxy>[]teamMembers;

    /**
     * array with the strength of each contestant
     */
  int strengthContestant[] = new int[SimulPar.P];

    /**
     * array to store the state of game of each contestant, if they're playing or on the bench
     */
  private boolean[] isPlaying;  // Array para armazenar o estado de jogo dos competidores

  /**
   * Playground constructor
   * @param repos Genereal Repo stub
   */
  public playground(GeneralRepoStub repos) {

    this.repos = repos;
    ready=0;
    done=0;
    reviewNotes=0;
    position_rope=0;
    goSeat=0;
    nr_trials=0;
    nEntities=0;
    AllDone=false;
    startTrial=false;
    refereeInformed=false;
    assertTrialDecisionCoaches=false;
    assertTrialDecisionContestants=false;
    game_done=false;

    contestants = new PlaygroundClientProxy [SimulPar.P];
    priorityQueues = new PriorityQueue[SimulPar.C];
    this.isPlaying = new boolean[SimulPar.P]; 

    for (int i = 0; i < SimulPar.P; i++){
        contestants[i] = null;
        //strengthContestant[i] = fixedStrengths.get(i);
      }

    coach = new PlaygroundClientProxy[SimulPar.C];
    teamMembers = new ArrayList[SimulPar.C];

    for (int i = 0; i < SimulPar.C; i++){
        coach[i] = null;
        priorityQueues[i] = new PriorityQueue<>();
        teamMembers[i]= new ArrayList<>();

    }


  }

    /**
     * defines that a contestant is going to play based on its identification
     * @param contestantID contestant identification
     * @param playing variable to define if he's playing or not
     */
  public void setPlaying(int contestantID, boolean playing) {
    if (contestantID >= 0 && contestantID < isPlaying.length) {
        isPlaying[contestantID] = playing;
    }
}

    /**
     * check if a contestant is playing
     * @param contestantID identification of the contestant
     * @return if he's playing return he will be on the isPlaying array, else return false
     */
    public boolean getPlaying(int contestantID) {
    if (contestantID >= 0 && contestantID < isPlaying.length) {
        return isPlaying[contestantID];
    }
    return false;  
}



  /**
   * Get the number of trials
   * @return number of trials
   */
  public int get_nr_trials(){
    return this.nr_trials;
  }


  /**
   * Operation GetReady
   *It is called by Players to get ready if they are selected to play the trial. They wait until Trial is started. The players notify the coaches that they are ready.
  */
  public synchronized void getReady() {

    int contestantID=((PlaygroundClientProxy) Thread.currentThread ()).getcontestantId();
    contestants[contestantID]=(PlaygroundClientProxy)Thread.currentThread ();
    contestants[contestantID].setplaying(true);
    strengthContestant[contestantID]=(contestants[contestantID].getStrength());
    //GenericIO.writelnString("Get ready Contestant"+ contestantID + "Strenghth" + strengthContestant[contestantID]);

    ready++;

    if(ready==SimulPar.P_T) notifyAll();

    while(startTrial==false) {
      try {
          wait(); //wait for all contestants to be ready
      } catch (InterruptedException e) {
      }
    }

    playing++;

    if(playing == SimulPar.P_T){
      playing=0;
      startTrial=false;
    }
    int team_id=-1;

    if(contestantID>=0 && contestantID<5){
        team_id=1;
    }
    else{
        team_id=2;
    }
    
    this.setPlaying(contestantID, true); 



    contestants[contestantID].setContestantState (ContestantState.DO_YOUR_BEST);
    //GenericIO.writelnString("Get ready Contestant"+ contestantID + "Strenghth" + contestants[contestantID].getStrength()+ "Team ID" + team_id);
    repos.setContestantStateAndStrenght(contestantID, ContestantState.DO_YOUR_BEST,contestants[contestantID].getStrength(),team_id);


  }

  /**
   * Operation informReferee
   * It is called by coaches to inform the referee that the teams are ready to proceed
   * the trial only starts when the last coach informs the referee
   */
  public synchronized void informReferee() {

    int coachId = ((PlaygroundClientProxy) Thread.currentThread()).getcoachId();
    coach[coachId] = (PlaygroundClientProxy) Thread.currentThread();

    while (ready < SimulPar.P_T) {
      try {
          wait();
      } catch (InterruptedException e) { }
    }

    lastCoach++;

    if(lastCoach==SimulPar.C){
      refereeInformed = true;
      ready = 0;
      lastCoach = 0;
      notifyAll();
    }

    ((PlaygroundClientProxy) Thread.currentThread()).setcoachState(coachId,CoachState.WATCH_TRIAL);
    repos.setCoachState(coachId,CoachState.WATCH_TRIAL);

}

/**
 * Operation startTrial
 * It is called by the referee to Start the trial when the coach informs that the teams are ready
 */
public synchronized void startTrial() {

  while(refereeInformed==false){
      try{

          wait();
      }
      catch(InterruptedException e){
      }
  }

  startTrial=true;
  refereeInformed=false;


  if(game_done==true){
    game_done=false;
    position_rope=0;
    nr_trials=1;
    repos.setPositionRope(position_rope);
  }

  else{
    nr_trials++;

  }
  repos.set_nr_trials(nr_trials);
  ((PlaygroundClientProxy) Thread.currentThread ()).setrefereeState(RefereeState.WAIT_FOR_TRIAL_CONCLUSION);
  repos.setRefereeState(RefereeState.WAIT_FOR_TRIAL_CONCLUSION);

  notifyAll();


}

  /**
   * Operation amDone
   * It is called by the players to signal that they have finished playing/pulling the rope
   */
  public synchronized void amDone() {

    int contestantID=((PlaygroundClientProxy) Thread.currentThread ()).getcontestantId();
    contestants[contestantID]=(PlaygroundClientProxy)Thread.currentThread ();
    done++;
    
    if(done==SimulPar.P_T){
      done=0;
      AllDone=true;
      notifyAll();
    }


    while(assertTrialDecisionContestants==false){
      try{
        wait();
      }
      catch(InterruptedException e){}
    }

    goSeat++;

    if(goSeat==SimulPar.P_T){
      goSeat = 0;
      assertTrialDecisionContestants=false;
    }


}


    /**
     * referee makes a decision based on the game
     * @return rope position
     */
    public synchronized int assertTrialDecision() {
  int strengthSum1 = 0;
  int strengthSum2 = 0;
  List<PlaygroundClientProxy> team1Members = new ArrayList<>();
  List<PlaygroundClientProxy> team2Members = new ArrayList<>();


  // Certifique-se de que todos os jogadores fizeram sua parte
  while (!AllDone) {
      try {
          wait();
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
      }
  }

  AllDone = false;

  List<Integer> team1MemberIDs = new ArrayList<>();
  List<Integer> team2MemberIDs = new ArrayList<>();


  for(int i=0;i<5;i++){
          team1MemberIDs.add(i);
  }


  for(int i=5;i<10;i++){
          team2MemberIDs.add(i);
  }


  for (Integer contestantID : team1MemberIDs) {
      //GenericIO.writelnString("IDS  " + contestantID);
      if (contestants[contestantID]!=null &&this.getPlaying(contestantID)) {
          team1Members.add(contestants[contestantID]);
          strengthSum1 +=  strengthContestant[contestantID];
          //GenericIO.writelnString("Strenght 1 " + strengthSum1);
      }
      this.setPlaying(contestantID,false);
  }

  for (Integer contestantID : team2MemberIDs) {
      if (contestants[contestantID]!=null &&this.getPlaying(contestantID)) {
          team2Members.add(contestants[contestantID]);
          strengthSum2 +=  strengthContestant[contestantID];
          //GenericIO.writelnString("Strenght 2 " + strengthSum2);
      }
      this.setPlaying(contestantID,false);

  }

  position_rope += strengthSum1 - strengthSum2;
  //GenericIO.writelnString("position rope" + position_rope);
  repos.setPositionRope(position_rope);

  boolean knockout = Math.abs(position_rope) >= 4;
  boolean maxTrialsReached = nr_trials == 6;

  assertTrialDecisionContestants = true;
  assertTrialDecisionCoaches = true;

  // Anuncia os resultados
  String outcomeMessage = "";

  if (knockout) {
      outcomeMessage = "was won by " + (position_rope > 0 ? "Team 1" : "Team 2") + " by knockout in " + nr_trials + " trials.";
      game_done = true;
      repos.setOutcomeDetails(outcomeMessage);
  } else if (maxTrialsReached) {
      game_done = true;
      if (position_rope == 0) {
          outcomeMessage = "was a draw.";
      } else {
          outcomeMessage = "was won by " + (position_rope > 0 ? "Team 1" : "Team 2") + " by points.";
      }
      repos.setOutcomeDetails(outcomeMessage);
  }

  // Notifica que a decisão foi tomada
  notifyAll();
  return position_rope;
}



/**
 * It is called by the coaches to review the notes after the trial decision has been made by the referee
 */
public synchronized void reviewNotes() {

  int coachID=((PlaygroundClientProxy) Thread.currentThread ()).getcoachId();
  coach[coachID]=(PlaygroundClientProxy) Thread.currentThread ();


  while(assertTrialDecisionCoaches==false ){
      try {
          wait();
      } catch (InterruptedException e) { }

  }

  reviewNotes++;


  if(reviewNotes==SimulPar.C){
    reviewNotes = 0;
    assertTrialDecisionCoaches=false;
  }

  coach[coachID].setcoachState(coachID,CoachState.WAIT_FOR_REFEREE_COMMAND);
  repos.setCoachState(coachID, coach[coachID].getcoachState());



}

  /**
   * A player is done playing
   * @param done defines that a player is done playing
   */
  public synchronized void setPlayerDone(int done){
    this.done=done;
  }

  /**
   * Get the number of players that are done playing on the game, if the number of players that are done is equal to the number of players playing the trial, then the trial is over
   * @return done
   */
  public synchronized boolean getPlayerDone(){
    return SimulPar.P_T==this.done;
  }
  /**
   * define the player status to ready
   * @param ready status of the player
   */
  public synchronized void setReadytatus(int ready){
    this.ready=ready;
  }
  /**
   * Get the player ready status, if all the player are ready to play the trial, then the trial starts
   * @return flag of players that are ready to play
   */
  public synchronized boolean getReadyStatus(){
    return SimulPar.P_T == this.ready;
  }

  /**
   * Game is done
   * @return flag that tells if the game is done or not
   */
  public synchronized boolean game_done(){
    return this.game_done;
  }


/**
  * Operation server shutdown.
  *
  * New operation.
  */

   public synchronized void shutdown() {
    nEntities++;
    if(nEntities==3)
		  ServerPlayground.waitConnection = false;
		notifyAll(); 
	}



}