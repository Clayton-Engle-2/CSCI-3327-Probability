package control.sched;

import control.tasks.tasksuper.Task;

public class ThreadExecutor {
    private final int size;
    private final WorkerThread[] workers;
    private final SchedQueue queue;
    private boolean shutdown;

    public ThreadExecutor(int size, SchedQueue queue) {
        this.size = size;
        this.queue = queue;
        workers = new WorkerThread[size];

        for (int i = 0; i < size; i++) {
            workers[i] = new WorkerThread(queue);
            workers[i].start();
        }
    }

    public void execute(Task task){
        synchronized (queue) {
            try {
				queue.put(task);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            queue.notify();
        }
    }
    

    public void shutdown() {
        synchronized (queue) {
            shutdown = true;
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
}

  