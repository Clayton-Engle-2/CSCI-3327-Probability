package main.data.types.numbers;

public class Simulation extends ShareData{
	
	private int numberOfSimulations;
	private int people;
	private String operation;

	public Simulation(int simulationRuns, String operation){
		super(simulationRuns, operation);
		this.setOperation(operation);
		setNumberOfSimulations(simulationRuns);
	}
	public Simulation(int simulationRuns, int people, String operation){
		super(simulationRuns, people, operation);
		this.setOperation(operation);
		this.setPeople(people);
		setNumberOfSimulations(simulationRuns);
	}
	public int getNumberOfSimulations() {
		return numberOfSimulations;
	}
	public void setNumberOfSimulations(int numberOfSimulations) {
		this.numberOfSimulations = numberOfSimulations;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}

}
