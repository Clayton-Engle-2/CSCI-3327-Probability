package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.MontyHall;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class MontyHallFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number, Pipe<ShareData> pipe){
			
		Task simulation = new MontyHall(number, pipe);
		return simulation;
	}

}
