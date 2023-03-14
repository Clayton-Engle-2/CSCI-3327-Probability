package control.tasks.concretefacotires.conditional;

import control.io.Pipe;
import control.tasks.runnable.conditional.Factorial;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;


	
public class FactorialFactory extends TaskFactory {
		
	@Override
	public Task makeTask(double number, Pipe<ShareData> pipe){
			
		Task factorial = new Factorial(number, pipe);
		return factorial;
	}

}


