package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.Mode;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class ModeFactory extends TaskFactory{
	
	@Override
	public Task makeTask(double[] array, Pipe<ShareData> pipe){
		
		Task median = new Mode(array, pipe);
		return median;
	}

}
