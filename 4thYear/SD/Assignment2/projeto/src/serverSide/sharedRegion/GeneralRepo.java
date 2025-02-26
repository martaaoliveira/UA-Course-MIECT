package serverSide.sharedRegion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import clientSide.entities.*;
import serverSide.main.ServerGeneralRepos;
import serverSide.main.SimulPar;
import genclass.TextFile;
import genclass.GenericIO;


/**
 * General Repository.
 * 
 * It is responsible to keep the visible the internal state of the problem and prints in the logging file.
 * It is implemented as an implicit monitor.
 * All public methods are executed in mutual exclusion.
 * There are no internal synchronization points.
 */

public class GeneralRepo {
    /**
     * Name of the log file
     */
    private String logFileName;

    /**
     * Array of the state of the contestants
     */
    private final int [] contestantState;
    /**
     * Array of the state of the coaches
     */
    private final int [] coachState;
    /**
     * State of the referee
    */
    private int refereeState;
    /**
     * Array of the strength of the contestants
     */
    private  final int[] strength;

    /**
     * List of the selected contestants for team 1
     */
    private List<Integer> selectedTeam1IDs = new ArrayList<>();
    /**
     * List of the selected contestants for team 2
     */
    private List<Integer> selectedTeam2IDs = new ArrayList<>();


    /**
     * first header of the log file
     */
    private String header1;
    /**
     * second header of the log file
     */
    private String header2;
    /**
     * third header of the log file
     */
    private String header3;
    /**
     * Outcome details of the game
     */
    private String outcomeDetails = "";
    /**
     * Boolean to keep track if the header of the match result was printed
     */
    private boolean headerResulMatch;
    /**
     * Number of games
     */
    private int number_games;
    /**
     * Number of trials
     */
    private int number_trials;
    /**
     * Position of the rope
     */
    private int position_rope;
    /**
     * Number of contestants in the bench
     */
    private int seat_bench;
    /**
     * Winner of the match
     */
    private int matchWinner;
    /**
     * Score of team 1
     */
    private int ScoreTeamID1;
    /**
     * Score of team 2
     */
    private int ScoreTeamID2;

    /**
     * number of entities
     */
    private int nEntities = 0;
    /**
     * header that shows the result of the game
     */
    private boolean headerResultGame=false;

    /**
     * GeneralRepo constructor
     */
    public GeneralRepo() {

        this.number_games =0;
        this.number_trials=0;
        this.position_rope=0;
        this.seat_bench=0;
        this.matchWinner=0;
        this.ScoreTeamID1=0;
        this.ScoreTeamID2=0;

        headerResulMatch=false;
        headerResultGame=false;
        strength = new int[SimulPar.P];
        this.logFileName="logger";

        coachState = new int[SimulPar.C];
        contestantState = new int[SimulPar.P];
        refereeState = RefereeState.START_OF_THE_MATCH;

        for (int i = 0; i <SimulPar.P;i++){//1..10
            contestantState[i]= ContestantState.SEAT_AT_THE_BENCH;
        }
        for (int i = 0; i <SimulPar.C;i++){
            coachState[i]= CoachState.WAIT_FOR_REFEREE_COMMAND;
        }

        this.header1 = "Ref Coa 1 Cont 1 Cont 2 Cont 3 Cont 4 Cont 5 Coa 2 Cont 1 Cont 2 Cont 3 Cont 4 Cont 5       Trial        ";
        this.header2 = "Sta  Stat Sta SG Sta SG Sta SG Sta SG Sta SG  Stat Sta SG Sta SG Sta SG Sta SG Sta SG 3 2 1 . 1 2 3    NB PS";
        header3="Game";

        reportInitialStatus();

    }

    /**
     * initiates the simulation
     * @param logFileName name of the log file
     */
    public synchronized void initSimul(String logFileName) {
		if (!Objects.equals(logFileName, ""))
			this.logFileName = logFileName;
		reportInitialStatus();
	}

