package sharedRegion;

import main.SimulPar;
import entities.*;
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


public class playground {
  /**
   * GeneralRepo instantiation
   */
  private final GeneralRepo repos;
  /**
   * Array of contestants
   */
  private final Contestant [] contestants;
  /**
   * Array of coaches
   */
  private final Coach [] coach;

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


  /**
 * Priority queues per coach to manage contestant strengths
 */
  
  private PriorityQueue<Contestant>[] priorityQueues;

  private ArrayList<Contestant>[]teamMembers;
 
  //private Contestant[] contestants;
  //private final Bench bench;

  /**
   * Playground constructor
   * @param repos
   */
  @SuppressWarnings("unchecked")
  public playground(GeneralRepo repos) {

    this.repos = repos;
    ready=0;
    done=0;
    reviewNotes=0;
    position_rope=0;
    goSeat=0;
    nr_trials=0;

    AllDone=false;
    startTrial=false;
    refereeInformed=false;  
    assertTrialDecisionCoaches=false;
    assertTrialDecisionContestants=false;
    game_done=false;

    contestants = new Contestant [SimulPar.P]; 

    priorityQueues = new PriorityQueue[SimulPar.C];

    for (int i = 0; i < SimulPar.P; i++){
        contestants[i] = null;

    }

    coach = new Coach[SimulPar.C];
    teamMembers = new ArrayList[SimulPar.C];

    for (int i = 0; i < SimulPar.C; i++){
        coach[i] = null;
        priorityQueues[i] = new PriorityQueue<>();
        teamMembers[i]= new ArrayList<>();

    }
        
    
  }

  /**
   * Get the number of trials
   * @return nr_trials
   */
  public int get_nr_trials(){
    return this.nr_trials;
  }

  // Method to retrieve contestant by ID
    private Contestant getContestantById(int contestantID) {
      for (Contestant contestant : contestants) {
          if (contestant != null && contestant.getcontestantId() == contestantID) {
              return contestant;
          }
      }
      return null;
  }

  /**
   * Operation GetReady
   *It is called by Players to get ready if they are selected to play the trial. They wait until Trial is started. The players notify the coaches that they are ready.
   */
  public synchronized void getReady() {

    int contestantID=((Contestant) Thread.currentThread ()).getcontestantId();
    contestants[contestantID]=(Contestant)Thread.currentThread ();
    
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

    contestants[contestantID].setContestantState (ContestantState.DO_YOUR_BEST);
    repos.setContestantStateAndStrenght(contestantID, ContestantState.DO_YOUR_BEST,contestants[contestantID].getStrength(),contestants[contestantID].getTeamID());


  }

  /**
   * Operation informReferee
   * It is called by coaches to inform the referee that the teams are ready to proceed
   * the trial only starts when the last coach informs the referee
   */
  public synchronized void informReferee() {
    
    int coachId = ((Coach) Thread.currentThread()).getcoachId();
    coach[coachId] = (Coach) Thread.currentThread();

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
    
    ((Coach) Thread.currentThread()).setcoachState(coachId,CoachState.WATCH_TRIAL);
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
  ((Referee) Thread.currentThread ()).setRefereeState(RefereeState.WAIT_FOR_TRIAL_CONCLUSION);
  repos.setRefereeState(RefereeState.WAIT_FOR_TRIAL_CONCLUSION);

  notifyAll();
  

}

  /**
   * Operation amDone
   * It is called by the players to signal that they have finished playing/pulling the rope 
   */
  public synchronized void amDone() {

    int contestantID=((Contestant) Thread.currentThread ()).getcontestantId();
    contestants[contestantID]=(Contestant)Thread.currentThread ();
    
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


private synchronized List<Contestant> selectContestantsForTrial(int coachID) {
  List<Contestant> selectedContestants = new ArrayList<>();
  Iterator<Contestant> iterator = priorityQueues[coachID].iterator();
  int count = 0;
  while (iterator.hasNext() && count < 3) {
      selectedContestants.add(iterator.next());
      count++;
  }
  return selectedContestants;
}



public synchronized int assertTrialDecision() {
  int strengthSum1 = 0;
  int strengthSum2 = 0;
  List<Contestant> team1Members = new ArrayList<>();
  List<Contestant> team2Members = new ArrayList<>();

  try {
      Thread.sleep((long) (1 + 100 * Math.random()));
  } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
  }

  // Certifique-se de que todos os jogadores fizeram sua parte
  while (!AllDone) {
      try {
          wait();
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
      }
  }

  AllDone = false;

  // Obtenha os membros da equipe para cada treinador
  List<Integer> team1MemberIDs = ((Referee) Thread.currentThread()).getContestantID().subList(0, SimulPar.P / 2-1);
  List<Integer> team2MemberIDs = ((Referee) Thread.currentThread()).getContestantID().subList(SimulPar.P / 2, SimulPar.P-1);

  // Adicione os concorrentes à lista da equipe
  for (Integer contestantID : team1MemberIDs) {
      Contestant contestant = getContestantById(contestantID);
      if (contestant != null && contestant.getplaying()) {
          team1Members.add(contestant);
      }
  }

  for (Integer contestantID : team2MemberIDs) {
      Contestant contestant = getContestantById(contestantID);
      if (contestant != null && contestant.getplaying()) {
          team2Members.add(contestant);
      }
  }

  // Soma as forças para cada equipe
  for (Contestant contestant : team1Members) {
      strengthSum1 += contestant.getStrength();
  }

  for (Contestant contestant : team2Members) {
      strengthSum2 += contestant.getStrength();
  }

  position_rope += strengthSum1 - strengthSum2;
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

  int coachID=((Coach) Thread.currentThread ()).getcoachId();
  coach[coachID]=(Coach) Thread.currentThread ();


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
   * @param done
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
   * Set the player status to ready
   * @param ready
   */
  public synchronized void setReadytatus(int ready){
    this.ready=ready;
  }
  /**
   * Get the player ready status, if all the player are ready to play the trial, then the trial starts
   * @return
   */
  public synchronized boolean getReadyStatus(){
    return SimulPar.P_T == this.ready;
  }

  /**
   * Game is done
   * @return
   */
  public synchronized boolean game_done(){
    return this.game_done;
  }



}


