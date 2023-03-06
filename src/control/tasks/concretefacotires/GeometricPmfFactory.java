package control.tasks.concretefacotires;

import control.Pipe;
import control.tasks.runnable.GeometricPmf;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.types.ShareData;

public class GeometricPmfFactory extends TaskFactory {
	
	@Override
	public Task makeTask(double number1, double num2, Pipe<ShareData> pipe){
		Task probability = new GeometricPmf(number1, num2, pipe);
		return probability;
	}

}