    /**
     * Report the initial status of the game
     */
    private void reportInitialStatus() {
		TextFile log = new TextFile(); // instantiation of a text file handler
		if (!log.openForWriting(".", logFileName)) {
			GenericIO.writelnString("The operation of creating the file " + logFileName + " failed!");
			System.exit(1);
		}
		log.writelnString(" \t\t\t\t\t\t Game of the Rope - Description of the internal state\n");
        log.writelnString(header1);
        log.writelnString(header2);

		if (!log.close()) {
			GenericIO.writelnString("The operation of closing the file " + logFileName + " failed!");
			System.exit(1);
		}

        reportStatus();

	}
    /**
     * Report the status of the game
     */
    private void reportStatus() {
		TextFile log = new TextFile();
		String lineStatus = "";
		seat_bench=0;
        int WFRC=0;


        if (!log.openForAppending(".", logFileName)) {
			GenericIO.writelnString("The operation of opening for appending the file " + logFileName + " failed!");
			System.exit(1);
		}


        switch(refereeState) {
            case RefereeState.END_OF_A_GAME:
            lineStatus += "005";
            break;

            case RefereeState.END_OF_THE_MATCH:
            lineStatus += "006";
            break;

            case RefereeState.START_OF_A_GAME:
            lineStatus += "002";
            break;

            case RefereeState.START_OF_THE_MATCH:
            lineStatus += "001";
            break;

            case RefereeState.TEAMS_READY:
            lineStatus += "003";
            break;

            case RefereeState.WAIT_FOR_TRIAL_CONCLUSION:
            lineStatus += "004";
            break;

        }


        switch (coachState[0]) {

            case CoachState.ASSEMBLE_TEAM:
            lineStatus += "   002";
            break;

            case CoachState.WAIT_FOR_REFEREE_COMMAND:
            lineStatus += "   001";
            WFRC++;
            break;

            case CoachState.WATCH_TRIAL:
            lineStatus += "   003";
            break;
        }

        for (int i = 0; i < 5; i++) {
            switch (contestantState[i]) {
                case ContestantState.DO_YOUR_BEST:
                lineStatus += " 003";
                break;

                case ContestantState.SEAT_AT_THE_BENCH:
                lineStatus += " 001";
                seat_bench++;
                break;

                case ContestantState.STAND_IN_POSITION:
                lineStatus += " 002";
                break;
                }
                lineStatus += " " + String.format("%2d", strength[i]);
        }

        switch (coachState[1]) {
            case CoachState.ASSEMBLE_TEAM:
            lineStatus += "   002";
            break;
            case CoachState.WAIT_FOR_REFEREE_COMMAND:
            lineStatus += "   001";
            WFRC++;
            break;

            case CoachState.WATCH_TRIAL:
            lineStatus += "   003";
            break;
        }

        for (int i = 5; i < 10; i++) {
                
            switch (contestantState[i]) {
                case ContestantState.DO_YOUR_BEST:
                lineStatus += " 003";
                break;
                case ContestantState.SEAT_AT_THE_BENCH:
                lineStatus += " 001";
                seat_bench++;
                break;

                case ContestantState.STAND_IN_POSITION:
                lineStatus += " 002";
                break;
            }

            lineStatus += " " +String.format("%2d", strength[i]);
                
        }

        for (int i = 0; i < 3; i++) {
            lineStatus += selectedTeam1IDs.size() > i ? " " + selectedTeam1IDs.get(i) : " -";
        }
        lineStatus += " .";
        for (int i = 0; i < 3; i++) {
            lineStatus += selectedTeam2IDs.size() > i ? " " + selectedTeam2IDs.get(i) : " -";
        }


        lineStatus += "     " + number_trials;
        lineStatus += "  " + position_rope;


        if (RefereeState.START_OF_A_GAME == refereeState && WFRC==2 && seat_bench==10 ) {
            log.writelnString(header3 + " " + number_games);
            log.writelnString(header1);
            log.writelnString(header2);
        }

        log.writelnString(lineStatus);


        if (refereeState == RefereeState.END_OF_A_GAME ) {

            if(headerResultGame==false){
                String conclusionMessage = "Game " + number_games;
                // Including knockout or points detail if available
                conclusionMessage += " "+outcomeDetails;
                log.writelnString(conclusionMessage);
            }

            headerResultGame=true;

        }

        if (RefereeState.END_OF_THE_MATCH == refereeState) {

            if(headerResulMatch==false){
                if(matchWinner==0){
                    log.writelnString("Match was a draw");
                }
                else{
                    log.writelnString("Match was won by team " + matchWinner + " with a score of " + ScoreTeamID1 + " - " + ScoreTeamID2);
    
                }
            }

            headerResulMatch=true;
        
        }



        if (seat_bench == 10) {
            //Clean list of selected teams to reset before next trial
            selectedTeam1IDs.clear();
            selectedTeam2IDs.clear();
        }


        if (!log.close()) {
            GenericIO.writelnString("The operation of closing the file " + logFileName + " failed!");
            System.exit(1);
        }

    }


