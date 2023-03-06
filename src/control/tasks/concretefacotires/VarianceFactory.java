package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.Variance;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class VarianceFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double[] array, Pipe<ShareData> pipe){
		
		Task variance = new Variance(array, pipe);
		return variance;
	}

}
