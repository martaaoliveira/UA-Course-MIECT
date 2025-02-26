package main;
import entities.*;
import sharedRegion.*;
import genclass.GenericIO;
import java.util.ArrayList;
import java.util.List;

/**
 * Simulation of the Assignment 1 - pull the rope game 
 */

public class theGame {
    /**
     * Main program.
     * @param args
     */
    public static void main(String[] args) {
        Contestant contestants[] = new Contestant[SimulPar.P];
        Coach coach[] = new Coach[SimulPar.C];
        int strengthContestant[] = new int[SimulPar.P];
        int contestantID[] = new int[SimulPar.P];

        refereeSite refereeSite;
        playground playground;
        Bench bench;
        GeneralRepo repo;
        GenericIO.writelnString("\n" + "\tThe Rope Game\n");
        GenericIO.writelnString("Insert the name of log file:\n");

        String fileName = GenericIO.readlnString(); 

        for (int i = 0; i < SimulPar.P; i++) {
            // Generate a random number between 6 and 10
            strengthContestant[i] = (int) (Math.random() * 5) + 6;
            contestantID[i] = i; 
        }

        repo = new GeneralRepo(fileName);

        refereeSite = new refereeSite(repo);
        playground = new playground(repo);
        bench = new Bench(repo);

        List<Integer> team1IDs = new ArrayList<Integer>();
        List<Integer> team2IDs = new ArrayList<Integer>();
        // Initialize referee
        Referee referee = new Referee("Referee_", RefereeState.START_OF_THE_MATCH, playground, refereeSite, bench);
        
        // Initialize contestants
        for (int i = 0; i < 5; i++) { 
            contestants[i] = new Contestant("Contestant_" + i, i, 1, strengthContestant[i], playground, bench, refereeSite, contestants);
            team1IDs.add(i);  
            referee.addContestantID(i);
        }

        for (int i = 5; i < 10; i++) { 
            contestants[i] = new Contestant("Contestant_" + i, i, 2, strengthContestant[i], playground, bench, refereeSite, contestants);
            team2IDs.add(i);  
            referee.addContestantID(i);
        }


        // Initialize coaches with contestant IDs
        coach[0] = new Coach("Coach_" + 0, 0, 1, team1IDs, refereeSite, bench, playground);
        coach[1] = new Coach("Coach_" + 1, 1, 2, team2IDs, refereeSite, bench, playground);
        referee.addCoachID(0);
        referee.addCoachID(1);


        for (int i = 0; i < 10; i++) {
            contestants[i].start();
        }

        coach[0].start();
        coach[1].start();

        referee.start();

        GenericIO.writelnString();

        try {
            referee.join();
        } catch (InterruptedException e) {
            GenericIO.writelnString("The Referee has terminated.");
        }

        for (int i = 0; i < 10; i++) {
            try {
                contestants[i].join();
            } catch (InterruptedException e) {
                GenericIO.writelnString("The contestant " + i + " has terminated.");
            }
        }

        for (int i = 0; i < SimulPar.C; i++) {
            try {
                coach[i].join();
            } catch (InterruptedException e) {
                GenericIO.writelnString("The coach " + (i + 1) + " has terminated.");
            }
        }

        GenericIO.writelnString();
    }
}
