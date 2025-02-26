package interfaces;
import java.rmi.*;


/**
 *   Operational interface of a remote object of type Bench.
 *
 *     It provides the functionality to access the Bench.
 */

public interface BenchInterface extends Remote{
    

     /**
     *   Operation server shutdown.
     *
     *   New operation.
     *
     *     @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
    */


    public void shutdown () throws RemoteException;

    /**
     * Operation CallTrial
     * It is called by the referee to wake up the coaches for they to start selecting next team
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
    */


    public int callTrial() throws RemoteException;

    /**
     * Called by the coach to select the 3 contestants that are going to play
     * @param coachID coach identification
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
     */


    public int callContestants(int coachID) throws RemoteException;

    /**
   * Operation followCoachAdvice
   * It is called by a contestant to follow the coach advice and to stand in position
     * @param contestantID contestant identification
     * @param strenght  contestant strength
   * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
   * 
   */

    public int followCoachAdvice(int contestantID, int strenght) throws RemoteException;

    
    /**
     *  Operation SeatDown
     * It is called by a contestant to seat and wait until he is selected to play by the coach.
     * @param contestantID contestant identification
     * @param strenght contestant strength
     * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
    */

    public ReturnInt seatDown(int contestantID, int strenght) throws RemoteException;

    /**
    * Operation canEndTheGame
    * Before announcing new game the referee will wait for all players to be seated
    *  @param EndofMatch check if the match was ended
    * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
    */

    public int EndGame(boolean EndofMatch) throws RemoteException;

}
