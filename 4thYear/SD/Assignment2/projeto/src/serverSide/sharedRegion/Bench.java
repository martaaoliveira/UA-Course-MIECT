package serverSide.sharedRegion;

import clientSide.entities.*;
import clientSide.stubs.GeneralRepoStub;
import serverSide.entities.BenchClientProxy;
import serverSide.main.ServerBench;
import serverSide.main.SimulPar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The Bench class implements the Bench shared region.
 * <p>
 * It is responsible for the synchronization of coaches, contestants, and the referee.
 * <p>
 * Structured as an implicit monitor, it features three synchronization points:
 * <p>
 * - One blocking point for the referee, where he waits until all contestants are seated before starting a new trial.
 * <p>
 * - One blocking point for the coaches, where they wait for the referee to call a trial.
 * <p>
 * - One blocking point for the contestants, where they wait for the coach to select the team to play.
 */
  @SuppressWarnings("unchecked")

public class Bench {

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
    private final BenchClientProxy [] coach;


    /**
     * List of team members per coach
     */
    private ArrayList<BenchClientProxy>[]teamMembers;


    /**
     * Priority queues per coach to manage contestant strengths
     */
    private PriorityQueue<BenchClientProxy>[] priorityQueues;


    private boolean noPrints;
    /**
     * Array of contestants
    */

    private final BenchClientProxy [] contestants;
    /**
     * GeneralRepo instantiation
     */
    private final GeneralRepoStub  repos;

    /**
     * number of entities
     */
    private int nEntities = 0;

    /**
     * stores the state of the competitors
     */
    private boolean[] isPlaying;

    /**
     * Bench constructor
     * @param repos General Repo Stub
     */
    // Constructor
    public Bench(GeneralRepoStub  repos) {
        this.repos = repos;
        this.callTrial=false;
        this.callContestants=0;
        this.seat=0;
        noPrints=false;
        nEntities=0;
        coach = new BenchClientProxy[SimulPar.C];
        contestants = new BenchClientProxy[SimulPar.P];
        teamMembers = new ArrayList[SimulPar.C];
        priorityQueues = new PriorityQueue[SimulPar.C];
        this.isPlaying = new boolean[SimulPar.P];  // Inicializar o array de estados de jogo

        for (int i = 0; i < SimulPar.C; i++){
            coach[i] = null;
            teamMembers[i]= new ArrayList<>();
            priorityQueues[i] = new PriorityQueue<>();
        }

        for (int i = 0; i < SimulPar.P; i++){
            contestants[i] = null;
            isPlaying[i] = false;  // Inicialmente, nenhum competidor está jogando

        }    

    }

    /**
     * retrieves de contestant identification
     * @param contestantID identification of the contestant
     * @return null or the contestant identification
     */
  // Method to retrieve contestant by ID
    private BenchClientProxy getContestantById(int contestantID) {
        for (BenchClientProxy contestant : contestants) {
            if (contestant != null && contestant.getcontestantId() == contestantID) {
                return contestant;
            }
        }
        return null;
    }

    /**
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
     * checks if a contestant iis playing or not
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
     * Operation CallTrial
     * It is called by the referee to wake up the coaches for they to start selecting next team
    */

    public synchronized void callTrial() {

        while (seat<SimulPar.P){
            try{
                wait();
            }
            catch (InterruptedException exception) {
            }
        }

        callTrial=true;
        ((BenchClientProxy) Thread.currentThread()).setrefereeState(RefereeState.TEAMS_READY);
        repos.setRefereeState(RefereeState.TEAMS_READY);

        notifyAll();
    }




