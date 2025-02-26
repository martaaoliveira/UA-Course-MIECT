package clientSide.entities;

public interface ContestantCloning {

    /**
     * Set contestant identification
     * @param id
     */
    public void setcontestantId (int id);


    /**
     * Get contestant ID
     * @return contestantId identification of the contestant
     */
    public int getcontestantId ();
    /**
     * Get team ID
     * @return teamID identification of the team
     */
    public int getTeamID ();

    /**
     * Set if that player is going to play
     * @param playing
     */
    public void setplaying(boolean playing);
    /**
     * Get if the player is going to play
     * @return boolean flag that indicates if the player is going to playing
     */
    public boolean getplaying();


    /**
     * Set contestant state
     * @param state state of the contestant
     */
    public void setContestantState (int state);
 
    /**
     * Get contestant state
     * @return contestantState returns state of the contestant
     */
    public int getContestantState ();

    /**
     * Get the strength of the contestant
     * @return strength of the contestant
     */
    public int getStrength();

    /**
     * Set the strength of the contestant
     * @param strength strength of the contestant
     */
    public void setStrength(int strength);


}
