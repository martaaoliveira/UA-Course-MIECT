package commInfra;

/**
 * Type of the exchanged messages.
 * Implementation of a client-server model of type 2 (server replication).
 * Communication is based on a communication channel under the TCP protocol.
 */
public class MessageType {
    

/**
 * Initialization of the logging file name and the number of iterations (service request).
 */
public static final int SETNFIC = 1;

/**
 * Logging file was initialized (reply).
 */
public static final int NFICDONE = 2;

/**
 * Server shutdown (service request).
 */
public static final int SHUT = 3;

/**
 * Server was shutdown (reply).
 */
public static final int SHUTDONE = 4;

/**
 * Setting acknowledged (reply).
 */

public static final int SACK = 50;



// ********************************************************Messages for trial management in BENCH 


/**
 * Request to call trial by the referee (service request).
 */
public static final int REQCALLTRIAL = 5;

/**
 * Confirmation that the trial has been called (reply).
 */
public static final int CALLTRIALDONE = 6;

/**
 * Request to call contestants by the coach (service request).
 */
public static final int REQCALLCONTESTANTS = 7;

/**
 * Confirmation that contestants have been called (reply).
 */
public static final int CALLCONTESTANTSDONE = 8;

/**
 * Request to follow coach advice by a contestant (service request).
 */
public static final int REQFOLLOWCOACHADVICE = 9;

/**
 * Confirmation that the coach advice has been followed (reply).
 */
public static final int FOLLOWCOACHADVICEDONE = 10;

/**
 * Request by a contestant to seat down (service request).
 */
public static final int REQSEATDOWN = 11;

/**
 * Confirmation that the contestant has seated down (reply).
 */
public static final int SEATDOWNDONE = 12;

/**
 * Request by the referee to end the game (service request).
 */
public static final int REQENDGAME = 13;

/**
 * Confirmation that the game has ended (reply).
 */
public static final int ENDGAMEDONE = 14;


/**
 * end of work-contestant
 */
public static final int ENDOPCONTESTANT =44;
/**
 * end of work-contestant (reply)
 */
public static final int ENDOPDONECONTESTANT = 45;


// **************************************************Messages for trial management in Playground

/**
 * Request to start the trial(service request).
 */
public static final int REQSTARTTRIAL = 14;
/**
 * Confirmation that the trial has started.
 */
public static final int STARTTRIALDONE = 15;
/**
 * Request to get ready for the trial (service request).
 */
public static final int REQGETREADY = 16;
/**
 * Confirmation that the contestant is ready for the trial.
 */
public static final int GETREADYDONE = 16;
/**
 * Request to inform the referee that the team is ready for the trial (service request).
 
 */
public static final int REQINFORMREFEREE = 17;
/**
 * Confirmation that the referee has been informed that the team is ready for the trial.
 */
public static final int INFORMREFEREEDONE = 18;
/**
 * Contestant request to stop playing (service request).
 */
public static final int REQAMDONE = 19;
/**
 * Confirmation that the contestant has stopped playing.
 */
public static final int AMDONEDONE = 20;
/**
 * Request to the referee to assert the trial decision (service request).
 */
public static final int REQASSERTTRIALDECISION = 21;
/**
 * Confirmation that the trial decision has been asserted.
 */
public static final int ASSERTTRIALDECISIONDONE = 22;
/**
 * Request to review the notes (service request).
 */
public static final int REQREVIEWNOTES = 23;
/**
 * Confirmation that the notes have been reviewed.
*/
public static final int REVIEWNOTESDONE = 24;
/**
 * request to see if the game is done
 */
public static final int REQGAMEDONE = 25;
/**
 * confirmation that the game is done
 */
public static final int GAMEDONEDONE = 26;
/**
 * end of work-coach
 
 */
public static final int ENDOPCOACH =46;
/**
 * end of work-coach (reply)
 */
public static final int ENDOPDONECOACH =47;


// **********************************************************Messages for trial management in GeneralRepo 


/**
 * Request to set the number of trials (service request).
 */
public static final int SETNUMBERTRIALS = 27;
/**
 * Request to set the state of the referee and the number of games (service request). 
 */
public static final int SETREFEREESTATEANDNUMBERGAMES = 28;
/**
 * Request to set the state of the referee and the match winner (service request).
 */
public static final int SETREFEREESTATEANDMATCHWINNER = 29;
/**
 * Request to set the outcome details of the trial/match (service request).
 */
public static final int SETOUTCOMEDETAILS = 30;
 /**
  * gets the updated state
  */
 public static final int STATEUPDATED = 31;
/**
 * Request to set the state of the referee (service request).
 */
public static final int SETREFEREESTATE = 32;
/**
 * Request to set the position of the rope (service request).
 */
public static final int SETPOSITIONROPE = 33;
/**
 * Request to set the state of the coach (service request).
 */
public static final int SETCOACHSTATE = 34;
/**
 * Request to set the state of the contestant and the strength of the contestant (service request).
 
 */
public static final int SETCONTESTANTSTATESTRENGTH = 35;

// **************************************************************Messages for trial management in RefereeSite
/**
 * Request to announce a new game (service request).
 */
public static final int REQANNOUNCENEWGAME = 36;
/**
 * Confirmation that a new game has been announced.
 */
public static final int ANNOUNCENEWGAMEDONE = 37;
/**
 * Request to declare the game winner (service request).
 */
public static final int REQDECLAREGAMEWINNER = 38;
/**
 * Confirmation that the game winner has been declared.
 */
public static final int DECLAREGAMEWINNERDONE = 39;
/**
 * Request to declare the match winner (service request).
 */
public static final int REQDECLAREMATCHWINNER= 40;
/**
 * Confirmation that the match winner has been declared.
 */
public static final int DECLAREMATCHWINNERDONE = 41;

/**
 * Confirmation that the match has ended.
 
 */
public static final int ENDOFMATCHDONE = 43; 

/**
 * end of work-referee
 */
public static final int  ENDOPREFEREE = 48;
/**
 * end of work-referee(reply)
 */
public static final int ENDOPDONEREFEREE =49;

 /**
  * Coach requests to see if the match has ended (Service request)
  */
 public static final int REQENDOFMATCHCOACH =51;
 /**
  * confirmation that the match has ended(reply)
  */
 public static final int REQENDOFMATCHREFEREE =52;

 /**
  * Contestant requests to see if the match has ended (Service request)
  */
 public static final int REQENDOFMATCHCONTESTANT =53;
 /**
  * confirmation that the match has ended(reply)
  */
 public static final int ENDOFMATCHCOACHDONE =51;
 /**
  * referee requests to tell that the match is done(service request)
  */
 public static final int ENDOFMATCHREFEREEDONE =52;

 /**
  * confirmation that the match is done(reply)
  */
 public static final int ENDOFMATCHCONTESTANTDONE =53;


}
