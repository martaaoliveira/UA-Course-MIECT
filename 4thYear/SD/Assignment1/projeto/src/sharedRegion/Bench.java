package sharedRegion;

import entities.*;
import main.SimulPar;

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
    private final Coach [] coach;


    /**
     * List of team members per coach
     */
    private ArrayList<Contestant>[]teamMembers;

    
    /**
     * Priority queues per coach to manage contestant strengths
     */
    private PriorityQueue<Contestant>[] priorityQueues;


    private boolean noPrints;
    /**
     * Array of contestants
    */

    private final Contestant [] contestants;
    /**
     * GeneralRepo instantiation
     */
    private final GeneralRepo repos;

    


    /**
     * Bench constructor
     * @param repos
     */
    // Constructor
    public Bench(GeneralRepo repos) {
        this.repos = repos;
        this.callTrial=false;
        this.callContestants=0;
        this.seat=0;
        noPrints=false;

        coach = new Coach[SimulPar.C];
        contestants = new Contestant[SimulPar.P];
        teamMembers = new ArrayList[SimulPar.C];
        priorityQueues = new PriorityQueue[SimulPar.C];

        for (int i = 0; i < SimulPar.C; i++){
            coach[i] = null;
            teamMembers[i]= new ArrayList<>();
            priorityQueues[i] = new PriorityQueue<>();
        }

        for (int i = 0; i < SimulPar.P; i++)
            contestants[i] = null;

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
        ((Referee) Thread.currentThread()).setRefereeState(RefereeState.TEAMS_READY);
        repos.setRefereeState(RefereeState.TEAMS_READY);    
        
        notifyAll();
    }



    /**
     * Rebuilds the priority queue for the specified coach based on current strength of team members.
     * @param coachID the coach ID
     */
    private synchronized void rebuildPriorityQueue(int coachID) {
        this.priorityQueues[coachID] = new PriorityQueue<>((c1, c2) -> Integer.compare(c2.getStrength(), c1.getStrength()));
        priorityQueues[coachID].addAll(teamMembers[coachID]);
    }

    /**
     * Selects the top 3 strongest contestants from the team of the specified coach.
     * @param coachID the coach ID
     * @return List of selected contestants
     */
    private List<Contestant> selectContestantsForTrial(int coachID, PriorityQueue<Contestant> priorityQueue) {
        List<Contestant> selectedContestants = new ArrayList<>();
        // Assuming the number of contestants to select is predefined
        int numToSelect = 3; // example
        for (int i = 0; i < numToSelect; i++) {
            if (!priorityQueue.isEmpty()) {
                selectedContestants.add(priorityQueue.poll());
            }
        }
        return selectedContestants;
    }
    

    public synchronized void callContestants() {
        int coachID = ((Coach) Thread.currentThread()).getcoachId();
        coach[coachID] = (Coach) Thread.currentThread();
    
        // Initialize priority queue for selecting strongest contestants
        PriorityQueue<Contestant> priorityQueue = new PriorityQueue<>((c1, c2) -> Integer.compare(c2.getStrength(), c1.getStrength()));
    
        // Initialize team members list
        List<Contestant> teamMembers = new ArrayList<>();
    
        // Get team member IDs from the coach
        List<Integer> teamMemberIDs = coach[coachID].getTeamMemberIDs();
    
        // Add contestants to the priority queue and team members list
        for (Integer contestantID : teamMemberIDs) {
            Contestant contestant = getContestantById(contestantID);
            if (contestant != null) {
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
        List<Contestant> selectedContestants = selectContestantsForTrial(coachID, priorityQueue);
    
        if (!noPrints) {
            coach[coachID].setcoachState(coachID, CoachState.ASSEMBLE_TEAM);
            repos.setCoachState(coachID, coach[coachID].getcoachState());
        }
    
        // Set playing status and adjust strengths
        for (Contestant contestant : teamMembers) {
            if (selectedContestants.contains(contestant)) {
                contestant.setplaying(true);
            } else {
                if (!contestant.getplaying() && contestant.getStrength() < 10) {
                    contestant.setStrength(contestant.getStrength() + 1);
                }
                repos.setContestantStateAndStrenght(contestant.getcontestantId(), contestant.getContestantState(), contestant.getStrength(), contestant.getTeamID());
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
   */
  public synchronized void followCoachAdvice() {
    
    int contestantID=((Contestant) Thread.currentThread ()).getcontestantId();
    contestants[contestantID]=(Contestant)Thread.currentThread ();

    seat--;

    contestants[contestantID].setContestantState(ContestantState.STAND_IN_POSITION);
    repos.setContestantStateAndStrenght(contestantID, ContestantState.STAND_IN_POSITION,contestants[contestantID].getStrength(),contestants[contestantID].getTeamID());
        
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

        int contestantID=((Contestant) Thread.currentThread ()).getcontestantId();
        contestants[contestantID]=(Contestant)Thread.currentThread ();

        seat++;

        if(contestants[contestantID].getplaying()==true){
            if(contestants[contestantID].getStrength()>1){ 
                contestants[contestantID].setStrength(contestants[contestantID].getStrength()-1);
            }
            contestants[contestantID].setContestantState(ContestantState.SEAT_AT_THE_BENCH);
            repos.setContestantStateAndStrenght(contestantID, ContestantState.SEAT_AT_THE_BENCH, contestants[contestantID].getStrength(),contestants[contestantID].getTeamID());
    
        }

        if(seat==SimulPar.P)notifyAll();


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
        List<Contestant> team = new ArrayList<>();

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

            // Adiciona os concorrentes à lista da equipe
            for (Integer contestantID : teamMemberIDs) {
                Contestant contestant = getContestantById(contestantID);
                if (contestant != null) {
                    team.add(contestant);
                }
            }
        }

        // Define todos os concorrentes da equipe como " a jogar " para os acordar
        for (Contestant contestant : team) {
            contestant.setplaying(true);
        }

        callTrial = true;
        noPrints = true;
        notifyAll();
    }

    ((Referee) Thread.currentThread()).setRefereeState(RefereeState.END_OF_A_GAME);
    repos.setRefereeState(RefereeState.END_OF_A_GAME);
}
    
    /**
     * Set the inPosition variable to each contestant
     * @param inPosition
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