    /**
     * Selects the top 3 strongest contestants from the team of the specified coach.
     * @param coachID the coach ID
     * @return List of selected contestants
     */
    private List<BenchClientProxy> selectContestantsForTrial(int coachID, PriorityQueue<BenchClientProxy> priorityQueue) {
        List<BenchClientProxy> selectedContestants = new ArrayList<>();
        // Assuming the number of contestants to select is predefined
        int numToSelect = 3; // example
        for (int i = 0; i < numToSelect; i++) {
            if (!priorityQueue.isEmpty()) {
                selectedContestants.add(priorityQueue.poll());
            }
        }
        return selectedContestants;
    }
    /**
     * Called by the coach to select the 3 contestants that are going to play
     */
    public synchronized void callContestants() {
        int coachID = ((BenchClientProxy) Thread.currentThread()).getcoachId();
        coach[coachID] = (BenchClientProxy) Thread.currentThread();
        //GenericIO.writelnString("Coach callContestants"+ coachID );
        // Initialize priority queue for selecting strongest contestants
        PriorityQueue<BenchClientProxy> priorityQueue = new PriorityQueue<>((c1, c2) -> Integer.compare(c2.getStrength(), c1.getStrength()));

        // Initialize team members list
        List<BenchClientProxy> teamMembers = new ArrayList<>();

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



        int team_id=-1;

        // Add contestants to the priority queue and team members list
        for (Integer contestantID : teamMemberIDs) {
            BenchClientProxy contestant = getContestantById(contestantID);
            if (contestant != null) {
                if(contestant.getcontestantId()>=0 && contestant.getcontestantId()<5){
                    team_id=1;
                }
                else{
                    team_id=2;
                }
                //GenericIO.writelnString("Contestant Called "+ contestant.getcontestantId());
                priorityQueue.add(contestant);
                teamMembers.add(contestant);
            }
        }

        while (!callTrial) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Properly handle interruption
            }
        }

        // Select strongest contestants for the trial
        List<BenchClientProxy> selectedContestants = selectContestantsForTrial(coachID, priorityQueue);

        if (!noPrints) {
            coach[coachID].setcoachState(coachID, CoachState.ASSEMBLE_TEAM);
            repos.setCoachState(coachID, coach[coachID].getcoachState());
        }

        // Set playing status and adjust strengths
        for (BenchClientProxy contestant : teamMembers) {
            //this.setPlaying(contestant.getcontestantId(), false);
            if (selectedContestants.contains(contestant)) {
                //contestant.setplaying(true);
                this.setPlaying(contestant.getcontestantId(), true); 
                contestants[contestant.getcontestantId()].setplaying(true);
                //GenericIO.writelnString("Contestant is going to play(called) "+ contestant.getcontestantId());

            }
            if (!this.getPlaying(contestant.getcontestantId()) && contestants[contestant.getcontestantId()].getStrength() < 10) {
                contestants[contestant.getcontestantId()].setStrength(contestants[contestant.getcontestantId()].getStrength() + 1);
                repos.setContestantStateAndStrenght(contestant.getcontestantId(), ContestantState.SEAT_AT_THE_BENCH, contestant.getStrength(), team_id);

            }        

        }


        notifyAll(); // Notify waiting threads

        // Check if both coaches have called their contestants
        callContestants++;
        if (callContestants == SimulPar.C) {
            callContestants = 0;
            callTrial = false;
        }
    }



  /**
   * Operation followCoachAdvice
   * It is called by a contestant to follow the coach advice and to stand in position
   * 
   */
  public synchronized void followCoachAdvice() {

    int contestantID=((BenchClientProxy) Thread.currentThread ()).getcontestantId();
    contestants[contestantID]=(BenchClientProxy)Thread.currentThread ();

    int team_id=-1;

    if(contestantID>=0 && contestantID<SimulPar.P_T-1){
        team_id=1;
    }
    else{
        team_id=2;
    }
    seat--;

    contestants[contestantID].setContestantState(ContestantState.STAND_IN_POSITION);
    repos.setContestantStateAndStrenght(contestantID, ContestantState.STAND_IN_POSITION,contestants[contestantID].getStrength(),team_id);

}

    /**
     * Get the number of players that are seated
     * @return seat
     */

    public synchronized int get_nr_seat(){
        return seat;
    }

    /**
     *  Operation SeatDown
     * It is called by a contestant to seat and wait until he is selected to play by the coach.
    */
    public synchronized void seatDown(){

        int contestantID=((BenchClientProxy) Thread.currentThread ()).getcontestantId();
        contestants[contestantID]=(BenchClientProxy)Thread.currentThread ();

        seat++;

        int team_id=-1;

        if(contestantID>=0 && contestantID<SimulPar.P_T-1){
            team_id=1;
        }
        else{
            team_id=2;
        }

        if(this.getPlaying(contestantID)){
            contestants[contestantID].setStrength(contestants[contestantID].getStrength()-1);
            repos.setContestantStateAndStrenght(contestantID, ContestantState.SEAT_AT_THE_BENCH,contestants[contestantID].getStrength(),team_id);

        }
        contestants[contestantID].setContestantState(ContestantState.SEAT_AT_THE_BENCH);





        if(seat==SimulPar.P)notifyAll();

        this.setPlaying(contestantID,false);
        contestants[contestantID].setplaying(false);

        while (contestants[contestantID].getplaying()==false ) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }




    }



/**
 * Operation canEndTheGame
 * Before announcing new game the referee will wait for all players to be seated
 *  @param endOfMatch check if the match was ended
 */
public synchronized void EndGame(boolean endOfMatch) {
    // Espera que todos os concorrentes estejam sentados
    while (seat < SimulPar.P) {
        try {
            wait();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Lida corretamente com a interrupção da thread
        }
    }

    if (endOfMatch) {

        List<BenchClientProxy> team = new ArrayList<>();

        // Obtém os IDs dos membros da equipe de cada treinador
        for (int coachID = 0; coachID < SimulPar.C; coachID++) {
            List<Integer> teamMemberIDs = new ArrayList<>();
            // Divide os IDs dos concorrentes entre as equipes com base nos IDs dos treinadores
            if (coachID == 0) {
                // IDs dos concorrentes da primeira equipe (0 a 4)
                for (int contestantID = 0; contestantID < SimulPar.P / 2; contestantID++) {
                    teamMemberIDs.add(contestantID);
                }
            } else {
                // IDs dos concorrentes da segunda equipe (5 a 9)
                for (int contestantID = SimulPar.P / 2; contestantID < SimulPar.P; contestantID++) {
                    teamMemberIDs.add(contestantID);
                }
            }

            // Adiciona os concorrentes à lista da equipe baseando-se em seus IDs
            for (Integer contestantID : teamMemberIDs) {
                BenchClientProxy contestant = getContestantById(contestantID);
                if (contestant != null) {
                    team.add(contestant);
                }
            }

            for (BenchClientProxy contestant : team) {
                contestant.setplaying(true);
                this.setPlaying(contestant.getcontestantId(), true);
            }
        }

        callTrial = true;
        noPrints = true;
        notifyAll();
    }

    ((BenchClientProxy) Thread.currentThread()).setrefereeState(RefereeState.END_OF_A_GAME);
    repos.setRefereeState(RefereeState.END_OF_A_GAME);
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

    /**
     * operation shutdown
     */
    public synchronized void shutdown() {
        nEntities++;
        if(nEntities==3)
		    ServerBench.waitConnection = false;
		notifyAll(); 
	}




}
