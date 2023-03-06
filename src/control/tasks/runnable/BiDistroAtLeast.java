package control.tasks.runnable;

import control.Pipe;
import control.tasks.tasksuper.Task;
import main.data.types.DoubleAndString;
import main.data.types.ShareData;
import model.statslibrary.StatLibrary;

public class BiDistroAtLeast extends Task implements Runnable{
	
	private boolean isReady;
	private long memoryNeeded;
	private long actions;;
	
	private double[] data;
	private Pipe<ShareData> toControl;
	private StatLibrary stat;
	
	
	public BiDistroAtLeast(double[] number, Pipe<ShareData> pipe) {
		super(number, pipe);
		data = number;
		toControl = pipe;
		stat= new StatLibrary();
	}

	@Override
	public void run() {
		
		double probability = -1;
			
		try {
			probability = stat.binomialDistributionAtLeast(data[0], data[1], data[2]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		ShareData ret = new DoubleAndString(probability, "biDistroAtLeast");
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

