package clientSide.entities;


/**
 *    Definition of the internal states of the customer during his life cycle.
 */

public final class ContestantState
{

   /**
    *  The player is seating at the bench waiting to be called
    */
   public static final int SEAT_AT_THE_BENCH = 0;

 
   /**
    * The player is standing in position waiting for the trial to begin
    */
   public static final int STAND_IN_POSITION = 1;

  
   /**
    * The player is doing his best during trial
    */
   public static final int DO_YOUR_BEST = 2;


   /**
    * It can not be instantiated.
    */
   private ContestantState ()
   { }
}
