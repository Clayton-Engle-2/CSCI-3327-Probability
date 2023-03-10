package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.Birthday;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class BirthdayFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, double number2, Pipe<ShareData> pipe){
			
		Task combination = new Birthday(number1, number2, pipe);
		return combination;
	}

}

