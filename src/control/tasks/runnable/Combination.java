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
	
	private double data1;
	private double data2;
	private Pipe<ShareData> toControl;
	private StatLibrary stat;
	
	
	public Combination(double number1, double number2, Pipe<ShareData> pipe) {
		super(number1, number2,  pipe);
		data1 = number1;
		data2 = number2;
		toControl = pipe;
		stat= new StatLibrary();
	}

	@Override
	public void run() {
		
		BigInteger combo = null;
			
		try {
			combo = stat.combination(data1, data2);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		ShareData ret = new BigString(combo, "Combination");
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

	public double getData1() {
		return data1;
	}

	public void setData1(double data1) {
		this.data1 = data1;
	}

	public double getData2() {
		return data2;
	}

	public void setData2(double data2) {
		this.data2 = data2;
	}

}


