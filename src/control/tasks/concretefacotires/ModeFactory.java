package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.Median;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class ModeFactory extends TaskFactory{
	
	@Override
	public Task makeTask(double[] array, Pipe<ShareData> pipe){
		
		Task median = new Median(array, pipe);
		return median;
	}

}
