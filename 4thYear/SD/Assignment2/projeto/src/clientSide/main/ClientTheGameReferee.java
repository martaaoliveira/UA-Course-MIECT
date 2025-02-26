package clientSide.main;

import clientSide.entities.Referee;
import clientSide.entities.RefereeState;
import clientSide.stubs.BenchStub;
import clientSide.stubs.GeneralRepoStub;
import clientSide.stubs.PlaygroundStub;
import clientSide.stubs.RefereeSiteStub;
import genclass.GenericIO;


/**
 * Client side of the Referee 
 *
 * Implementation of a client-server model of type 2 (server replication).
 * Communication is based on a communication channel under the TCP protocol.
 */

public class ClientTheGameReferee {
        
	/**
	 * Main method.
	 *
	 * @param args runtime arguments 
	 *		args[0] - name of the platform where is located the benchServerHostName server 
	 *      args[1] - name of the platform where is located the playgroundServerHostName server 
	 * 		args[2] - name of the platform where is located the refereeSiteServerHostName server 
	 * 
	 *      args[3] - port number for listening to service requests Bench
	 *      args[4] - port number for listening to service requests Playground
	 * 	    args[5] - port number for listening to service requests RefereeSite
	 *      args[6] - name of the platform where is located the general repository server 
	 *      args[7] - port number for listening to service requests GeneralRepo
	 */

     public static void main(String[] args) {

		String benchServerHostName; 
		int benchServerPortNumb = -1; 

		String playgroundServerHostName; 
		int playgroundServerPortNumb = -1; 

		String refereeSiteServerHostName;
		int refereeSiteServerPortNumb = -1; 


		String genReposServerHostName; // name of the platform where is located the general repository server
		int genReposServerPortNumb = -1; // port number for listening to service requests


		BenchStub benchtub; // remote reference to the bench
		PlaygroundStub playgroundStub; // remote reference to the playground
		RefereeSiteStub refereeSiteStub; //remote reference to the referee site
		GeneralRepoStub genReposStub; // remote reference to the general repository

		/* getting problem runtime parameters */

		if (args.length != 8) {
			GenericIO.writelnString("Wrong number of parameters!");
			System.exit(1);
		}
		benchServerHostName = args[0];
		playgroundServerHostName = args[1];
		refereeSiteServerHostName= args[2];

		try {
			benchServerPortNumb = Integer.parseInt(args[3]);
		} catch (NumberFormatException e) {
			GenericIO.writelnString("args[3] is not a number!");
			System.exit(1);
		}
		if ((benchServerPortNumb < 4000) || (benchServerPortNumb >= 65536)) {
			GenericIO.writelnString("args[3] is not a valid port number!");
			System.exit(1);
		}
		try {
			playgroundServerPortNumb = Integer.parseInt(args[4]);
		} catch (NumberFormatException e) {
			GenericIO.writelnString("args[4] is not a number!");
			System.exit(1);

		}
		if ((playgroundServerPortNumb < 4000) || (playgroundServerPortNumb >= 65536)) {
			GenericIO.writelnString("args[4] is not a valid port number!");
			System.exit(1);
		}



		try {
			refereeSiteServerPortNumb = Integer.parseInt(args[5]);
		} catch (NumberFormatException e) {
			GenericIO.writelnString("args[5] is not a number!");
			System.exit(1);

		}
		if ((refereeSiteServerPortNumb < 4000) || (refereeSiteServerPortNumb >= 65536)) {
			GenericIO.writelnString("args[5] is not a valid port number!");
			System.exit(1);
		}


		genReposServerHostName = args[6];
		try {
			genReposServerPortNumb = Integer.parseInt(args[7]);
		} catch (NumberFormatException e) {
			GenericIO.writelnString("args[5] is not a number!");
			System.exit(1);
		}
		if ((genReposServerPortNumb < 4000) || (genReposServerPortNumb >= 65536)) {
			GenericIO.writelnString("args[5] is not a valid port number!");
			System.exit(1);
		}

		/* problem initialization */

		benchtub = new BenchStub(benchServerHostName, benchServerPortNumb);
		playgroundStub = new PlaygroundStub(playgroundServerHostName, playgroundServerPortNumb);
		refereeSiteStub = new RefereeSiteStub(refereeSiteServerHostName, refereeSiteServerPortNumb);
		genReposStub = new GeneralRepoStub(genReposServerHostName, genReposServerPortNumb);

        Referee referee = new Referee("Referee_", RefereeState.START_OF_THE_MATCH,playgroundStub,refereeSiteStub,benchtub);
		referee.addCoachID(0);
        referee.addCoachID(1);
		
		for (int i = 0; i < 10; i++) { 
            referee.addContestantID(i);
        }

        referee.start();

		/* waiting for the end of the simulation */

		GenericIO.writelnString();
			try {
				referee.join();
			} catch (InterruptedException e) {
			}
			GenericIO.writelnString("The referee has terminated ");
		
            GenericIO.writelnString();
			genReposStub.shutdown();
			benchtub.shutdown();
			playgroundStub.shutdown();
            refereeSiteStub.shutdown();
	
}
}
