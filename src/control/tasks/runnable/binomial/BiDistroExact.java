package control.tasks.runnable.binomial;

import control.io.Pipe;
import control.tasks.tasksuper.Task;
import main.data.types.numbers.DoubleAndString;
import main.data.types.numbers.ShareData;
import model.statslibrary.StatLibrary;

public class BiDistroExact extends Task implements Runnable{
	
	private boolean isReady;
	private long memoryNeeded;
	private long actions;;
	
	private double[] data;
	private Pipe<ShareData> toControl;
	private StatLibrary stat;
	
	
	public BiDistroExact(double[] number, Pipe<ShareData> pipe) {
		super(number, pipe);
		data = number;
		toControl = pipe;
		stat= new StatLibrary();
	}

	@Override
	public void run() {
		
		double probability = -1;
			
		try {
			probability = stat.binomialPmf(data[0], data[1], data[2]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		ShareData ret = new DoubleAndString(probability, "biDistroExact");
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
