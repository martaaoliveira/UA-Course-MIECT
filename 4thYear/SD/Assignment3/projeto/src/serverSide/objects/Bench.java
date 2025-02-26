package serverSide.objects;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import clientSide.entities.CoachState;
import clientSide.entities.ContestantState;
import clientSide.entities.RefereeState;
import genclass.GenericIO;
import interfaces.BenchInterface;
import interfaces.GeneralRepoInterface;
import interfaces.ReturnInt;
import serverSide.main.ServerBench;
import serverSide.main.SimulPar;

public class Bench implements BenchInterface {
     /**
     * variable to keep track if players are in position
     */
    private int inPosition=0;
    /**
     * variable to keep track if both coaches have called their contestants.
     */

    private int callContestants;
    /**
     * variable to keep track  the number of players that are seated
     */
    private int seat;
    /**
     * boolean to indicate if the trial has been called
     */
    private boolean callTrial;
    /**
     * Array of coaches
     */
    private final Thread [] coach;

    /**
     * boolean to indicate if its the first game
     */

    private boolean firstGame=false;
    /**
     * List of team members per coach
     */
    private ArrayList<Integer>[]teamMembers;


    /**
     * Priority queues per coach to manage contestant strengths
     */
    
    //private PriorityQueue<Integer>[] priorityQueues;

    /**
     * boolean to check if the match has ended
     */
    private boolean matchEnded;
    /**
     * Array of contestants
    */

    private final Thread [] contestants;
    /**
     * GeneralRepo instantiation
     */
    private final GeneralRepoInterface repos;

    /**
     * number of entities
     */
    private int nEntities = 0;

    /**
     * stores the state of the competitors
     */
    private boolean[] isPlaying;

    /**
     * stores the strenght of the contestant
     */
    private int [] strength;

    /**
     * random number to select the players that are going to be selected
     */
    private final Random random;

    /**
     * Bench constructor
     * @param repos General Repo Stub
     */
    // Constructor
    @SuppressWarnings("unchecked")
    public Bench(GeneralRepoInterface  repos) {
        this.repos = repos;
        this.callTrial=false;
        this.callContestants=0;
        this.seat=0;
        matchEnded=false;
        nEntities=0;
        coach = new Thread [SimulPar.C];
        contestants = new Thread[SimulPar.P];
        teamMembers = new ArrayList[SimulPar.C];
        //priorityQueues = new PriorityQueue[SimulPar.C];
        this.random = new Random();
        this.isPlaying = new boolean[SimulPar.P];  // Inicializar o array de estados de jogo
        this.strength = new int[SimulPar.P];

        for (int i = 0; i < SimulPar.C; i++){
            coach[i] = null;
            teamMembers[i]= new ArrayList<>();
            //priorityQueues[i] = new PriorityQueue<>();
        }

        for (int i = 0; i < SimulPar.P; i++){
            contestants[i] = null;
            isPlaying[i] = false;  
            //strength[i]=null;

        }    

    }



   /**
    * set playing flag
     * defines if a contestant is playing or not
     * @param contestantID contestant identification
     * @param playing set a player to play the game
     */
    public void setPlaying(int contestantID, boolean playing) {
        if (contestantID >= 0 && contestantID < isPlaying.length) {
            isPlaying[contestantID] = playing;
        }
    }

    /**
     * getter to get who's playing
     * checks if a contestant is playing or not
     * @param contestantID contestant identification
     * @return checks if a contestant is playing
     */
    public boolean getPlaying(int contestantID) {
        if (contestantID >= 0 && contestantID < isPlaying.length) {
            return isPlaying[contestantID];
        }
        return false;  
    }

    /**
     * close of operations
     * @throws RemoteException
     */
    public synchronized void shutdown() throws RemoteException {
        nEntities++;
        if(nEntities==3)
		    ServerBench.shutdown(); 
		notifyAll(); 
	}

    /**
     * operation call trial
     * called by the referee to tells that a trial will take place
     * @return the state of the teams
     * @throws RemoteException
     */
    @Override
    public synchronized int callTrial() throws RemoteException {
        
        firstGame = true;

        while (seat<SimulPar.P){
            try{
                wait();
            }
            catch (Exception exception) {
            }
        }

        callTrial=true;

        try{
            repos.setRefereeState(RefereeState.TEAMS_READY);
        }
        catch (RemoteException e)
		{ 
			GenericIO.writelnString (" Referee remote exception on Call Trial - setRefereeState: " + e.getMessage ());
			System.exit (1);
		}

        //repos.setRefereeState(RefereeState.TEAMS_READY);
        notifyAll();
        return RefereeState.TEAMS_READY;
    }

