package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.ConditionalProb;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class ConditionalProbFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, double num2, Pipe<ShareData> pipe){
		Task probability = new ConditionalProb(number1, num2, pipe);
		return probability;
	}

}

