package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.Compliment;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class ComplimentFactory extends TaskFactory{
	
	@Override
	public Task makeTask(double[] array1, double[] array2, Pipe<ShareData> pipe){
		
		Task compliemnt = new Compliment(array1, array2, pipe);
		return compliemnt;
	}

}
