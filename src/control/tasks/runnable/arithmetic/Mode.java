package control.tasks.runnable.arithmetic;

import control.io.Pipe;
import control.tasks.tasksuper.Task;
import main.data.types.numbers.DoubleAndString;
import main.data.types.numbers.ShareData;
import model.statslibrary.StatLibrary;

public class Mode extends Task implements Runnable{
	
	private boolean isReady;
	private long memoryNeeded;
	private long actions;;
	
	private double[] data;
	private Pipe<ShareData> toControl;
	private StatLibrary stat;
	
	
	public Mode(double[] array, Pipe<ShareData> pipe) {
		super(array, pipe);
		data = array;
		toControl = pipe;
		stat= new StatLibrary();
	}

	@Override
	public void run() {
		
		Double avg = 0.0;
			
		try {
			avg = stat.mode(data);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(data == null) {
			
		}
		ShareData ret = new DoubleAndString(avg, "Mode");
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

