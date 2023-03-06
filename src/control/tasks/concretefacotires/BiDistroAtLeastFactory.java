package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.BiDistroAtLeast;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class BiDistroAtLeastFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double[] number, Pipe<ShareData> pipe){
			
		Task probability = new BiDistroAtLeast(number, pipe);
		return probability;
	}

}

