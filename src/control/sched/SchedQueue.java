package control.sched;

import control.tasks.tasksuper.Task;
import main.data.structures.lists.circular.CircularDoublyLinkedList;

public class SchedQueue {
	
    private final CircularDoublyLinkedList<Task> queue;
    private final int capacity;
   
    public SchedQueue(int capacity) {
    	this.queue = new CircularDoublyLinkedList<Task>();
    	this.capacity = capacity;
    }
    /*
     * This method provides a safe and thread-safe way to add elements to the scheduling queue while ensuring that the queue 
     * doesn't exceed its capacity. By synchronizing on the queue object, it ensures that only one thread can modify the queue 
     * at a time, which can help prevent race conditions and data corruption. By using "wait" and "notifyAll", it allows threads 
     * to efficiently block and wait for space to become available in the queue, without consuming unnecessary CPU resources. 
     * 
     * First, we obtain a lock on the queue object using the "synchronized" keyword to ensure that only one thread can modify 
     * the queue at a time. It then enters a while loop that checks whether the queue is already at full capacity using the 
     * "size" method. If the queue is already full, the method calls "wait" on the queue object to block the current thread 
     * until space becomes available in the queue.
     * 
     * When space becomes available in the queue, we can add the new element to the queue using the "add" method. Finally, 
     * we call "notifyAll" on the queue object to wake up any waiting threads that were blocked on the "wait" call.
     */
    public synchronized void put(Task element) throws InterruptedException {
    	while (queue.size() == capacity) {
    		wait();
    	}
    	queue.addFront(element);
    	notifyAll();
    }
    /*
     * This method provides a safe and thread-safe way to remove elements from the scheduling queue while ensuring that the queue 
     * isn't empty. By synchronizing on the queue object, it ensures that only one thread can modify the queue at a time, which 
     * can help prevent race conditions and data corruption. By using "wait" and "notifyAll", it allows threads to efficiently 
     * block and wait for new elements to become available in the queue, without consuming unnecessary CPU resources. 
     * 
     * Just like the put() method above, we first obtains a lock on the queue object using the "synchronized" keyword to ensure 
     * that only one thread can modify the queue at a time. It then enters a while loop that checks whether the queue is empty 
     * using the "isEmpty" method. If the queue is empty, the method calls "wait" on the queue object to block the current thread 
     * until a new element is added to the queue.
     * 
     * When a new element is added to the queue, the method removes the first element from the queue using the "remove" method 
     * and assigns it to the "element" variable. Finally, it calls "notifyAll" on the queue object to wake up any waiting threads 
     * that were blocked on the "wait" call, and returns the element.
     */
    public synchronized Task take() throws InterruptedException {
    	while (queue.isEmpty()) {
    		wait();
    	}
        
    	Task element = queue.removeBack();
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

