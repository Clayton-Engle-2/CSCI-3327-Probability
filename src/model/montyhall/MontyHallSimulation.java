package model.montyhall;

import java.util.Random;

public class MontyHallSimulation {

    /**
     * Constructor to initialize the simulation
     * 
     * @param simulations number of simulations to run
     */
    public MontyHallSimulation() {
        
    }

    /**
     * Starts the simulation
     * 
     * @param simulations number of simulations to run
     */
    public void beginSimulation(int simulations) {
        conclusion(simulation(simulations));
    }

    /**
     * Runs the simulation to determine win probability for staying and switching
     * 
     * @param simulations number of simulations to run
     * @return double array with the number of wins for staying and switching
     */
    public double[] simulation(int simulations) {
        long s = System.nanoTime(); // start time of simulation
        double winStay = 0; // number of wins by staying
        double winSwitch = 0; // number of wins by switching
        Random rand = new Random(); // random number generator

        // loop to simulate the game
        for (int i = 0; i < simulations; i++) {
            int carDoor = rand.nextInt(3) + 1; // car door location
            int choice = rand.nextInt(3) + 1; // initial choice of door
            int reveal;
            // reveal a goat door
            do {
                reveal = rand.nextInt(3) + 1;
            } while (reveal == carDoor || reveal == choice);
            int switchChoice;
            // switch to the remaining door
            do {
                switchChoice = rand.nextInt(3) + 1;
            } while (switchChoice == choice || switchChoice == reveal);

            // increment win count for staying and switching
            if (choice == carDoor) {
                winStay++;
            }
            if (switchChoice == carDoor) {
                winSwitch++;
            }
        }
        
        double[] ret = {winStay, winSwitch, (double)simulations};
        long e = System.nanoTime(); // end time of simulation
        System.out.println("Time: "+(e - s)); // print time taken
        return ret;
    }
    
    /**
     * Concludes the simulation by calculating the win probability for staying and switching
     * 
     * @param data array with the number of wins for staying and switching
     */
    public void conclusion(double[] data) {
        double winStayProb = data[0]/ data[2]; // win probability for staying
        double winSwitchProb = data[1]/ data[2]; // win probability for switching
        System.out.println("Win probability by staying: " + winStayProb);
        System.out.println("Win probability by switching: " + winSwitchProb);
    }
}
