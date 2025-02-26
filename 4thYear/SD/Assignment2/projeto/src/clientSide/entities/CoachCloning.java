package clientSide.entities;

import java.util.List;

public interface CoachCloning {
    

    /**
     * Set the coach identification
     * @param id identification of the coach
     */
    public void setcoachId (int id);

 
    /**
     * Get the coach identification
     * @return coachID coach identification
     */
    public int getcoachId();


    /**
     * Set the coach state
     * @param coachID identification of the coach
     * @param state state of the coach
     */
    public void setcoachState(int coachID,int state);
 
    /**
     * Get the coach state
     * @return coachState state of the caoach
     */
    public int getcoachState();


    /**
     * get the team ID
     * @return teamID identification of the team
     */
    public int getTeamID();

    /**
     * Get the team members list
     * @return teamMembers list with the members of the teams
     */
    public  List<Integer> getTeamMemberIDs();

}
