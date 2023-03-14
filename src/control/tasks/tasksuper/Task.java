package control.tasks.tasksuper;

import control.io.Pipe;
import main.data.types.numbers.ShareData;

public class Task implements Runnable {
	
	private boolean isReady;
	private long memoryNeeded;
	private long actions;
	
	public Task(double[] array, Pipe<ShareData> pipe) {
		
	}
	public Task(double number, Pipe<ShareData> pipe) {
		
	}
	public Task(double number1, double number2, Pipe<ShareData> pipe) {
		
	}
	public Task(double[] array1, double[] array2, Pipe<ShareData> pipe) {
		
	}
	
	public void isReady(boolean canRun) {
		isReady = canRun;	
	}
	
	

	
	public boolean isReady() {
		return isReady;
	}


	public long getMemoryNeeded() {
		return memoryNeeded;
	}


	public void setMemoryNeeded(long memoryNeeded) {
		this.memoryNeeded = memoryNeeded;
	}


	public long getActions() {
		return actions;
	}


	public void setActions(long actions) {
		this.actions = actions;
	}

	@Override
	public void run() {
		
	}
}
