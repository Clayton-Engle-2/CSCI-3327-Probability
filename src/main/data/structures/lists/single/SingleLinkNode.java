package main.data.structures.lists.single;


public class SingleLinkNode<T> {
    private T data;
    private SingleLinkNode<T> next;

    public SingleLinkNode(T data) {
        this.setData(data);
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public SingleLinkNode<T> getNext() {
		return next;
	}

	public void setNext(SingleLinkNode<T> next) {
		this.next = next;
	}
}
