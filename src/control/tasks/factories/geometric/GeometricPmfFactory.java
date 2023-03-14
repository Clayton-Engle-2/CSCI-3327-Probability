package control.tasks.concretefacotires.geometric;

import control.io.Pipe;
import control.tasks.runnable.geometric.GeometricPmf;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class GeometricPmfFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, double num2, Pipe<ShareData> pipe){
		Task probability = new GeometricPmf(number1, num2, pipe);
		return probability;
	}

}


