package control.sched;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import javax.swing.SwingUtilities;

import main.data.structures.trees.RedBlackNode;
import main.data.structures.trees.RedBlackTree;

public class ThreadScheduler {
	
	 private ThreadExecutor executor;
	 private RedBlackTree<Double> readyQueue;
	 private SchedQueue threadSafe;
	 
	 public ThreadScheduler() {
		 readyQueue = new RedBlackTree<Double>();
		 threadSafe = new SchedQueue(2);
		 executor = createThreadExecutor(threadSafe);
	 }
	 /*
	  * This public method accepts a RedBlackNode<Double>, which it will add to the 
	  * tree for processing.
	  * 
	  * @param RedBlackNode, which will be added to the scheduling tree
	  */
	 public void schedule(RedBlackNode<Double> task) {
		 readyQueue.insert(task);
	 }
	 
	 /*
	  * This method is called by the program control loop. If the  thread safe queue is empty, and there are tasks
	  * waiting to run in the tree, this method takes the highest priority method from the tree and adds it to the 
	  * thread-safe queue so the worker threads can execute the task.
	  * 
	  * I'm not static about this approach to maintaining a ready queue of tasks, but for how the application is 
	  * designed currently this will work. If the control loop was held up for some reason, the scheduler will not 
	  * be updated. Currently however there is no way for a user to upload their own data set to the calculator, 
	  * so it is unlikely that the loop will be caught up converting a large data set. 
	  * 
	  * In the future if a user can read a CSV in for processing the worker threads will need to pull tasks from 
	  * the tree directly, so a synchronized treeMinimum() method in the RedBlackTree class would be useful. The
	  * worker threads run separately from the control, so if the control loop is occupied the worker threads 
	  * can find tasks on their own.
	  */
	 public void update() {
		 if(threadSafe.isEmpty() && readyQueue.size() > 0) {
			 
			 while(threadSafe.isEmpty() && readyQueue.size() > 0) {
				 executor.execute(readyQueue.treeMinimum().getTTask());
				 readyQueue.remove(readyQueue.treeMinimum());
			 }
		 }
	 }
	 
	 public void shutDown() {
		 executor.shutdown();
	 }
	 /*
	  * This method is a public method that returns a ThreadExecutor object. It takes in a SchedQueue object as a parameter.
	  * 
	  * The method first initializes an integer variable availableCpus to the number of available processors on the current 
	  * machine. This value is obtained using the Runtime.getRuntime().availableProcessors() method.
	  * 
	  * Next, the method calls the getGuiThreadCount() method, which returns the number of currently running GUI threads in 
	  * the JVM. This value is stored in the guiThreadCount variable.
	  * 
	  * The number of threads that the ThreadExecutor object should create is calculated by subtracting the guiThreadCount 
	  * value and 1 (for the main thread) from the availableCpus value. The result is then passed to the Math.max() method 
	  * along with 1, to ensure that at least one thread is created even if the available CPUs and GUI threads are zero.
	  * 
	  * Finally, a new ThreadExecutor object is created with the calculated number of threads and the SchedQueue object 
	  * passed as a parameter, and returned from the method.
	  * 
	  * @param SchedQueue, the thread-safe queue reference each worker thread needs to find runnable tasks
	  * @ return ThreadExecutor, an array of worker threads, who's size depends on how many processors are available
	  */
	 public ThreadExecutor createThreadExecutor(SchedQueue queue) {
		 int availableCpus = Runtime.getRuntime().availableProcessors();
		 int guiThreadCount = getGuiThreadCount();
	        
		 int numThreads = Math.max(1, availableCpus - guiThreadCount - 1);

		 return new ThreadExecutor(numThreads, queue);
	 }
	 
	 /*
	  * This method is a private method that returns an integer value. It uses the Java Management Extensions (JMX) API to access 
	  * information about the current thread and check if a particular thread is a GUI thread or not.
	  * 
	  * First, the method obtains an instance of the ThreadMXBean class using the ManagementFactory.getThreadMXBean() method. 
	  * This bean provides access to information about the threads that are currently running in the Java Virtual Machine (JVM).
	  * 
	  * Next, the method initializes a variable guiThreadCount to keep track of the number of GUI threads that are running in the JVM.
	  * 
	  * The method then iterates through all the running threads in the JVM using a for loop. For each thread, it obtains the 
	  * thread's name using the getThreadName() method of the ThreadInfo class returned by ThreadMXBean.getThreadInfo(long threadId).
	  * 
	  * For each thread, the method checks if the thread is the Event Dispatch Thread (EDT) and has the name "AWT-EventQueue-0" 
	  * using the SwingUtilities.isEventDispatchThread() method and equals() method respectively. If the thread is a GUI thread, 
	  * the guiThreadCount variable is incremented.
	  * 
	  * Finally, the method returns the guiThreadCount value after iterating through all the threads in the JVM.
	  */
	 private int getGuiThreadCount() {
		 ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
		 int guiThreadCount = 0;
		 
		 for (long threadId : threadBean.getAllThreadIds()) {
			 String threadName = threadBean.getThreadInfo(threadId).getThreadName();
			 if (SwingUtilities.isEventDispatchThread() && threadName.equals("AWT-EventQueue-0")) {
				 guiThreadCount++;
			 }
		 }
		 return guiThreadCount;
	 }
}
	 
	
	
	


