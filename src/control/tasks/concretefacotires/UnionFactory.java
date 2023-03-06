package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.Union;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class UnionFactory extends TaskFactory{
	
	@Override
	public Task makeTask(double[] array1, double[] array2, Pipe<ShareData> pipe){
		
		Task union = new Union(array1, array2, pipe);
		return union;
	}

}


