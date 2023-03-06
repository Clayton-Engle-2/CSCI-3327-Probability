package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.Intersection;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class IntersectionFactory extends TaskFactory{
	
	@Override
	public Task makeTask(double[] array1, double[] array2, Pipe<ShareData> pipe){
		
		Task intersect = new Intersection(array1, array2, pipe);
		return intersect;
	}


}
