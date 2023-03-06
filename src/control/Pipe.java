package control;

public class Pipe<T> {

	private ThreadQueue<T> queue;

	public Pipe() {
		this.queue = new ThreadQueue<T>(4);
	}
	
	public boolean hasInput() {
		if(!queue.isEmpty())
			return true;
		else
			return false;
	}

	public void put(T item) {
		try {
			this.queue.put(item);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}

	public T take() {
		try {
			return this.queue.take();
		} catch (InterruptedException e) {			
			e.printStackTrace();
			return null;
		}
	}
	


}
