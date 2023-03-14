package main.data.structures.lists.single;

public class SinglyLinkedList<T> {
	
    private SingleLinkNode<T> head;
    private int size;
    
    public SinglyLinkedList() {
    	size = 0;
    }

    public void add(T data) {
        SingleLinkNode<T> node = new SingleLinkNode<T>(data);
        if (head == null) {
            head = node;
        } else {
            SingleLinkNode<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(node);
        }
        size++;
    }

    public void addFront(T data) {
        SingleLinkNode<T> node = new SingleLinkNode<T>(data);
        node.setNext(head);
        head = node;
        size++;
    }

    public void remove(T data) {
        if (head == null) {
            return;
        }

        if (head.getData().equals(data)) {
            head = head.getNext();
            return;
        }

        SingleLinkNode<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                current.setNext(current.getNext().getNext());
                return;
            }
            current = current.getNext();
        }
        size--;
    }
    
    public SingleLinkNode<T> getHead(){
    	return this.head;
    }

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
