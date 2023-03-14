package control.tasks.concretefacotires.conditional;

import control.io.Pipe;
import control.tasks.runnable.conditional.Permutation;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class PermutationFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, double number2, Pipe<ShareData> pipe){
			
		Task permutation = new Permutation(number1, number2, pipe);
		return permutation;
	}

}
