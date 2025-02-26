package serverSide.sharedRegion;

import java.util.HashMap;
import java.util.Map;

import clientSide.entities.*;
import clientSide.stubs.GeneralRepoStub;
import serverSide.entities.RefereeSiteClientProxy;
import serverSide.main.*;

/**
 * The refereeSite class encapsulates the shared region for referee actions and referee state management.
 * <p>
 * Implemented as an implicit monitor, it manages the referee's interactions within the game.
 * <p>
 * This class does not include internal synchronization points, focusing exclusively on referee operations.
 */
public class refereeSite {
    /**
     * GeneralRepo instantiation
     */
    private final GeneralRepoStub repos;
    /**
     * Array of coaches
     */
    private final RefereeSiteClientProxy  [] coach;
    /**
     * Array of contestants
     */
    private final RefereeSiteClientProxy  [] contestants;

    /**
     * variable to keep track of the number of games that have been played
     */
    private int nr_games;
    /**
     * variable to keep track of the position of the rope
     */
    private int position_rope;

    /**
     * variable to keep track of the trials of the game
     */
    private int game_point;

    /**
     * boolean to keep track if the match has ended
     */
    private boolean endOfMatch;

    /**
     * boolean to keep track of the winner team
    */
    private int team_winner;
    /**
     * track games of each team
     */
    private final Map<Integer, Integer> teamGames; //track Games

    /**
     * refereeSite constructor
     * @param repos generalRepoStub instantiation
     */
    public refereeSite(GeneralRepoStub repos) {

        game_point=0;
        position_rope=0;
        nr_games=0;
        endOfMatch=false;
        team_winner=0;
        this.teamGames = new HashMap<>();

        this.repos = repos;
        coach = new RefereeSiteClientProxy [SimulPar.C];
        contestants = new RefereeSiteClientProxy [SimulPar.P];
        for (int i = 0; i < SimulPar.C; i++)
            coach[i] = null;

        for (int i = 0; i < SimulPar.P; i++)
        contestants[i] = null;
    }


    /**
     * Operation announceNewGame
     * Method called by the referee announce a new game
     */
    public synchronized void announceNewGame() {
        try
        {
        Thread.sleep ((long) (1 + 100 * Math.random ()));
        }
        catch (InterruptedException e) {
        }
        nr_games++;
        ((RefereeSiteClientProxy ) Thread.currentThread()).setrefereeState(RefereeState.START_OF_A_GAME);
        repos.setRefereeStateAndNumberGames(RefereeState.START_OF_A_GAME, nr_games);
    }


        /**
     * update number of games won by each team
     * @param teamID identification od the team
     * @param scoreToAdd score that should be added to the winning team
     */
    public synchronized void updateTeamGame(int teamID, int scoreToAdd) {
        teamGames.merge(teamID, scoreToAdd, Integer::sum);
    }
    /**
     * Get the games score of a specific team
     * @param teamID team identification
     * @return number of games won by the desired team
     */
    public synchronized int getTeamGame(int teamID) {
        return teamGames.getOrDefault(teamID, 0);
    }

    /**
     * Operation declareGameWinner
     * It is called by the referee to Declare the game winner based on the position of the rope
     * @param position indicates the position of the rope
     */
    public synchronized boolean declareGameWinner(int position) {

        try
        {
        Thread.sleep ((long) (1 + 100 * Math.random ()));
        }
        catch (InterruptedException e) {
        }
        game_point=0;

        if (position > 0) {
            int teamID = 1;
            team_winner = teamID;
            //GenericIO.writelnString("Game winner"+ team_winner);
            game_point++;
            updateTeamGame(team_winner, game_point);
        } else if (position < 0) {
            int teamID = 2;
            team_winner = teamID;
            //GenericIO.writelnString("Game winner"+ team_winner);
            game_point++;
            updateTeamGame(team_winner, game_point);
        }

        if(nr_games<SimulPar.G){
            endOfMatch=false;
        }
        else{
            endOfMatch=true;
        }

        ((RefereeSiteClientProxy ) Thread.currentThread()).setrefereeState(RefereeState.WAIT_ALL_SAB);
        return endOfMatch;
    }

    /**
     * Operation declareMatchWinner
     * It is called by the referee to declare the match winner based on the number of games won
     */
    public synchronized void declareMatchWinner() {

        try
        {
        Thread.sleep ((long) (1 + 100 * Math.random ()));
        }
        catch (InterruptedException e) {
        }

        int teamID1 = 1;
        int teamID2 = 2;

        int ScoreTeamID1=getTeamGame(teamID1);
        int ScoreTeamID2 = getTeamGame(teamID2);
        
        //GenericIO.writelnString("Match winner: score team 1 "+ ScoreTeamID1);
        //GenericIO.writelnString("Match winner: score team 2 "+ ScoreTeamID2);


        int winnerTeam=0;
        game_point=0;

        ((RefereeSiteClientProxy ) Thread.currentThread()).setrefereeState(RefereeState.END_OF_THE_MATCH);

        if(nr_games==SimulPar.G){

            if (ScoreTeamID1>ScoreTeamID2){
                winnerTeam=1;
            }
            else if(ScoreTeamID2>ScoreTeamID1){
                winnerTeam=2;
            }
            else{
                winnerTeam=0;
            }

            repos.setRefereeStateAndMatchWinner(RefereeState.END_OF_THE_MATCH, winnerTeam, ScoreTeamID1, ScoreTeamID2);
        }

    }



    /**
     * Method to get the number of games
     * @return number of games
     */
    public synchronized int get_nr_games(){
        return this.nr_games;
    }
    /**
     * Method to get the position of the rope in the game
     * @return
     */
    public synchronized int get_position_rope(){
        return this.position_rope;
    }

    /**
     * Method to set the position of the rope in the game
     * @param position_rope position of the rope
     */
    public synchronized void set_position_rope(int position_rope){
        this.position_rope=position_rope;;
    }

    /**
     * Method to check if the match has ended
     * @return flag that tells if the game has ended
     */
    public synchronized boolean getEndOfMatch() {
        return this.endOfMatch;
    }

    /**
     * refereeSite shutdown
     */
    public synchronized void shutdown() {
			ServerRefereeSite.waitConnection = false;
		notifyAll();
	}

}
