package interfaces;
import java.rmi.*;


/**
 *   Operational interface of a remote object of type PlaygroundInterface.
 *
 *     It provides the functionality to access the Playground.
*/


public interface PlaygroundInterface extends Remote {
    /**
     *   Operation server shutdown.
     *
     *   New operation.
     *
     *     @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
    */

    public void shutdown () throws RemoteException;

    /**
     * Operation startTrial
     * It is called by the referee to Start the trial when the coach informs that the teams are ready
     *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */

    public int startTrial() throws RemoteException;

    /**
    * Operation GetReady
    *It is called by Players to get ready if they are selected to play the trial. They wait until Trial is started. The players notify the coaches that they are ready.
     * @param  contestantID identification of the contestant
     * @param strenght strength of the contestants
    * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
    */


    public int getReady(int contestantID, int strenght) throws RemoteException;

    /**
   * Operation informReferee
   * It is called by coaches to inform the referee that the teams are ready to proceed
   * the trial only starts when the last coach informs the referee
     * @param coachID identification of the coach
   *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
   */

    public int informReferee(int coachID) throws RemoteException;
    
    /**
   * Operation amDone
   * It is called by the players to signal that they have finished playing/pulling the rope
   *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
   */
    public void amDone() throws RemoteException;

    /**
     * referee makes a decision based on the game
     * @return rope position
     *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */

    public int assertTrialDecision() throws RemoteException;

    /**
    * It is called by the coaches to review the notes after the trial decision has been made by the referee
     * @param coachID identification of the coach
    * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
    */

    public int reviewNotes(int coachID) throws RemoteException;

    /**
   * Game is done
   * @return flag that tells if the game is done or not
   *  @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
   */

    public boolean game_done() throws RemoteException;
}
