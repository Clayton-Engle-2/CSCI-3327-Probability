package control.tasks.concretefacotires.binomial;

import control.io.Pipe;
import control.tasks.runnable.binomial.BinomialMean;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class BinomialMeanFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, double num2, Pipe<ShareData> pipe){
		Task probability = new BinomialMean(number1, num2, pipe);
		return probability;
	}

}


