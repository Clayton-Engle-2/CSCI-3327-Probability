package control.tasks.runnable.sims;

import control.io.Pipe;
import control.tasks.tasksuper.Task;
import main.data.types.numbers.DoubleAndString;
import main.data.types.numbers.ShareData;
import model.birthdayparadox.BirthdayParadox;

public class Birthday extends Task implements Runnable{
	
	private boolean isReady;
	private long memoryNeeded;
	private long actions;;
	
	private int simNumber;
	private int people;
	private Pipe<ShareData> toControl;
	private BirthdayParadox simulation;
	
	
	public Birthday(double number1, double number2, Pipe<ShareData> pipe) {
		super(number1, number2, pipe);
		simNumber = (int)number1;
		people = (int)number2;
		toControl = pipe;
		simulation = new BirthdayParadox();
	}
	@Override
	public void run() {
		
		double results = 0.0;
			
		try {
			results = simulation.beginSimulation(people, simNumber);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		ShareData ret = new DoubleAndString(results, "Birthday Paradox");
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


