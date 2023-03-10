package control.tasks.concretefacotires.geometric;

import control.io.Pipe;
import control.tasks.runnable.geometric.GeometricStdDev;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class GeometricStdDevFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, Pipe<ShareData> pipe){
		Task probability = new GeometricStdDev(number1, pipe);
		return probability;
	}

}

