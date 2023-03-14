package control.tasks.concretefacotires.conditional;

import control.io.Pipe;
import control.tasks.runnable.conditional.BayesTheorem;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class BayesTheoremFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, double num2, Pipe<ShareData> pipe){
		Task probability = new BayesTheorem(number1, num2, pipe);
		return probability;
	}

}
