package serverSide.objects;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import clientSide.entities.RefereeState;
import genclass.GenericIO;
import interfaces.GeneralRepoInterface;
import interfaces.RefereeSiteInterface;
import interfaces.ReturnBoolean;
import serverSide.main.ServerRefereeSite;
import serverSide.main.SimulPar;

public class RefereeSite implements RefereeSiteInterface{

     /**
     * GeneralRepo instantiation
     */
    private final GeneralRepoInterface repos;
    /**
     * Array of coaches
     */
    private final Thread  [] coach;
    /**
     * Array of contestants
     */
    private final Thread  [] contestants;

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

    private int nEntities = 0;


    /**
     * refereeSite constructor
     * @param repos generalRepoStub instantiation
     */
    public RefereeSite(GeneralRepoInterface repos) {

        game_point=0;
        position_rope=0;
        nr_games=0;
        endOfMatch=false;
        team_winner=0;
        this.teamGames = new HashMap<>();

        this.repos = repos;
        coach = new Thread [SimulPar.C];
        contestants = new Thread [SimulPar.P];
        for (int i = 0; i < SimulPar.C; i++)
            coach[i] = null;

        for (int i = 0; i < SimulPar.P; i++)
        contestants[i] = null;
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
     * Operation server shutdown.
     * @throws RemoteException
     */
    public synchronized void shutdown() throws RemoteException {
        nEntities++;
        if (nEntities == 3)
		    ServerRefereeSite.shutdown(); 
		notifyAll(); 
    }

    /**
     * operation announce new game
     * @return state of the referee
     * @throws RemoteException
     */
    @Override
    public synchronized int announceNewGame() throws RemoteException {
        nr_games++;
        //((RefereeSiteClientProxy ) Thread.currentThread()).setrefereeState(RefereeState.START_OF_A_GAME);
        //repos.setRefereeStateAndNumberGames(RefereeState.START_OF_A_GAME, nr_games);
        try{
            repos.setRefereeStateAndNumberGames(RefereeState.START_OF_A_GAME, nr_games);
        }
        catch (RemoteException e)
		{ 
			GenericIO.writelnString (" Referee remote exception on Call Trial - setRefereeState: " + e.getMessage ());
			System.exit (1);
		}

        return RefereeState.START_OF_A_GAME;
    }

    /**
     * operation declare game winner
     * @param position_rope indicates the position of the rope
     * @return flag that tell if the game is over and the referee state
     * @throws RemoteException
     */
    @Override
    public synchronized ReturnBoolean declareGameWinner(int position_rope) throws RemoteException {

        game_point=0;

        if (position_rope > 0) {
            int teamID = 1;
            team_winner = teamID;
            //GenericIO.writelnString("Game winner"+ team_winner);
            game_point++;
            updateTeamGame(team_winner, game_point);
        } else if (position_rope < 0) {
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

        //((RefereeSiteClientProxy ) Thread.currentThread()).setrefereeState(RefereeState.WAIT_ALL_SAB);
        //return endOfMatch;
    
        return new ReturnBoolean(endOfMatch, RefereeState.WAIT_ALL_SAB);

    }

    /**
     * operation declare match winner
     * @return state of the referee
     * @throws RemoteException
     */
    @Override
    public synchronized int declareMatchWinner() throws RemoteException {

        int teamID1 = 1;
        int teamID2 = 2;

        int ScoreTeamID1=getTeamGame(teamID1);
        int ScoreTeamID2 = getTeamGame(teamID2);
        
        //GenericIO.writelnString("Match winner: score team 1 "+ ScoreTeamID1);
        //GenericIO.writelnString("Match winner: score team 2 "+ ScoreTeamID2);


        int winnerTeam=0;
        game_point=0;

        //((RefereeSiteClientProxy ) Thread.currentThread()).setrefereeState(RefereeState.END_OF_THE_MATCH);

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

        }

        try{
            repos.setRefereeStateAndMatchWinner(RefereeState.END_OF_THE_MATCH, winnerTeam, ScoreTeamID1, ScoreTeamID2);
        }
        catch (RemoteException e)
        { 
            GenericIO.writelnString (" Referee remote exception on DeclareMatchWinner- setRefereeStateAndMatchWinner: " + e.getMessage ());
            System.exit (1);
        }

        return RefereeState.END_OF_THE_MATCH;

    }

    @Override
    public boolean getEndOfMatch() throws RemoteException {
        return this.endOfMatch;
    }
    
}
