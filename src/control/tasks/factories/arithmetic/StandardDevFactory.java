package control.tasks.concretefacotires.arithmetic;

import control.io.Pipe;
import control.tasks.runnable.arithmetic.StandardDev;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class StandardDevFactory extends TaskFactory{
	
	@Override
	public Task makeTask(double[] array, Pipe<ShareData> pipe){
		
		Task stdDev = new StandardDev(array, pipe);
		return stdDev;
	}

}
