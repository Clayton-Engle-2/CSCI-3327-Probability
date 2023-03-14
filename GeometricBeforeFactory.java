package control.tasks.concretefacotires.geometric;

import control.io.Pipe;
import control.tasks.runnable.geometric.GeometricBeforeN;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.numbers.ShareData;

public class GeometricBeforeFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, double num2, Pipe<ShareData> pipe){
		Task probability = new GeometricBeforeN(number1, num2, pipe);
		return probability;
	}

}
