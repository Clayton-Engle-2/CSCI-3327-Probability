package control.tasks.runnable;

import java.math.BigInteger;

import control.Pipe;
import control.tasks.tasksuper.Task;
import main.data.types.BigString;
import main.data.types.ShareData;
import model.statslibrary.StatLibrary;

public class Combination extends Task implements Runnable{
	
	private boolean isReady;
	private long memoryNeeded;
	private long actions;;
	
	private double[] data;
	private Pipe<ShareData> toControl;
	private StatLibrary stat;
	
	
	public Combination(double[] number, Pipe<ShareData> pipe) {
		super(number, pipe);
		data = number;
		toControl = pipe;
		stat= new StatLibrary();
	}

	@Override
	public void run() {
		
		BigInteger combo = null;
			
		try {
			combo = stat.combination(data[0], data[1]);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		ShareData ret = new BigString(combo, "combination");
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


