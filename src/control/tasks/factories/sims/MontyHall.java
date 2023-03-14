package control.tasks.runnable.sims;

import control.io.Pipe;
import control.tasks.tasksuper.Task;
import main.data.types.numbers.DubArrAndString;
import main.data.types.numbers.ShareData;
import model.montyhall.MontyHallSimulation;

public class MontyHall extends Task implements Runnable{
	
	private boolean isReady;
	private long memoryNeeded;
	private long actions;;
	
	private int numberOfRuns;
	private Pipe<ShareData> toControl;
	private MontyHallSimulation sim;
	
	
	public MontyHall(double sims, Pipe<ShareData> pipe) {
		super(sims, pipe);
		numberOfRuns = (int) sims;
		toControl = pipe;
		sim = new MontyHallSimulation();
	}

	@Override
	public void run() {
		
		double[] results = new double[3];;
			
		try {
			results = sim.simulation(numberOfRuns);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		ShareData ret = new DubArrAndString(results, "Monte Hall");
		toControl.put(ret);
	}

	@Override
	public void isReady(boolean canRun) {
		isReady = canRun;	
	}

	@Override
	public boolean isReady() {
		return isReady;
	}
	@Override
	public long getMemoryNeeded() {
		return memoryNeeded;
	}
	@Override
	public void setMemoryNeeded(long memoryNeeded) {
		this.memoryNeeded = memoryNeeded;
	}
	@Override
	public long getActions() {
		return actions;
	}
	@Override
	public void setActions(long actions) {
		this.actions = actions;
	}

}