   /**
    * operation select contestants for trial
     * Selects 3 random contestants from the team of the specified coach.
     * @param coachID the coach ID
     * @return List of selected contestants
     */
    private List<Integer> selectContestantsForTrial(int coachID, PriorityQueue<Integer> priorityQueue) {
        List<Integer> selectedContestants = new ArrayList<>();
        List<Integer> shuffledList = new ArrayList<>(priorityQueue);
        Collections.shuffle(shuffledList, random);
        for (int i = 0; i < 3 && !shuffledList.isEmpty(); i++) {
            selectedContestants.add(shuffledList.remove(0));
        }
        priorityQueue.clear();
        priorityQueue.addAll(shuffledList);
        return selectedContestants;
    }


    /**
     * operation call contestants
     * Call contestants to play the game
     * @param coachID coach identification
     * @return state of the coach
     * @throws RemoteException
     */
    @Override
    public synchronized int callContestants(int coachID) throws RemoteException {
        
        // int coachID = ((Thread) Thread.currentThread())
        //coach[coachID] = (Thread) Thread.currentThread();
        //GenericIO.writelnString("Coach callContestants"+ coachID );

        // Initialize priority queue for selecting strongest contestants
        
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(teamMembers[coachID]);        
        int coachState=CoachState.WAIT_FOR_REFEREE_COMMAND;


        // Get team member IDs from the coach
        List<Integer> teamMemberIDs = new ArrayList<>();
    
        if(coachID==0){
            for(int i=0;i<5;i++){
                teamMemberIDs.add(i);
                //GenericIO.writelnString("Coach Team membersIdin call contestants"+ teamMemberIDs.get(i));
            }
        }
        else{
            for(int i=5;i<10;i++){
                teamMemberIDs.add(i);
                //GenericIO.writelnString("Coach Team membersId in call contestants"+ teamMemberIDs.get(i-5));
            }
        }
        priorityQueue.addAll(teamMemberIDs);


        while (!callTrial) {
            try {
                wait();
            } catch (Exception e) {
            }
        }


        // Select strongest contestants for the trial
        List<Integer> selectedContestants = selectContestantsForTrial(coachID, priorityQueue);
        int team_id=0;
        
        for(int contestantID: teamMemberIDs){
            if(selectedContestants.contains(contestantID)){
                this.setPlaying(contestantID, true);
            }
            else if(!selectedContestants.contains(contestantID)){
                if(contestantID>=0 && contestantID<SimulPar.P_T-1){
                    team_id=1;
                }
                else{
                    team_id=2;
                }
                if(strength[contestantID]<10){
                    strength[contestantID]= strength[contestantID]+1;
                    try
                    { 
                        repos.setContestantStateAndStrenght(contestantID, ContestantState.SEAT_AT_THE_BENCH, strength[contestantID],team_id);
                    }
                    catch (RemoteException e)
                    { 
                        GenericIO.writelnString ("Contestant " + contestantID + " remote exception on SeatDown - setContestantStateAndStrenght: " + e.getMessage ());
                        System.exit (1);
                    }
                }
            }
            }


        if (!matchEnded) {
            //coach[coachID].setcoachState(coachID, CoachState.ASSEMBLE_TEAM);
            try
            { 
                repos.setCoachState(coachID, CoachState.ASSEMBLE_TEAM);
                coachState=CoachState.ASSEMBLE_TEAM;
            }
            catch (RemoteException e)
            { 
                GenericIO.writelnString ("Coach " + coachID + " remote exception on CallTrial - setCoachState: " + e.getMessage ());
                System.exit (1);
            }
        }

        notifyAll(); // Notify waiting threads 

        // Check if both coaches have called their contestants
        callContestants++;
        if (callContestants == SimulPar.C) {
            callContestants = 0;
            callTrial = false;
        }
        
        return coachState;
    }

