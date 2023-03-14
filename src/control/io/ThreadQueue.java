package control.io;
	
	import java.util.LinkedList;

	public class ThreadQueue<T> {
	    private final LinkedList<T> queue;
	    private final int capacity;

	    public ThreadQueue(int capacity) {
	        this.queue = new LinkedList<>();
	        this.capacity = capacity;
	    }

	    public synchronized void put(T element) throws InterruptedException {
	        while (queue.size() == capacity) {
	            wait();
	        }
	        queue.add(element);
	        notifyAll();
	    }

	    public synchronized T take() throws InterruptedException {
	        while (queue.isEmpty()) {
	            wait();
	        }
	        T element = queue.remove();
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



