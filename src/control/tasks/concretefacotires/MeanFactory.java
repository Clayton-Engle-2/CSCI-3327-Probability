package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.Mean;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class MeanFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double[] array, Pipe<ShareData> pipe){
		
		Task mean = new Mean(array, pipe);
		return mean;
	}

}
