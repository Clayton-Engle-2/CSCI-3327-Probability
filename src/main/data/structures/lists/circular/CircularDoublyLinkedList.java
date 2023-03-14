package main.data.structures.lists.circular;

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
    /*
     * The list structure is circular, so both add methods here run in O(1) time complexity, since we always
     * have a reference to the front and the back of the list, as head.prev() implicitly references the back of the list.
     * 
     * Here is a normal add method, which will insert a new node to the back of the list. Below is a slightly
     * different method for specifically adding an element to the front of the list, which is useful for 
     * queue-like implementations of this list where the orientation of the list matters.
     * 
     * @param LinkedListNode, the node to be added to the list
     */
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
    
    public void addFront(T data) {
    	LinkedListNode<T> newNode = new LinkedListNode<>(data);

    	if (head == null) {
    		head = newNode;
    		head.setNext(head);
    		head.setPrev(head);
    	} else {
    		newNode.setNext(head);
    		newNode.setPrev(head.getPrev());
    		head.getPrev().setNext(newNode);
    		head.setPrev(newNode);
    		head = newNode;
    	}

    	size++;
    }
    
    /*
     * This method removes a node from the linked list that contains the given target data. It starts at the head of the 
     * list and loops through all nodes until it finds the target node. If the node is found, it is removed from the list 
     * and the links between its neighbors are adjusted accordingly. If the head node is being removed, a new head is set. 
     * If the list contains only one node, the head is set to null. The method returns true if the node was found and removed, 
     * and false otherwise. The worst-case time complexity of this method is O(n) because the loop must traverse the entire 
     * list in the worst case, but in the average case, it is closer to O(1) because the loop can exit early as soon as it 
     * finds the target node.
     * 
     * @param data, the node to be removed will contain this data
     * @return true if node is found and removed, false otherwise
     * @complexity O(n) where n is the number of nodes in the list
     */
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
    /**
     * This method removes the node at the back of the list. Since we have a reference to the head node we can quickly 
     * remove the last node. This method is useful for queue implementations of the linked list where the goal is
     * to quickly remove the node that has been in the list the longest, regardless of what data it contains
     * @return T, node data of the last node in the list.
     */
    public T removeBack() {
    	if (head == null) {
    		return null;
    	}

    	LinkedListNode<T> tail = head.getPrev();
    	T removedData = tail.getData();

    	if (size == 1) {
    		head = null;
    	} else {
    		tail.getPrev().setNext(head);
    		head.setPrev(tail.getPrev());
    	}

    	size--;
    	return removedData;
    }
    /**
     * Very similar to the remove() method above, this method finds and returns the data associated with the first node in the linked 
     * list that has a tag that matches the given target. It starts at the head of the list and loops through all nodes until it finds 
     * the target node. If the node is found, its associated data is returned. If the target is not found in the list, the method 
     * returns null. The worst-case time complexity of this method is O(n), where n is the number of nodes in the list, because in 
     * the worst case it must traverse the entire list to find the target node. However, in the average case, the time complexity is 
     * closer to O(1) because the loop can exit early as soon as it finds the target node. This make the linked list an efficient 
     * method of storing a small collection of objects
     * @param target, how we will identify which node we are trying to find
     * @return T, the node's  contents, which we are looking for
     */
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
    
    public boolean isEmpty() {
    	if (head == null) {
    		return true;
    	}
    	return false;
    }


    public int size() {
    	return size;
    }
}
