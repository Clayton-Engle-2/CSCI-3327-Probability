package control.tasks.concretefacotires.arithmetic;

import control.io.Pipe;
import control.tasks.runnable.arithmetic.Variance;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class VarianceFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double[] array, Pipe<ShareData> pipe){
		
		Task variance = new Variance(array, pipe);
		return variance;
	}

}
