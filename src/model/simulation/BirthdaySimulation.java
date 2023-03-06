package model.simulation;

import model.birthdayparadox.BirthdayParadox;

public class BirthdaySimulation {
	
	private double[][] results;
	private int peopleStart;
	private int peopleEnd;
	private int simLength;
	private int trialsEachParam;
	
	public BirthdaySimulation(int peopleMin, int peopleMax, int simulationLength, int trialsEach) {
		if(peopleMin >= 2)
			peopleStart = peopleMin;
		else
			peopleStart = 2;
		
		peopleEnd = peopleMax;
		simLength = simulationLength;
		trialsEachParam = trialsEach;
		results = new double[trialsEachParam][(peopleEnd+1) - peopleStart];
	}
	
	public void runSimulation() {
	    BirthdayParadox tester = new BirthdayParadox();
	    for(int i = peopleStart; i <= peopleEnd; i++) {
	        for(int j = 0; j < trialsEachParam; j++) { // fix: loop should stop at trialsEachParam
	            if(j == 0)
	                results[j][i-peopleStart] = i;  // fix: swap indices i and j-1
	            else
	                results[j][i-peopleStart] = tester.beginSimulation(i, simLength);
	        }
	    }
	    CsvWriter writer = new CsvWriter();
	    writer.writeToCsvFile(results, "bday");
	}

}

