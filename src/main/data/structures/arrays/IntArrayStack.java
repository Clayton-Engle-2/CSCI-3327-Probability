package main.data.structures.arrays;


	
	public class IntArrayStack {
	    private int[] stack;
	    private int top;
	    private int capacity;

	    public IntArrayStack(int capacity) {
	        stack = new int[capacity];
	        top = -1;
	        this.capacity = capacity;
	    }

	    public boolean isEmpty() {
	        return top == -1;
	    }

	    public boolean isFull() {
	        return top == capacity - 1;
	    }

	    public int peek() {
	        if (isEmpty()) {
	            throw new IllegalStateException("Stack is empty");
	        }
	        return stack[top];
	    }

	    public int pop() {
	        if (isEmpty()) {
	            throw new IllegalStateException("Stack is empty");
	        }
	        return stack[top--];
	    }

	    public void push(int value) {
	        if (isFull()) {
	            throw new IllegalStateException("Stack is full");
	        }
	        stack[++top] = value;
	    }

	    public int size() {
	        return top + 1;
	    }
	}



