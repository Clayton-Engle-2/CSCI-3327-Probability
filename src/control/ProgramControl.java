package control;

import control.sched.ThreadScheduler;
import control.tasks.tasksuper.Task;
import main.data.structures.RedBlackNode;
import main.data.structures.SingleLinkNode;
import main.data.structures.SinglyLinkedList;
import main.data.types.DataToStringConverter;
import main.data.types.DisplayData;
import main.data.types.DisplaySolution;
import main.data.types.ShareData;
import main.data.types.StringToTaskConverter;

public class ProgramControl {
	
//In the control class
	private StringToTaskConverter convertInput;;
	private ThreadScheduler scheduler;
	private DataToStringConverter convertResults;
	private Pipe<ShareData> well;
//Return to view
	private Pipe<DisplaySolution> viewOut; 
	
//Input from model
	private SinglyLinkedList<Pipe<ShareData>> modelIn;
	
//Input from view
	private Pipe<DisplayData> viewIn;
	
	public ProgramControl( Pipe<DisplayData> viewIn, Pipe <DisplaySolution> viewOut) {
		//Pipes to the GUI
		this.viewIn = viewIn;
		this.viewOut = viewOut;
		
		//this. =  controlToViewBig;
		convertInput = new StringToTaskConverter();
		modelIn = new SinglyLinkedList<Pipe<ShareData>>();
		scheduler = new ThreadScheduler();
		convertResults = new DataToStringConverter();
		well = new Pipe<ShareData>();
	}
	
	public void run() {
		while(true) {
			handleViewInput();
			handleModelIn();
			scheduler.update();
		}
	}
	
	/**
	 * These methods are responsible for taking user input, converting 
	 * the Strings into usable data types, and passing them into the
	 * TaskClient to make new tasks. The newly created task is then 
	 * added to the task queue for the scheduler
	 */
	public void handleViewInput() {
		if(viewIn.hasInput() == true) {
			sendToScheduler(convertInput.ToTask(viewIn.take(), well));
		}
	}
	
	public void sendToScheduler(Task task) {
		RedBlackNode<Double>  job = new RedBlackNode<Double>(1.0, task);
		scheduler.schedule(job);
	}
	
	public void handleModelIn() {
		if(well.hasInput()) {
			viewOut.put(convertResults.toDisplay(well.take()));
		}
	}		
}
