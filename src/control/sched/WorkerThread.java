package control.sched;

public class WorkerThread extends Thread {
	
	private SchedQueue queue;
	/*
	 * Every worker thread is passed a reference to the thread-safe queue from which it will pull runnable tasks
	 */
	public WorkerThread(SchedQueue queue) {
		this.queue = queue;
	}
	/*
	 * This method is a part of a Thread class which implements the Runnable interface. It is responsible for continuously running a loop, 
	 * checking a queue of Runnable tasks for any waiting tasks, and executing them one by one.
	 * 
	 * The loop is executed indefinitely, thanks to the while(true) statement. Within the loop, a Runnable task variable is declared, 
	 * which will be used to store the task retrieved from the queue.
	 * 
	 * To prevent synchronization issues, a synchronized block is used to ensure that only one thread at a time can access the queue. 
	 * Within this block, a while loop is used to check if the queue is empty, and if so, the current thread is put to sleep using wait(). 
	 * This helps prevent busy waiting and conserves CPU resources. 
	 * 
	 * When a task is available, it is retrieved using the take() method from the queue, which removes the task from the queue. The 
	 * retrieved task is then checked for nullity, and if it is not null, the run() method of the task is called to execute the task.
	 * 
	 * If an InterruptedException is thrown during the wait, the stack trace is printed to the console to aid in debugging. The same 
	 * applies when the take() method throws an InterruptedException.
	 * 
	 * @Override
	 */
	public void run() {
		while (true) {
			Runnable task;
			synchronized (queue) {
				while (queue.isEmpty()) {
					try {
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					task = queue.take();
					if(task != null)
						task.run();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}



