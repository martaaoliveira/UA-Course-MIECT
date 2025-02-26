package interfaces;
import java.rmi.*;



/**
 *   Operational interface of a remote object of type GeneralRepos.
 *
 *     It provides the functionality to access the General Repository of Information.
*/


public interface GeneralRepoInterface extends Remote
{

    /**
     *   Operation initialization of simulation.
     *
     *   New operation.
     *
     *     @param logFileName name of the logging file
     *     @throws RemoteException if either the invocation of the remote method, or the communication with the registry
     *                             service fails
     */

    public void initSimul (String logFileName) throws RemoteException;

    /**
     *   Operation server shutdown.
     *
     *   New operation.
     *
     *     @throws RemoteException if either the invocation of the remote method, or the communication with the registry
     *                             service fails
    */


     public void shutdown () throws RemoteException;

        /**
     * Set the state of the referee
     * @param state state of the referee
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */

     public void setRefereeState(int state) throws RemoteException;

    /**
     * Set the outcome details of the game
     * @param outcomeDetails outcome details of the game
     */


     public void setOutcomeDetails(String outcomeDetails) throws RemoteException;

        /**
     * Set the referee state and the number of games
     * @param state referee state
     * @param number_games number of games played
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */

     public void setRefereeStateAndNumberGames(int state, int number_games)throws RemoteException;

    /**
     * Set the referee state and the match winner
     * @param state referee state
     * @param matchWinner match winner
     * @param ScoreTeamID1 score of Team 1
     * @param ScoreTeamID2 score of Team 2
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */

     public void setRefereeStateAndMatchWinner(int state, int matchWinner, int ScoreTeamID1, int ScoreTeamID2)throws RemoteException;

     /**
     * Set the position of the rope
     * @param position_rope defines the position of the rope
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */

     public void setPositionRope(int position_rope)throws RemoteException;


    /**
     * Set the number of trials
     * @param nr_trials define the number of trials
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */

     public void set_nr_trials(int nr_trials)throws RemoteException;


        /**
     * Set coach state
     * @param coachID coach identification
     * @param state state of the coach
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */


     public void setCoachState(int coachID, int state)throws RemoteException;

    /**
     * Set contestant state and strength for each team
     * @param id identification of the contestant
     * @param state state of the contestant
     * @param strength strength of the contestant
     * @param team team that the contestant belongs to
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */

     public void setContestantStateAndStrenght(int id, int state, int strength,int team)throws RemoteException;



}
