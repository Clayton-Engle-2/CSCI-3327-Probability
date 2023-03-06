package control.sched;

import java.util.LinkedList;

import control.tasks.tasksuper.Task;

public class SchedQueue {
    private final LinkedList<Task> queue;
    private final int capacity;
   
    public SchedQueue(int capacity) {
        this.queue = new LinkedList<Task>();
        this.capacity = capacity;
    }

    public synchronized void put(Task element) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(element);
        notifyAll();
    }

    public synchronized Task take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        
        Task element = queue.remove();
        notifyAll();
        return element;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    public synchronized int size() {
        return queue.size();
    }
}

