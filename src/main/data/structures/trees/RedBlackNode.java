package main.data.structures.trees;

import control.tasks.tasksuper.Task;

/**
 */ // class RedBlackNode
public class RedBlackNode<T extends Comparable<T>> {
    /** Possible color for this node */
    public static final int BLACK = 0;
    /** Possible color for this node */
    public static final int RED = 1;
	// the key of each node
	private T key;
    /** Parent of node */
    private RedBlackNode<T> parent;
    /** Left child */
    private RedBlackNode<T> left;
    /** Right child */
    private RedBlackNode<T> right;
    // the number of elements to the left of each node
    private int numLeft = 0;
    // the number of elements to the right of each node
    private int numRight = 0;
    // the color of a node
    private int color;
    
    private Task task;
    
    RedBlackNode(){
        setColor(BLACK);
        setNumLeft(0);
        setNumRight(0);
        setParent(null);
        setLeft(null);
        setRight(null);
    }

	// Constructor which sets key to the argument.
	public RedBlackNode(T key2) {
        this.setKey(key2);
	}
	
	public RedBlackNode(T key, Task task) {
        this.setKey(key);
        this.task = task;
	}
	public Task getTTask() {
		return task;
	}
	

	public RedBlackNode<T> getParent() {
		return parent;
	}

	public void setParent(RedBlackNode<T> parent) {
		this.parent = parent;
	}

	public RedBlackNode<T> getLeft() {
		return left;
	}

	public void setLeft(RedBlackNode<T> left) {
		this.left = left;
	}

	public RedBlackNode<T> getRight() {
		return right;
	}

	public void setRight(RedBlackNode<T> right) {
		this.right = right;
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public int getNumLeft() {
		return numLeft;
	}

	public void setNumLeft(int numLeft) {
		this.numLeft = numLeft;
	}

	public int getNumRight() {
		return numRight;
	}

	public void setNumRight(int numRight) {
		this.numRight = numRight;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}// end class RedBlackNode
