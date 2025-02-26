package clientSide.main;

import clientSide.entities.Referee;
import clientSide.entities.RefereeState;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.*;
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
	 * 			  args[0] - name of the platform where is located the RMI registering service
     *        	  args[1] - port number where the registering service is listening to service requests
     *        	  args[2] - name of the logging file
     */


	public static void main(String[] args) {
		String rmiRegHostName;                                         // name of the platform where is located the RMI registering service
		int rmiRegPortNumb = -1;                                       // port number where the registering service is listening to service requests
		String fileName;                                               // name of the logging file
		  
	    
		/* getting problem runtime parameters */

	      if (args.length != 3)
	         { GenericIO.writelnString ("Wrong number of parameters!");
	           System.exit (1);
	         }
	      rmiRegHostName = args[0];
	      try
	      { rmiRegPortNumb = Integer.parseInt (args[1]);
	      }
	      catch (NumberFormatException e)
	      { GenericIO.writelnString ("args[1] is not a number!");
	        System.exit (1);
	      }
	      if ((rmiRegPortNumb < 4000) || (rmiRegPortNumb >= 65536))
	         { GenericIO.writelnString ("args[1] is not a valid port number!");
	           System.exit (1);
	         }
	      fileName = args[2];
		
	      /* problem initialization */  
	      String nameEntryGeneralRepos = "GeneralRepository";            // public name of the general repository object
	      
	      String nameEntryBench = "Bench";                    				 // public name of the bar object
	      String nameEntryRefereeSite = "RefereeSite";                    		 // public name of the kitchen object
		  String nameEntryPlayground = "Playground";                    		 // public name of the kitchen object
		  
		  GeneralRepoInterface genReposStub = null;                     // remote reference to the general repository object
		  BenchInterface benchStub = null; 							 // remote reference to the bar
	      PlaygroundInterface playgroundStub = null;                          		 // remote reference to the bar object
		  RefereeSiteInterface refereeSiteStub = null;
	      
		  Registry registry = null;                                      // remote reference for registration in the RMI registry service
	      
		  Referee referee = null;							 // array of chef threads
			
		
		/* problem initialization */
	      try
	      { registry = LocateRegistry.getRegistry (rmiRegHostName, rmiRegPortNumb);
	      }
	      catch (RemoteException e)
	      { GenericIO.writelnString ("RMI registry creation exception: " + e.getMessage ());
	        e.printStackTrace ();
	        System.exit (1);
	      }

		 
		 try
	      { genReposStub = (GeneralRepoInterface) registry.lookup (nameEntryGeneralRepos);
	      }
	      catch (RemoteException e)
	      { GenericIO.writelnString ("GeneralRepos lookup exception: " + e.getMessage ());
	        e.printStackTrace ();
	        System.exit (1);
	      }
	      catch (NotBoundException e)
	      { GenericIO.writelnString ("GeneralRepos not bound exception: " + e.getMessage ());
	        e.printStackTrace ();
	        System.exit (1);
	      }
		
		 try
	      { benchStub = (BenchInterface) registry.lookup (nameEntryBench);
	      }
	      catch (RemoteException e)
	      { GenericIO.writelnString ("Bench lookup exception: " + e.getMessage ());
	        e.printStackTrace ();
	        System.exit (1);
	      }
	      catch (NotBoundException e)
	      { GenericIO.writelnString ("Bench not bound exception: " + e.getMessage ());
	        e.printStackTrace ();
	        System.exit (1);
	      }

		 try
	      { refereeSiteStub = (RefereeSiteInterface) registry.lookup (nameEntryRefereeSite);
	      }
	      catch (RemoteException e)
	      { GenericIO.writelnString ("RefereeSite lookup exception: " + e.getMessage ());
	        e.printStackTrace ();
	        System.exit (1);
	      }
	      catch (NotBoundException e)
	      { GenericIO.writelnString ("RefereeSite not bound exception: " + e.getMessage ());
	        e.printStackTrace ();
	        System.exit (1);
	      }
		 
		  try
	      { playgroundStub = (PlaygroundInterface) registry.lookup (nameEntryPlayground);
	      }
	      catch (RemoteException e)
	      { GenericIO.writelnString ("Playground lookup exception: " + e.getMessage ());
	        e.printStackTrace ();
	        System.exit (1);
	      }
	      catch (NotBoundException e)
	      { GenericIO.writelnString ("Playground not bound exception: " + e.getMessage ());
	        e.printStackTrace ();
	        System.exit (1);
	      }


		 try
	     { genReposStub.initSimul (fileName);
	     }
	     catch (RemoteException e)
	     { GenericIO.writelnString ("GeneralRepos generator remote exception on initSimul: " + e.getMessage ());
	       System.exit (1);
	     }
		
		
		
		referee = new Referee("Referee_", RefereeState.START_OF_THE_MATCH,playgroundStub,refereeSiteStub,benchStub);

		/* start of the simulation */
		referee.start();

		/* waiting for the end of the simulation */
		GenericIO.writelnString();
		
		try {
			referee.join();
			} catch (InterruptedException e) {
		}
		
		GenericIO.writelnString("The Referee  has terminated.");
		
		GenericIO.writelnString();
		
		try {
			benchStub.shutdown();
		}catch (RemoteException e){ 
			GenericIO.writelnString ("Referee generator remote exception on Bar shutdown: " + e.getMessage ());
		    System.exit (1);
		}
		try {
			refereeSiteStub.shutdown();
		}catch (RemoteException e){ 
			GenericIO.writelnString ("Referee generator remote exception on RefereeSite shutdown: " + e.getMessage ());
		    System.exit (1);
		}

		try {
			playgroundStub.shutdown();
		}catch (RemoteException e){ 
			GenericIO.writelnString ("Chef generator remote exception on Playground shutdown: " + e.getMessage ());
		    System.exit (1);
		}

		try {
			genReposStub.shutdown();
		}catch (RemoteException e){ 
			GenericIO.writelnString ("Chef generator remote exception on GeneralRepo shutdown: " + e.getMessage ());
		    System.exit (1);
		}
	}


}
