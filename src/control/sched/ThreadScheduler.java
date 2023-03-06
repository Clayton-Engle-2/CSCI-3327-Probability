package control.sched;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import javax.swing.SwingUtilities;

import main.data.structures.RedBlackNode;
import main.data.structures.RedBlackTree;

public class ThreadScheduler {
	
	 private ThreadExecutor executor;
	 private RedBlackTree<Double> readyQueue;
	 private SchedQueue threadSafe;
	 
	 public ThreadScheduler() {
		 readyQueue = new RedBlackTree<Double>();
		 threadSafe = new SchedQueue(2);
		 executor = createThreadExecutor(threadSafe);
	 }
	 
	 public void schedule(RedBlackNode<Double> task) {
		 readyQueue.insert(task);
		 System.out.println("scheduling task");
	 }
	 public void update() {
		 if(threadSafe.isEmpty() && readyQueue.size() > 0) {
			 while(threadSafe.isEmpty() && readyQueue.size() > 0) {
				 executor.execute(readyQueue.treeMinimum().getTTask());
				 readyQueue.remove(readyQueue.treeMinimum());
			 }
		 }
	 }
	 
	
	 public ThreadExecutor createThreadExecutor(SchedQueue queue) {
	        int availableCpus = Runtime.getRuntime().availableProcessors();
	        int guiThreadCount = getGuiThreadCount();
	        
	        int numThreads = Math.max(1, availableCpus - guiThreadCount - 1);

	        return new ThreadExecutor(numThreads, queue);
	    }

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
	 
	
	
	


