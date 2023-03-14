package control.tasks.concretefacotires.binomial;

import control.io.Pipe;
import control.tasks.runnable.binomial.BiDistroAtLeast;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class BiDistroAtLeastFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double[] number, Pipe<ShareData> pipe){
			
		Task probability = new BiDistroAtLeast(number, pipe);
		return probability;
	}

}

