package control.tasks.concretefacotires.binomial;

import control.io.Pipe;
import control.tasks.runnable.binomial.BinomialVariance;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class BinomialVarianceFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, double num2, Pipe<ShareData> pipe){
		Task probability = new BinomialVariance(number1, num2, pipe);
		return probability;
	}

}


