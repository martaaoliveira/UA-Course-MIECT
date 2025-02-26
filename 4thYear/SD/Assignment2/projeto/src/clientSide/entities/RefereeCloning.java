package clientSide.entities;

import java.util.List;

public interface RefereeCloning {
   
    /**
     * Adds a coach ID to the list of managed coaches.
     * @param coachID The coach's ID to add.
     */
    public void addCoachID(int coachID) ;
    /**
     * Adds a contestant ID to the list.
     * @param contestantID The contestant's ID to add.
     */
    public void addContestantID(int contestantID);

        /**
     * Adds a coach ID to the list of managed coaches.
     * @param index The coach's ID to add.
     */
    public int getCoachID(int index);
    

    /**
     * returns the contestant ID of the game 
     * @return List contestantID 
     */
    public List<Integer> getContestantID();

    /**
     * Set the state of the referee
     * @param state
     */
    public void setrefereeState (int state);
    
    
    /**
     * Get the referee state
     * @return refereetState returns state of the referee
     */
    public int getrefereeState ();


    
}
