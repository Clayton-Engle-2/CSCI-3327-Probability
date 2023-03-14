package control.tasks.concretefacotires.geometric;

import control.io.Pipe;
import control.tasks.runnable.geometric.GeometricMean;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class GeometricMeanFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, Pipe<ShareData> pipe){
		Task probability = new GeometricMean(number1, pipe);
		return probability;
	}

}

