package interfaces;
import java.rmi.*;


/**
 *   Operational interface of a remote object of type RefereeSite.
 *
 *     It provides the functionality to access the RefereeSite.
*/



public interface RefereeSiteInterface extends Remote {
    /**
     *   Operation server shutdown.
     *
     *   New operation.
     *
     *     @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
    */

    public void shutdown () throws RemoteException;


        /**
     * Operation announceNewGame
     * Method called by the referee announce a new game
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */
    public int announceNewGame() throws RemoteException;

    /**
     * Operation declareGameWinner
     * It is called by the referee to Declare the game winner based on the position of the rope
     * @param position_rope indicates the position of the rope
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */
    public ReturnBoolean declareGameWinner(int position_rope) throws RemoteException;

    /**
     * Operation declareMatchWinner
     * It is called by the referee to declare the match winner based on the number of games won
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */
    public int declareMatchWinner() throws RemoteException;

    /**
     * Method to check if the match has ended
     * @return flag that tells if the game has ended
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */


    public boolean getEndOfMatch() throws RemoteException;
}
