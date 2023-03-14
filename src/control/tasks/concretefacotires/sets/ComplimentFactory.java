package control.tasks.concretefacotires.sets;

import control.io.Pipe;
import control.tasks.runnable.sets.Compliment;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class ComplimentFactory extends TaskFactory{
	
	@Override
	public Task makeTask(double[] array1, double[] array2, Pipe<ShareData> pipe){
		
		Task compliemnt = new Compliment(array1, array2, pipe);
		return compliemnt;
	}

}
