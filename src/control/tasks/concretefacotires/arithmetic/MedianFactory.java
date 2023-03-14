package control.tasks.concretefacotires.arithmetic;

import control.io.Pipe;
import control.tasks.runnable.arithmetic.Median;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class MedianFactory extends TaskFactory{
	
	@Override
	public Task makeTask(double[] array, Pipe<ShareData> pipe){
		
		Task median = new Median(array, pipe);
		return median;
	}

}
