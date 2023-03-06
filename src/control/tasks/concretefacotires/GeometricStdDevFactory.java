package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.GeometricStdDev;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class GeometricStdDevFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, Pipe<ShareData> pipe){
		Task probability = new GeometricStdDev(number1, pipe);
		return probability;
	}

}

