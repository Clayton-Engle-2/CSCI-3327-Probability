package control.tasks.client;

import control.io.Pipe;
import control.tasks.concretefacotires.arithmetic.MeanFactory;
import control.tasks.concretefacotires.arithmetic.MedianFactory;
import control.tasks.concretefacotires.arithmetic.ModeFactory;
import control.tasks.concretefacotires.arithmetic.StandardDevFactory;
import control.tasks.concretefacotires.arithmetic.VarianceFactory;
import control.tasks.concretefacotires.binomial.BiDistroAtLeastFactory;
import control.tasks.concretefacotires.binomial.BiDistroAtMostFactory;
import control.tasks.concretefacotires.binomial.BiDistroExactFactory;
import control.tasks.concretefacotires.binomial.BinomialMeanFactory;
import control.tasks.concretefacotires.binomial.BinomialSdFactory;
import control.tasks.concretefacotires.binomial.BinomialVarianceFactory;
import control.tasks.concretefacotires.conditional.BayesTheoremFactory;
import control.tasks.concretefacotires.conditional.CombinationFactory;
import control.tasks.concretefacotires.conditional.ConditionalProbFactory;
import control.tasks.concretefacotires.conditional.FactorialFactory;
import control.tasks.concretefacotires.conditional.PermutationFactory;
import control.tasks.concretefacotires.geometric.GeometricBeforeFactory;
import control.tasks.concretefacotires.geometric.GeometricMeanFactory;
import control.tasks.concretefacotires.geometric.GeometricPmfFactory;
import control.tasks.concretefacotires.geometric.GeometricStdDevFactory;
import control.tasks.concretefacotires.geometric.GeometricVarianceFactory;
import control.tasks.concretefacotires.sets.ComplimentFactory;
import control.tasks.concretefacotires.sets.IntersectionFactory;
import control.tasks.concretefacotires.sets.UnionFactory;
import control.tasks.concretefacotires.sims.BirthdayFactory;
import control.tasks.concretefacotires.sims.MontyHallFactory;
import control.tasks.tasksuper.Task;
import control.tasks.tasksuper.TaskFactory;
import main.data.structures.lists.circular.CircularDoublyLinkedList;
import main.data.types.numbers.ShareData;


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
		taskList.add(new BayesTheoremFactory(), "Bayes Theorem");
		
		// Binomial Distribution operations
		taskList.add(new BiDistroExactFactory(), "Binomial PMF");
		taskList.add(new BiDistroAtMostFactory(), "Binomial At Most");
		taskList.add(new BiDistroAtLeastFactory(), "Binomial At Least");
		taskList.add(new BinomialVarianceFactory(), "Binomial Variance");
		taskList.add(new BinomialMeanFactory(), "Binomial Mean");
		taskList.add(new BinomialSdFactory(), "Binomial Std Dev");
		
		//Geometric operations
		taskList.add(new GeometricPmfFactory(), "Geometric PMF");
		taskList.add(new GeometricVarianceFactory(), "Geometric Variance");
		taskList.add(new GeometricMeanFactory(), "Geometric Mean");
		taskList.add(new GeometricStdDevFactory(), "Geometric Std Dev");
		taskList.add(new GeometricBeforeFactory(), "Geometric Before N");
		
		// Simulation programs
		taskList.add(new MontyHallFactory(), "Monty Hall");
		taskList.add(new BirthdayFactory(), "Birthday Paradox");
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
