package control.tasks.concretefacotires.sims;

import control.io.Pipe;
import control.tasks.runnable.sims.MontyHall;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class MontyHallFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number, Pipe<ShareData> pipe){
			
		Task simulation = new MontyHall(number, pipe);
		return simulation;
	}

}
