package main.data.structures.lists.queue;

public class Queue<T> {
    
    private QueueNode<T> head;
    private QueueNode<T> tail;
    private int size;
    
    public Queue() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void enqueue(T data) {
        QueueNode<T> newNode = new QueueNode<T>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }
    
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T data = head.getData();
        head = head.getNext();
        size--;
        if (isEmpty()) {
            tail = null;
        }
        return data;
    }
    
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return head.getData();
    }
}

