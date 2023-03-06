package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.BinomialSD;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class BinomialSdFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, double num2, Pipe<ShareData> pipe){
		Task probability = new BinomialSD(number1, num2, pipe);
		return probability;
	}

}


