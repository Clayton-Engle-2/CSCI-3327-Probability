package control.tasks.runnable;

import java.math.BigInteger;

import control.Pipe;
import control.tasks.tasksuper.Task;
import main.data.types.BigString;
import main.data.types.ShareData;
import model.statslibrary.StatLibrary;

public class Permutation extends Task implements Runnable{
	
	private boolean isReady;
	private long memoryNeeded;
	private long actions;;
	
	private double data1;
	private double data2;
	private Pipe<ShareData> toControl;
	private StatLibrary stat;
	
	
	public Permutation(double number1, double number2, Pipe<ShareData> pipe) {
		super(number1, number2, pipe);
		data1 = number1;
		data2 = number2;
		toControl = pipe;
		stat= new StatLibrary();
	}

	@Override
	public void run() {
		
		BigInteger permu = null;
			
		try {
			permu = stat.permutation(data1, data2);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		ShareData ret = new BigString(permu, "permutation");
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
