package control.sched;

public class WorkerThread extends Thread {
	
	private SchedQueue queue;
	
	public WorkerThread(SchedQueue queue) {
		this.queue = queue;
	}
        @Override
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
                    	// TODO Auto-generated catch block
                    	e1.printStackTrace();
                    }
            }
        }
    }
}



