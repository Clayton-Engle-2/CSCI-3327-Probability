package main.data.structures;

public class CircularDoublyLinkedList<T> {

    private LinkedListNode<T> head;
    private int size;

    public CircularDoublyLinkedList() {
        head = null;
        size = 0;
    }
    public void add(T data, String tag) {
    	 LinkedListNode<T> newNode = new LinkedListNode<T>(data, tag);
    	 add(newNode);
    }
    public void add(T data) {
        LinkedListNode<T> newNode = new LinkedListNode<>(data);
        add(newNode);
    }

    public void add(LinkedListNode<T> newNode) {

        if (head == null) {
            head = newNode;
            head.setNext(head);
            head.setPrev(head);
        } else {
            newNode.setNext(head);
            newNode.setPrev(head.getPrev());
            head.getPrev().setNext(newNode);
            head.setPrev(newNode);
        }

        size++;
    }

    public boolean remove(T data) {
        if (head == null) {
            return false;
        }

        LinkedListNode<T> curr = head;
        do {
            if (curr.getData().equals(data)) {
                if (size == 1) {
                    head = null;
                } else {
                    curr.getPrev().setNext(curr.getNext());
                    curr.getNext().setPrev(curr.getPrev());
                    if (curr == head) {
                        head = curr.getNext();
                    }
                }

                size--;
                return true;
            }

            curr = curr.getNext();
        } while (curr != head);

        return false;
    }
    
    public T findNode(String target) {
        if (head == null) {
            return null;
        }

        LinkedListNode<T> curr = head;
        do {
            if (curr.getTag().equals(target)) {
                return curr.getData();
            }
            curr = curr.getNext();
        } while (curr != head);

        return null;
    }


    public int size() {
        return size;
    }
}
