package control.tasks.client;

import control.Pipe;
import control.tasks.concretefacotires.BayesTheoremFactory;
import control.tasks.concretefacotires.BiDistroAtLeastFactory;
import control.tasks.concretefacotires.BiDistroAtMostFactory;
import control.tasks.concretefacotires.BiDistroExactFactory;
import control.tasks.concretefacotires.BinomialMeanFactory;
import control.tasks.concretefacotires.BinomialSdFactory;
import control.tasks.concretefacotires.BinomialVarianceFactory;
import control.tasks.concretefacotires.CombinationFactory;
import control.tasks.concretefacotires.ComplimentFactory;
import control.tasks.concretefacotires.ConditionalProbFactory;
import control.tasks.concretefacotires.FactorialFactory;
import control.tasks.concretefacotires.GeometricBeforeFactory;
import control.tasks.concretefacotires.GeometricMeanFactory;
import control.tasks.concretefacotires.GeometricPmfFactory;
import control.tasks.concretefacotires.GeometricStdDevFactory;
import control.tasks.concretefacotires.GeometricVarianceFactory;
import control.tasks.concretefacotires.IntersectionFactory;
import control.tasks.concretefacotires.MeanFactory;
import control.tasks.concretefacotires.MedianFactory;
import control.tasks.concretefacotires.ModeFactory;
import control.tasks.concretefacotires.PermutationFactory;
import control.tasks.concretefacotires.StandardDevFactory;
import control.tasks.concretefacotires.UnionFactory;
import control.tasks.concretefacotires.VarianceFactory;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.structures.CircularDoublyLinkedList;
import main.data.types.ShareData;


public class TaskClient {
	
	private CircularDoublyLinkedList<TaskFactory> taskList;
	private TaskFactory task;
	
	public TaskClient() {
		taskList = new CircularDoublyLinkedList<TaskFactory>();
		
		//Arithmetic Operations
		taskList.add(new MeanFactory(), "Mean");
		taskList.add(new MedianFactory(), "Median");
		taskList.add(new ModeFactory(), "Mode");
		taskList.add(new VarianceFactory(), "Variance");
		taskList.add(new StandardDevFactory(), "Std Deviation");
		
		// Set Theory operations
		taskList.add(new IntersectionFactory(), "Intersection");
		taskList.add(new UnionFactory(), "Union");
		taskList.add(new ComplimentFactory(), "Compliment");
		
		//Conditional Probability operations
		taskList.add(new FactorialFactory(), "Factorial");
		taskList.add(new CombinationFactory(), "Combination");
		taskList.add(new PermutationFactory(), "Permutation");
		taskList.add(new ConditionalProbFactory(), "P(A|B)");
		taskList.add(new BayesTheoremFactory(), "BayesTheorem");
		
		// Binomial Distribution operations
		taskList.add(new BiDistroExactFactory(), "Binomial PMF");
		taskList.add(new BiDistroAtMostFactory(), "Binomial At Most");
		taskList.add(new BiDistroAtLeastFactory(), "Binomial At Least");
		taskList.add(new BinomialVarianceFactory(), "Binomial PMF");
		taskList.add(new BinomialMeanFactory(), "Binomial At Most");
		taskList.add(new BinomialSdFactory(), "Binomial At Least");
		
		//Geometric operations
		taskList.add(new GeometricPmfFactory(), "Geometric PMF");
		taskList.add(new GeometricVarianceFactory(), "Geometric Variance");
		taskList.add(new GeometricMeanFactory(), "Geometric Mean");
		taskList.add(new GeometricStdDevFactory(), "Geometric Std Dev");
		taskList.add(new GeometricBeforeFactory(), "Geometric Before N");
		
	}
	
	public Task newTask(String opperation, double number, Pipe<ShareData> pipe) {
		task = taskList.findNode(opperation);
		return task.makeTask(number, pipe);
	}
	public Task newTask(String opperation, double number1, double number2, Pipe<ShareData> pipe) {
		task = taskList.findNode(opperation);
		return task.makeTask(number1, number2, pipe);
	}
	
	public Task newTask(String opperation, double[] array, Pipe<ShareData> pipe) {
		task = taskList.findNode(opperation);
		return task.makeTask(array, pipe);
	}
	
	public Task newTask(String opperation, double[] array1, double[] array2, Pipe<ShareData> pipe) {
		task = taskList.findNode(opperation);
		System.out.println("creating task");
		return task.makeTask(array1, array2, pipe);
	}

}
