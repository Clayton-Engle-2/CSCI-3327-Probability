package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.Factorial;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;


	
public class FactorialFactory extends TaskFactory {
		
	@Override
	public Task makeTask(double number, Pipe<ShareData> pipe){
			
		Task factorial = new Factorial(number, pipe);
		return factorial;
	}

}


