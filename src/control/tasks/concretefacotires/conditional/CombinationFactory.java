package control.tasks.concretefacotires.conditional;

import control.io.Pipe;
import control.tasks.runnable.conditional.Combination;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class CombinationFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, double number2, Pipe<ShareData> pipe){
			
		Task combination = new Combination(number1, number2, pipe);
		return combination;
	}

}
