package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.Permutation;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class PermutationFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double[] number, Pipe<ShareData> pipe){
			
		Task permutation = new Permutation(number, pipe);
		return permutation;
	}

}