    /**
     * operation follow coach advice
     * Contestants follow the coach advice
     * @param contestantID contestant identification
     * @param strengthValue  contestant strength
     * @return contestants state
     * @throws RemoteException
     */
    @Override
    public synchronized int followCoachAdvice(int contestantID, int strengthValue) throws RemoteException {

        //int contestantID=((BenchClientProxy) Thread.currentThread ()).getcontestantId();
        //contestants[contestantID]=(Thread)Thread.currentThread ().getStrength();
        strength[contestantID]= strength[contestantID];
        int team_id=-1;
        
        if(contestantID>=0 && contestantID<SimulPar.P_T-1){
            team_id=1;
        }
        else{
            team_id=2;
        }
        seat--;
            
                
        
        try
        { 
            repos.setContestantStateAndStrenght(contestantID, ContestantState.STAND_IN_POSITION,strength[contestantID],team_id);
        }
        catch (RemoteException e)
        { 
            GenericIO.writelnString ("Contestant " + contestantID + " remote exception on SeatDown - setContestantStateAndStrenght: " + e.getMessage ());
            System.exit (1);
        }
        return ContestantState.STAND_IN_POSITION;
    }

    /**
     * Get the number of players that are seated
     * @return seat
     */

     public synchronized int get_nr_seat(){
        return seat;
    }

    /**
     * operation seat down
     * tells contestant to seat on the bench
     * @param contestantID contestant identification
     * @param strengthValue contestant strength
     * @return contestant state and strength
     * @throws RemoteException
     */
    @Override
    public synchronized ReturnInt seatDown(int contestantID,int strengthValue) throws RemoteException {
        //contestants[contestantID]=(Thread)Thread.currentThread ();
        
        if(firstGame==false)strength[contestantID]= strengthValue;
        else{
            strength[contestantID]= strength[contestantID];
        }

        seat++;

        int team_id=-1;
        
       
        if(contestantID>=0 && contestantID<SimulPar.P_T-1){
            team_id=1;
        }
        else{
            team_id=2;
        }

        try
        { 
            repos.setContestantStateAndStrenght(contestantID, ContestantState.SEAT_AT_THE_BENCH, strength[contestantID], team_id);
        }
        catch (RemoteException e)
        { 
            GenericIO.writelnString ("Contestant " +contestantID + " remote exception on Call contestants - setContestantStateAndStrenght: " + e.getMessage ());
            System.exit (1);
        }
        
        
        if(this.getPlaying(contestantID) && strength[contestantID]>0){
            strength[contestantID]=strengthValue-1;
            try
            { 
                repos.setContestantStateAndStrenght(contestantID, ContestantState.SEAT_AT_THE_BENCH, strength[contestantID],team_id);
            }
            catch (RemoteException e)
            { 
                GenericIO.writelnString ("Contestant " + contestantID + " remote exception on SeatDown - setContestantStateAndStrenght: " + e.getMessage ());
                System.exit (1);
            }
        
        }

     
        if(seat==SimulPar.P)notifyAll();

        this.setPlaying(contestantID,false);

        while (this.getPlaying(contestantID)==false ) {
            try {
                wait();
            } catch (Exception e) {
            }
        }


        return new ReturnInt(strength[contestantID], ContestantState.SEAT_AT_THE_BENCH);
    }

    /**
     * operation en game
     * to check if the game has ended
     * @param EndofMatch check if the match was ended
     * @return state of the referee
     * @throws RemoteException
     */
    @Override
    public synchronized int EndGame(boolean EndofMatch)throws RemoteException {
        while (seat < SimulPar.P) {
            try {
                wait();
            } catch (Exception e) {
            }
        }

        if (EndofMatch) {
            for (int contestantID=0; contestantID<10; contestantID++) {
                    this.setPlaying(contestantID, true);
            }
            callTrial = true;
            matchEnded = true;
            notifyAll();

        }

    
        try
        { 
            repos.setRefereeState(RefereeState.END_OF_A_GAME);
        }
        catch (RemoteException e)
        { 
            GenericIO.writelnString ("Referee  remote exception on EndGame - setRefereeState: " + e.getMessage ());
            System.exit (1);
        }
        
        return RefereeState.END_OF_A_GAME;
    }

    
    
    /**
     * Set the inPosition variable to each contestant
     * @param inPosition position of the player
     */
    public synchronized void setInPosition(int inPosition){
        this.inPosition=inPosition;
    }

    /**
     * Get the inPosition variable of the contestant
     * @return boolean variable inPosition
     */
    public synchronized boolean getInPosition(){
        return SimulPar.P_T == this.inPosition;
    }

}
