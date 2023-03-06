package main.data.structures;


public class LinkedStack {
	
	private int size;
	  
	  StackNode top;
	  
	  public LinkedStack() {
	    top = null;
	    size = 0;
	  }
	  
	  public boolean isEmpty() {
	    return top == null;
	  }
	  
	  public void push(double value) {
		  StackNode newNode = new StackNode(value);
		  push(newNode);
	  }
	  
	  public void push(StackNode newNode) {	    
	    newNode.next = top;
	    top = newNode;
	    size += 1;;
	  }
	  
	  public double pop() {
	    double data = top.data;
	    top = top.next;
	    size-= 1;
	    return data;
	  }
	  
	  public double[] popAll() {

		  double[] values = new double[size];
		  for (int i = size - 1; i >= 0; i--) {
			  values[i] = pop();
		  }

		  return values;
	  }
	  
	  public double peek() {
		  if (isEmpty()) {
		     return Double.NaN;
		    }
		  return top.data;
	  }

	public int getSize() {
		if(isEmpty()) {
			return 0;
		}
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	}