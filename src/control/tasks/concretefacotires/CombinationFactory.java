package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.Combination;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class CombinationFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double[] number, Pipe<ShareData> pipe){
			
		Task combination = new Combination(number, pipe);
		return combination;
	}

}