    /**
     * Set the state of the referee
     * @param state state of the referee
     */
    public synchronized void setRefereeState(int state) {
        this.refereeState = state;
        reportStatus();
    }


    /**
     * Set the outcome details of the game
     * @param outcomeDetails outcome details of the game
     */
    public synchronized void setOutcomeDetails(String outcomeDetails) {
        this.outcomeDetails = outcomeDetails;
    }



    /**
     * Set the referee state and the number of games
     * @param state referee state
     * @param number_games number of games played
     */
    public synchronized void setRefereeStateAndNumberGames(int state, int number_games) {
        this.refereeState = state;
        this.number_games=number_games;
        selectedTeam1IDs.clear();
        selectedTeam2IDs.clear();
        headerResultGame=false;
        number_trials=1;
        position_rope=0;
        reportStatus();
    }


    /**
     * Set the referee state and the match winner
     * @param state referee state
     * @param matchWinner match winner
     * @param ScoreTeamID1 score of Team 1
     * @param ScoreTeamID2 score of Team 2
     */
    public synchronized void setRefereeStateAndMatchWinner(int state, int matchWinner, int ScoreTeamID1, int ScoreTeamID2) {
        refereeState = state;
        this.matchWinner = matchWinner;
        this.ScoreTeamID1 = ScoreTeamID1;
        this.ScoreTeamID2 = ScoreTeamID2;
        //position_rope=0;
        //number_trials=0;
        reportStatus();
    }


    /**
     * Set the position of the rope
     * @param position_rope defines the position of the rope
     */
    public synchronized void setPositionRope(int position_rope) {
        this.position_rope = position_rope;
        reportStatus();
    }

    /**
     * Set the number of trials
     * @param nr_trials define the number of trials
     */
    public synchronized void set_nr_trials(int nr_trials) {
        this.number_trials = nr_trials;
        reportStatus();
    }

    /**
     * Set coach state
     * @param coachID coach identification
     * @param state state of the coach
     */
    public synchronized void setCoachState(int coachID, int state) {
        this.coachState[coachID] = state;
        reportStatus();

    }


    /**
     * Set contestant state and strength for each team
     * @param id identification of the contestant
     * @param state state of the contestant
     * @param strength strength of the contestant
     * @param team team that the contestant belongs to
     */
    public synchronized void setContestantStateAndStrenght(int id, int state, int strength,int team) {
        this.contestantState[id]=state;
        this.strength[id] = strength;
        if (state == ContestantState.STAND_IN_POSITION){
            if (team == 1) {
                selectedTeam1IDs.add(id);
            }

            else if(team==2){
                selectedTeam2IDs.add(id);
            }
        }

        reportStatus();
    }

    /**
     * generalRepo shutdown
     */
    public synchronized void shutdown() {
        nEntities++;
        if(nEntities==3)
		    ServerGeneralRepos.waitConnection = false;
        notifyAll(); 

	}

}




    
