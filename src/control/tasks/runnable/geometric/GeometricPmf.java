package control.tasks.runnable.geometric;

import control.io.Pipe;
import control.tasks.tasksuper.Task;
import main.data.types.numbers.DoubleAndString;
import main.data.types.numbers.ShareData;
import model.statslibrary.StatLibrary;

public class GeometricPmf extends Task implements Runnable{
	
	private boolean isReady;
	private long memoryNeeded;
	private long actions;;
	
	private double num1;
	private double num2;
	private Pipe<ShareData> toControl;
	private StatLibrary stat;
	
	
	public GeometricPmf(double number1, double number2, Pipe<ShareData> pipe) {
		super(number1, number2, pipe);
		num1 = number1;
		num2 = number2;
		toControl = pipe;
		stat= new StatLibrary();
	}

	@Override
	public void run() {
		
		double probability = 0.0;
			
		try {
			probability = stat.geometricPmf(num1, num2);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
			
		ShareData ret = new DoubleAndString(probability, "Geometric PMF");
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
