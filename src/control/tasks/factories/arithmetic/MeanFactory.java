package control.tasks.concretefacotires.arithmetic;

import control.io.Pipe;
import control.tasks.runnable.arithmetic.Mean;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class MeanFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double[] array, Pipe<ShareData> pipe){
		
		Task mean = new Mean(array, pipe);
		return mean;
	}

}
