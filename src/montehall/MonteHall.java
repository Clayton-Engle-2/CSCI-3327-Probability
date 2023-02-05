package montehall;

import java.util.Random;

public class MonteHall {
    
    public MonteHall(int simulations) {
    	beginSimulation(simulations);
    }
    
    public void beginSimulation(int simulations) {
    	conclusion(simulation(simulations));
    }

    public double[] simulation(int simulations) {
    	long s = System.nanoTime();
        double winStay = 0;
        double winSwitch = 0;
        Random rand = new Random();

        for (int i = 0; i < simulations; i++) {
            int carDoor = rand.nextInt(3) + 1;
            int choice = rand.nextInt(3) + 1;
            int reveal;
            do {
                reveal = rand.nextInt(3) + 1;
            } while (reveal == carDoor || reveal == choice);
            int switchChoice;
            do {
                switchChoice = rand.nextInt(3) + 1;
            } while (switchChoice == choice || switchChoice == reveal);

            if (choice == carDoor) {
                winStay++;
            }
            if (switchChoice == carDoor) {
                winSwitch++;
            }
        }
        
        double[] ret = {winStay, winSwitch, (double)simulations};
        long e = System.nanoTime();
        System.out.println("Time: "+(e - s));
        return ret;
    }
    
    public void conclusion(double[] data) {
    	 double winStayProb = data[0]/ data[2];
         double winSwitchProb = data[1]/ data[2];
         System.out.println("Win probability by staying: " + winStayProb);
         System.out.println("Win probability by switching: " + winSwitchProb);
    }
}
