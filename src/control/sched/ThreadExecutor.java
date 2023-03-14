package control.sched;

import control.tasks.tasksuper.Task;

public class ThreadExecutor {
	
	private final int size;
	private final WorkerThread[] workers;
	private final SchedQueue queue;
	private boolean shutdown;
	
	/*
	 * Constructor for implementing a thread executor, which manages a pool of worker threads that can execute 
	 * tasks in parallel. This constructor for the ThreadExecutor class takes two parameters: the size of the 
	 * thread pool, and a scheduling queue.
	 * 
	 * First, the constructor sets the size of the thread pool and the scheduling queue by assigning them to 
	 * instance variables. Then, it creates an array of worker threads of size "size" using the constructor of 
	 * the WorkerThread class.
	 * 
	 * Next, it starts each worker thread in the pool by calling the "start" method on each of the worker 
	 * threads. Starting a thread causes it to begin executing its "run" method in a separate thread of control.
	 * 
	 * @param int size, the number of worker threads to create
	 * @param SchedQueue queue, the reference to the queue where new runnable tasks can be found
	 */
	public ThreadExecutor(int size, SchedQueue queue) {
		this.size = size;
		this.queue = queue;
		workers = new WorkerThread[size];

		for (int i = 0; i < size; i++) {
			workers[i] = new WorkerThread(queue);
			workers[i].start();
		}
	}
	/*
	 * This method provides a safe and thread-safe way to add tasks to the scheduling queue. By synchronizing on the queue object, it 
	 * ensures that only one thread can modify the queue at a time, and by calling "notify" after adding the task, it wakes up any 
	 * waiting worker threads to execute the new task. 
	 * 
	 * This method first obtains a lock on the queue object using the synchronized keyword to ensure that only one thread can modify 
	 * the queue at a time. It then tries to add the task to the queue using the "put" method, which can block if the queue is full.
	 * 
	 * If an InterruptedException is thrown, the method prints the stack trace to standard error. Finally, the method calls the 
	 * "notify" method on the queue object to signal any waiting worker threads that a new task is available for execution.
	 * 
	 * @param Task, the runnable task retrieved from the scheduling queue
	 */
    public void execute(Task task){
    	synchronized (queue) {
    		try {
    			queue.put(task);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		queue.notify();
    	}
    }
    
    /*
     * This method provides a safe and thread-safe way to shut down the thread pool and ensure that all tasks are completed before shutting down. By 
     * synchronizing on the queue object and calling "notifyAll", it ensures that all worker threads are notified of the shutdown and have a chance 
     * to terminate gracefully. By calling "join" on each worker thread, it ensures that all tasks in the queue are completed before the thread pool 
     * is shut down. I like this simple approach to closing a thread pool because it can help prevent data corruption or loss due to abrupt shutdowns 
     * and  also ensures that all tasks are executed fully and properly.
     * 
     * This method first obtains a lock on the queue object using the synchronized keyword to ensure that only one thread can modify the queue at a 
     * time. It then calls the "setShutdown" method with the parameter "true" to indicate that the thread pool should be shut down. Finally, it calls 
     * "notifyAll" on the queue object to wake up all worker threads waiting on the queue, so they can check the shutdown flag and terminate 
     * if necessary.
     * 
     * Next, we use a for-each loop to iterate over all the worker threads in the pool. For each worker thread, it calls the "join" method to 
     * wait for the thread to finish executing. The "join" method blocks the current thread until the worker thread has finished executing, 
     * which ensures that all tasks in the queue have been completed before the thread pool is shut down. If an InterruptedException is 
     * thrown during the join operation, the method prints the stack trace to standard error.
     */
    public void shutdown() {
    	synchronized (queue) {
    		setShutdown(true);
    		queue.notifyAll();
    	}

    	for (WorkerThread worker : workers) {
    		try {
    			worker.join();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }
    /*
     * Below are getters and setters for private fields which may be needed
     */
	public boolean isShutdown() {
		return shutdown;
	}

	public void setShutdown(boolean shutdown) {
		this.shutdown = shutdown;
	}

	public int getSize() {
		return size;
	}
}

  