package control.tasks.concretefacotires.binomial;

import control.io.Pipe;
import control.tasks.runnable.binomial.BiDistroAtMost;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class BiDistroAtMostFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double[] number, Pipe<ShareData> pipe){
			
		Task probability = new BiDistroAtMost(number, pipe);
		return probability;
	}

}
