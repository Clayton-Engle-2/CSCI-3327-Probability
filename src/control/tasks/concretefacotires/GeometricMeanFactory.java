package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.GeometricMean;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class GeometricMeanFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, Pipe<ShareData> pipe){
		Task probability = new GeometricMean(number1, pipe);
		return probability;
	}

}

