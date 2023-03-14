package control.tasks.runnable.geometric;

import control.io.Pipe;
import control.tasks.tasksuper.Task;
import main.data.types.numbers.DoubleAndString;
import main.data.types.numbers.ShareData;
import model.statslibrary.StatLibrary;

public class GeometricVariance extends Task implements Runnable{
	
	private boolean isReady;
	private long memoryNeeded;
	private long actions;;
	
	private double num1;
	private Pipe<ShareData> toControl;
	private StatLibrary stat;
	
	
	public GeometricVariance(double number1, Pipe<ShareData> pipe) {
		super(number1, pipe);
		num1 = number1;
		toControl = pipe;
		stat= new StatLibrary();
	}

	@Override
	public void run() {
		
		double probability = 0.0;
			
		try {
			probability = stat.geometricVariance(num1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		ShareData ret = new DoubleAndString(probability, "Geometric Variance");
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