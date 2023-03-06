package control.tasks.runnable;

import control.Pipe;
import control.tasks.tasksuper.Task;
import main.data.types.DoubleAndString;
import main.data.types.ShareData;
import model.statslibrary.StatLibrary;

public class Median extends Task implements Runnable{
	
	private boolean isReady;
	private long memoryNeeded;
	private long actions;;
	
	private double[] data;
	private Pipe<ShareData> toControl;
	private StatLibrary stat;
	
	
	public Median(double[] array, Pipe<ShareData> pipe) {
		super(array, pipe);
		data = array;
		toControl = pipe;
		stat= new StatLibrary();
	}

	@Override
	public void run() {
		
		double avg = 0;
			
		try {
			avg = stat.median(data);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		ShareData ret = new DoubleAndString(avg, "median");
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

