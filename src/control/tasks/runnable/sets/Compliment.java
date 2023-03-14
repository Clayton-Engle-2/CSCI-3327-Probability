package control.tasks.runnable.sets;

import control.io.Pipe;
import control.tasks.tasksuper.Task;
import main.data.types.numbers.DubArrAndString;
import main.data.types.numbers.ShareData;
import model.statslibrary.StatLibrary;

public class Compliment extends Task implements Runnable{
	
	private boolean isReady;
	private long memoryNeeded;
	private long actions;;
	
	private double[] data1;
	private double[] data2;
	private Pipe<ShareData> toControl;
	private StatLibrary stat;
	
	
	public Compliment(double[] array1, double[] array2, Pipe<ShareData> pipe) {
		super(array1, array2, pipe);
		data1 = array1;
		data2 = array2;
		toControl = pipe;
		stat= new StatLibrary();
	}

	@Override
	public void run() {
		
		double[] comp = null;
			
		try {
			comp = stat.compliment(data1, data2);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		ShareData ret = new DubArrAndString(comp, "mean");
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

