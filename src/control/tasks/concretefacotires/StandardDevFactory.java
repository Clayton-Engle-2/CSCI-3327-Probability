package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.StandardDev;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class StandardDevFactory extends TaskFactory{
	
	@Override
	public Task makeTask(double[] array, Pipe<ShareData> pipe){
		
		Task stdDev = new StandardDev(array, pipe);
		return stdDev;
	}

}
