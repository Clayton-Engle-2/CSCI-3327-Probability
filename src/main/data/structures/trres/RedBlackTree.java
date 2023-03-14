package main.data.structures.trees;


/*
 * Red Black Tree Data Structure with generics
 * 
 * Project Notes:
 * The enemies and objects I put in my game all needed to be updated, but updating all of them was too slow and made my game play like time slowed down
 * I decided to reduce the number of things that get updated every game loop, picking objects closest to the player
 * I assigned each moving enemy or item an attribute distance, which represented how far away (in pixels) the item is from the player
 * My initial solution was to sort the array list i was using so that it would be ordered with the closest items first, and a time limit or update limit could 
 * be set to keep the game moving.
 * This sort of worked, but not really
 * 
 * I needed a data structure faster than an array list when inserting, deleting, searching, and sorting.
 * The only option I knew of was a Binary Search Tree
 * A balanced binary search tree has a worst case time complexity of Olog(n), which would be a step up from array list O(n)
 * Is there an efficient way to keep a BST balanced?
 * 
 * Yes, it`s commonly refereed to as a Red Black Tree
 * This data structure is a specific variation of a BST where each node has an extra attribute, a color, set as either red or black
 * The high level idea is that using the red/black info, an algorithm can be used to re-balance the tree every time a node is inserted or deleted
 * 
 * Here are the properties that must be true for a tree to be a Red Black Tree::
 * 		- The root node must always be black
 * 		- All external nodes, or leaves, should be black
 * 		- Children of red nodes must be black
 * 		- Black depth must be the same throughout entire tree
 * 
 * 
 */
import java.util.ArrayList;
import java.util.List;



// Class Definitions
public class RedBlackTree<T extends Comparable<T>> {

	private RedBlackNode<T> nil = new RedBlackNode<T>();
	private RedBlackNode<T> root = nil;
	private int size;

    public RedBlackTree() {
        root.setLeft(nil);
        root.setRight(nil);
        root.setParent(nil);
        size = 0;
    }
    
    public RedBlackNode<T> getRoot() {
    	return root;
    }
   
    // Return the number of nodes to the root's left + the number of nodes on the root's right + the root itself.
    public int size(){
		return root.getNumLeft() + root.getNumRight() + 1;
	}
   	
   	/**
   	 * The treeMinimum method returns the node with the lowest key value from a specified position.
   	 * If left child is not nil, make pointer = node`s left child
   	 * When node`s left child is nil, return the current node, the minimum key value
   	 * @param node where to begin searching for the lowest key value
   	 * @return node with lowest key value
   	 */
   	public RedBlackNode<T> treeMinimum(RedBlackNode<T> node){
   		while (!isNil(node.getLeft()))
   			node = node.getLeft();
   		return node;
   	}
   	
   	//Overload that assumes to start at root
   	public RedBlackNode<T> treeMinimum(){
   		RedBlackNode<T> node = root;
   		while (!isNil(node.getLeft()))
   			node = node.getLeft();
   		return node;
   	}
 	
 	/**
 	 * @param key the value we will use to compare other nodes against
 	 * @return number of values greater than key parameter
 	 */
 	public int numGreater(T key){
 		return findNumGreater(root,key);
 	}
 	/**
 	 * @param: key, any Comparable object
 	 * @return: return's teh number of elements smaller than key
 	 */
 	public int numSmaller(T key){
 		return findNumSmaller(root,key);
 	}
 	/**
 	 * The findNumGreater method begins by checking if 'node' is nil.
 	 * If so, it returns 0 since there are no nodes with keys greater than 'key' in the 
 	 * subtree rooted at 'node'.
 	 * If 'node' is not nil, the method compares the key of 'node' with the given key.
 	 * If 'key' is less than 'node.key', then all nodes with keys greater than 'key' must 
 	 * be in the right subtree of 'node'.
 	 *  Add 1 to the total count to include 'node' itself and recursively search the 
 	 *  left subtree of 'node'.
 	 *  If 'key' is greater than or equal to 'node.key', then all nodes with keys greater 
 	 *  than 'key' must be in the right subtree of 'node'.
 	 *  Recursively search the right subtree of 'node'.
 	 *  Then return the total count of nodes with keys greater than 'key' in the 
 	 *  subtree rooted at 'node'.
 	 *  
 	 * @param node the node in the tree from which we will begin comparing
 	 * @param key the value we will use to determine whether a node is greater than
 	 * @return number of nodes whos key is greater than the parameter key
 	 */	
 	public int findNumGreater(RedBlackNode<T> node, T key){

 		if (isNil(node))
 			return 0;
 		
 		else if (key.compareTo(node.getKey()) < 0)
 			return 1+ node.getNumRight() + findNumGreater(node.getLeft(),key);

 		else
 			return findNumGreater(node.getRight(),key);
 	}
 	
 	/**
 	 * findNumSmaller works just like the findNumGreater method i describe above
 	 * except we are looking left for smaller keys this time
 	 * 
 	 * @param node node from which to begin counting smaller keys
 	 * @param key used to compare for smaller determination
 	 * @return
 	 */
	public int findNumSmaller(RedBlackNode<T> node, T key){

   		if (isNil(node)) return 0;

   		else if (key.compareTo(node.getKey()) <= 0)
   			return findNumSmaller(node.getLeft(),key);

   		else
   			return 1+ node.getNumLeft() + findNumSmaller(node.getRight(),key);
   	}

     /**
      * Returns sorted list of keys greater than key.  Size of list
      * will not exceed maxReturned
      * @param key Key to search for
      * @param maxReturned Maximum number of results to return
      * @return List of keys greater than key.  List may not exceed maxReturned
      */
 	public List<T> getGreaterThan(T key, Integer maxReturned) {
 		List<T> list = new ArrayList<T>();
 		getGreaterThan(root, key, list);
 		return list.subList(0, Math.min(maxReturned, list.size()));
 	}
     
     /**
      *  The treeSuccessor method begins by checking if the left child of 'x' is not a nil node.
      *  
      *  If so, the successor of 'x' is the minimum node in the right subtree of 'x'. This is because 
      *  any node in the right subtree of 'x' has a key greater than 'x' and the minimum node in the 
      *  right subtree has the smallest key greater than 'x'. 
      *  
      *  If the left child of 'x' is a nil node, then the successor of 'x' is the first ancestor of 'x' 
      *  whose right child is also an ancestor of 'x'.This is because any node in the right subtree of 
      *  an ancestor of 'x' has a key greater than 'x' and the first ancestor whose right child is also 
      *  an ancestor of 'x' has the smallest key greater than 'x'.
      *  
      *  To find this ancestor, the method uses a loop that moves up the tree from 'x' to its ancestors.
      *  
      *  If the parent of the current node is a right child, the loop continues moving up the tree.
      *  If the parent of the current node is a left child, then the loop exits and returns the parent node as the successor of 'x'.
      *  
      *  Finally, the method returns the successor node.
      *  
      * @param x the node whose successor is to be found
      * @return the successor node of the given node
      */
 	public RedBlackNode<T> treeSuccessor(RedBlackNode<T> x){

 		if (!isNil(x.getLeft()) )
 			return treeMinimum(x.getRight());

 		RedBlackNode<T> y = x.getParent();
 		
 		while (!isNil(y) && x == y.getRight()){
 			x = y;
 			y = y.getParent();
 		}
 		return y;
 	}
  /**
   * The search method begins by initializing a pointer 'current' to the root of the tree. 
   * Then, it enters a loop where it compares the 'key' parameter with the key of the current node. 
   * If they are equal, the current node is returned.
   * Otherwise, it moves to the left or right child of the current node depending on the comparison of the 
   * 'key' parameter with the key of the current node.
   * This process continues until the 'key' parameter is found or the end of the tree is reached.
   * Finally, if the 'key' parameter is not found, the method returns null.
   * @param key key of node we are searching for
   * @return the node who`s key matches that of the parameter
   */
 	public RedBlackNode<T> search(T key){

 		RedBlackNode<T> current = root;

 		while (!isNil(current)){

 			if (current.getKey().equals(key))
 				return current;

 			else if (current.getKey().compareTo(key) < 0)
 				current = current.getRight();

 			else
 				current = current.getLeft();
 		}
 		return null;
 	}
 		
 	//REMOVE METHODS
 	//---------------------------------------
 	
 	/*
 	 * Method:       remove()
 	 * Parameters:   RedBlackNode<T>
 	 * Visibility:   public
 	 * Description:  removes a specific node from tree and prepares tree for removal fix up
 	 
 	 Variables:
 	  		v = node to be removed
 	  		x = to be used as reference, initialized to nil
 	  		y = to be used as reference, initialized to nil
 	  		z = initialized to equal v, used as reference to v's initial status
 	  
 	  Logic:
 	  This method checks for many different conditions, and calls the appropriate methods 
 	 
 	 Check 1: Initialize y and specify node for deletion
 	  		if:   either one of z's children is nil, then we must remove z
 	  		else: we must remove the successor of z
 	  
 	  Check 2: Initialize x, Fill void where deletion will occur
 	  		* If & else statement to set x to be the left or right child of y (y can only have one child)
 	  		* link x's parent to y's parent
 	  
 	  Check 3: Reset object references to remove v
 	  		* If y's parent is nil, then x is the root
			* else if y is a left child, set x to be y's left sibling
			* else if y is a right child, set x to be y's right sibling
			* if y != z, transfer y's satellite data into z.
			
	  Clean up: Reset counter variables and call fix up if needed
			* Update the numLeft and numRight numbers which might need updating due to the deletion of z.key.
			* If y's color is black, it is a violation of the RedBlackTree properties so call removeFixup()
 	 */
 	
 	/**
 	 * @param v the node to be inserted
 	 */
 	public void remove(RedBlackNode<T> v) {
 		if(size > 0)
 			removeNode(v);
 	}
 	private void removeNode(RedBlackNode<T> v){
 		RedBlackNode<T> z = search(v.getKey());
 		RedBlackNode<T> x = nil;
 		RedBlackNode<T> y = nil;
 		size -= 1;
 	
 	//Check 1:
 		if (isNil(z.getLeft()) || isNil(z.getRight()))
 			y = z;
 		else 
 			y = treeSuccessor(z);

 	//Check 2:
 		if (!isNil(y.getLeft()))
 			x = y.getLeft();
 		else
 			x = y.getRight();

 		x.setParent(y.getParent());

 	//Check 3:
 		if (isNil(y.getParent()))
 			root = x;
 		
 		else if (!isNil(y.getParent().getLeft()) && y.getParent().getLeft() == y)
 			y.getParent().setLeft(x);
 		
 		else if (!isNil(y.getParent().getRight()) && y.getParent().getRight() == y)
 			y.getParent().setRight(x);
 		
 		if (y != z){
 			z.setKey(y.getKey());
 		}

 		//Clean up
 		fixNodeData(x,y);
 		if (y.getColor() == RedBlackNode.BLACK)
 			removeFixup(x);
 	}
 	
 	/*
 	  * Method:        removeFixup()
 	  * Parameters:    RedBlackNode<T>
 	  * Visibility:    private
 	  * Description:   The removeFixup method takes the child of the removed node and will alter the RedBlackTree to restore it's original properties.
 	  
 	  Variables:
 	  		x = child of removed node
 	  		w = reference node
 	  
 	  Logic:
 	  While loop runs as long as the tree has not been fixed completely. Will check if RedBlackTree properties are being upheld. When infractions occur, the if
 	  else statements will handle the problem accordingly
 	  
 	  		LEFT CHILD CASE:
 	  		if x is it's parent's left child, set w = x's sibling
 	 			* Left Case 1: if w's color is red, re-color and left rotate around x`s parent 
 	  			* Left Case 2: if both of w's children are black, then re-color w to red
 	  			* Left Case 3 / Case 4:
 	  				- Left Case 3: if w's right child is black, re-color and right rotate around w
 	  				 -Left Case 4: w = black, w.right = red, left rotate around x parent
 	  
 	  		RIGHT CHILD CASE:
 	  		else x is it's parent's right child, set w to x's sibling
 	  			* Right Case 1: w's color is red
 	  			* Right Case 2: both of w's children are black
 	 			 * Right Case 3 / Case 4:
 	 				- Right Case 3: w's left child is black
 	 				- Right Case 4: w = black, and w.left = red
 	 				
 	 While loop ends: all properties of RedBlackTree are in order				
 	 Set x to black to ensure there is no violation of RedBlack tree properties
 	 *@param node to be removed
 	 */
 	private void removeFixup(RedBlackNode<T> x){
 		RedBlackNode<T> w;

 		while (x != root && x.getColor() == RedBlackNode.BLACK){

 			if (x == x.getParent().getLeft()){
 				w = x.getParent().getRight(); 				
 		//Left Case 1:
 				if (w.getColor() == RedBlackNode.RED){
 					w.setColor(RedBlackNode.BLACK);
 					x.getParent().setColor(RedBlackNode.RED);
 					leftRotate(x.getParent());
 					w = x.getParent().getRight();
 				}
 		//Left Case 2: 
 				if (w.getLeft().getColor() == RedBlackNode.BLACK && w.getRight().getColor() == RedBlackNode.BLACK){
 					w.setColor(RedBlackNode.RED);
 					x = x.getParent();
 				} 				
 		// Left Case 3 / Case 4:
 				else{ 					
 		//Left Case 3:
 					if (w.getRight().getColor() == RedBlackNode.BLACK){
 						w.getLeft().setColor(RedBlackNode.BLACK);
 						w.setColor(RedBlackNode.RED);
 						rightRotate(w);
 						w = x.getParent().getRight();
 					}		
 		//Left Case 4:
 					w.setColor(x.getParent().getColor());
 					x.getParent().setColor(RedBlackNode.BLACK);
 					w.getRight().setColor(RedBlackNode.BLACK);
 					leftRotate(x.getParent());
 					x = root;
 				}
 			}
 			
 			else{
 				w = x.getParent().getLeft();
 		//Right Case 1: 
 				if (w.getColor() == RedBlackNode.RED){
 					w.setColor(RedBlackNode.BLACK);
 					x.getParent().setColor(RedBlackNode.RED);
 					rightRotate(x.getParent());
 					w = x.getParent().getLeft();
 				}
 		//Right Case 2:
 				if (w.getRight().getColor() == RedBlackNode.BLACK && w.getLeft().getColor() == RedBlackNode.BLACK){
 					w.setColor(RedBlackNode.RED);
 					x = x.getParent();
 				}

 		//Right Case 3 / Case 4:
 				else{					
 		//Right Case 3:
 					 if (w.getLeft().getColor() == RedBlackNode.BLACK){
 						w.getRight().setColor(RedBlackNode.BLACK);
 						w.setColor(RedBlackNode.RED);
 						leftRotate(w);
 						w = x.getParent().getLeft();
 					}
 		// Right Case 4:
 					w.setColor(x.getParent().getColor());
 					x.getParent().setColor(RedBlackNode.BLACK);
 					w.getLeft().setColor(RedBlackNode.BLACK);
 					rightRotate(x.getParent());
 					x = root;
 				}
 			}
 		}
 		x.setColor(RedBlackNode.BLACK);
 	}

 	//INSERTION METHODS
 	//--------------------------------------------------------
 	/*
 	 * Method:       insert()
 	 * Parameters:   RedBlackNode<T>
 	 * Visibility:   Private
 	 * Description:  places node z in correct position in tree and updates the numLeft and numRight values as it goes
 	 * Calls:        insertionFixup()
 	 * 
 	  Variables:
 	  		z = new node to be placed in tree
 	  		x = root node, used as reference for z
 	  		y = initialized to nil, used as a reference for z
 	  
 	  Logic:
 	  While loop checks if z is in correct position, if not, z is moved accordingly
 	  		* if z.key is < the current key, go left, and update x.numLeft as z is < x
 	 		* else z.key >= x.key so go right, and update x.numGreater as z is >= x
 	
 	 Once the node is in the correct place, the rest of the method prepares the new node to be handled by the insertFixup method
 	 		* Depending on the value of y.key, put z as the left or right child of y
 	 		* Initialize z's children to nil and z's color to red
 	 		* Call insertFixup(z)
 	 		
 	 * @param node to be inserted
 	 */
 	public void insert(RedBlackNode<T> z) {
 		RedBlackNode<T> y = nil;
 		RedBlackNode<T> x = root;
 		size += 1;

 		while (!isNil(x)){
 			y = x;
 			if (z.getKey().compareTo(x.getKey()) < 0){
 				x.setNumLeft(x.getNumLeft() + 1);
 				x = x.getLeft();
 			}
 			
 			else{
 				x.setNumRight(x.getNumRight() + 1);
 				x = x.getRight();
 			}
 		}
 		
 		z.setParent(y);

 		if (isNil(y))
 			root = z;
 		else if (z.getKey().compareTo(y.getKey()) < 0)
 			y.setLeft(z);
 		else
 			y.setRight(z);

 		z.setLeft(nil);
 		z.setRight(nil);
 		z.setColor(RedBlackNode.RED);

 		insertFixup(z);
 	}

	/*
	 * Method:       insertionFixup()
	 * Parameters:   RedBlackNode<T>
	 * Visibility:   private
	 * Description:  looks at the node which was just inserted, and checks to see if any of the RedBlackTree`s properties have been violated.
	                 If there is an infractions, this method will reorganize and re-color the tree so it remains balanced after every insert
	 * Calls:        leftRotate(), rightRotate()
	 
	  Variables:
	  		z = newly inserted node
	  		y is initialized to nil and used as a reference for z
	  
	  Logic:
	  While loop will run as long as the RedBlackTree is unbalanced, starting at newly inserted node and working up to the root of the tree if needed
	 		
	  	 If z's parent is the the left child of it's parent Initialize y to z 's cousin
 			*Left Case 1: if y is red,.re-color
 		 	* Left Case 2: if y is black & z is a right child, leftRotaet around z's parent
 		 	* Left Case 3: else y is black & z is a left child, re-color and rotate round z's grandpa
 		
 		 If z's parent is the right child of it's parent, initialize y to z's cousin
 			*  Right Case 1: if y is red, re-color
 			*  Right Case 2: if y is black and z is a left child, rightRotate around z's parent
 		 	*  Right Case 3: if y  is black and z is a right child, re-color and rotate around z's grandpa
 	
 		 z is set to the next node up the tree that may now be in question
 		
 	While loop ends when node z meets all RedBlackTree requirements		 	
 	 Ensure root`s color is set to black at all times 
	 */	
	private void insertFixup(RedBlackNode<T> z){		
		RedBlackNode<T> y = nil;
		
 		while (z.getParent().getColor() == RedBlackNode.RED){

 			if (z.getParent() == z.getParent().getParent().getLeft()){
 				y = z.getParent().getParent().getRight();
 				
 		// Left Case 1:
 				if (y.getColor() == RedBlackNode.RED){
 					z.getParent().setColor(RedBlackNode.BLACK);
 					y.setColor(RedBlackNode.BLACK);
 					z.getParent().getParent().setColor(RedBlackNode.RED);
 					z = z.getParent().getParent();
 				}
 				
 		// Left Case 2:
 				else if (z == z.getParent().getRight()){
 					z = z.getParent();
 					leftRotate(z);
 				}
 				
 		// Left Case 3:
 				else{
 					z.getParent().setColor(RedBlackNode.BLACK);
 					z.getParent().getParent().setColor(RedBlackNode.RED);
 					rightRotate(z.getParent().getParent());
 				}
 			}

 			else{
 				y = z.getParent().getParent().getLeft();
 				
 		// Right Case 1
 				if (y.getColor() == RedBlackNode.RED){
 					z.getParent().setColor(RedBlackNode.BLACK);
 					y.setColor(RedBlackNode.BLACK);
 					z.getParent().getParent().setColor(RedBlackNode.RED);
 					z = z.getParent().getParent();
 				}
 				
 		// Right Case 2: 
 				else if (z == z.getParent().getLeft()){
 					z = z.getParent();
 					rightRotate(z);
 				}
 				
 		// Right Case 3:
 				else{
 					z.getParent().setColor(RedBlackNode.BLACK);
 					z.getParent().getParent().setColor(RedBlackNode.RED);
 					leftRotate(z.getParent().getParent());
 				}
 			}
 		}
 	root.setColor(RedBlackNode.BLACK);
 	}
 			
 	/*
 	 * Method:       leftRotate()
 	 * Parameters:   RedBlackNode<T>
 	 * Visibility:   private
 	 * Description:  Parameter is in violation of a RedBlackTree property and needs some switching above it to maintain properties. This method will
 	                 rotate nodes in the tree depending on the situation	
 	 * Calls:        leftRotateFix()
 	 * 
 	 * Logic
 	 * 	Call leftRotateFixup() which updates the numLeft and numRight values.
 	 * 
 	 *		 Rotate (Check for existence if needed
 	 *			 * set y = x`s right child  
 	 * 			 * set x`s right child = to y`s left child
 	 * 			 * set x = y left child's parent
 	 * 			 * set y`s parent = x parent
 	 * 			 * if x`s parent is nil, y = root
 	 *			 * set x`s parent`s left/right = y
 	 * 			 * set y`s left child = x
 	 *			 * set x`s parent = y
 	 *
 	 *@param x the node on which the leftRotate will be performed on
 	 */	
	private void leftRotate(RedBlackNode<T> x){
		
		leftRotateFix(x);
		RedBlackNode<T> y = x.getRight();
		x.setRight(y.getLeft());

		if (!isNil(y.getLeft()))
			x = y.getLeft().getParent();
		
		y.setParent(x.getParent());

		if (isNil(x.getParent()))
			root = y;
		else if (x.getParent().getLeft() == x)
			x.getParent().setLeft(y);
		else
			x.getParent().setRight(y);
		
		y.setLeft(x);
		x.setParent(y);
	}

	/*
	 * Method:       leftRotateFixup()
	 * Parameters:   RedBlackNode<T>
	 * Visibility:   private
	 * Description:  Checks the 4 cases which can occur where adding a node breaks the properties of the RedBlackTree. This method will set the 
	                 left and right number variables accordingly so the rotate function can classify the situation and restore the RedBlackTree properties
	 * Calls:        No other methods from RedBlackTree Class
	  
	  Variables:
	  		x = node the rotate will occur on
	  		
	  Logic:
	  This method will check the following cases, if a case qualifies the method will execute all * expressions
	 		Case 1: Only x, x.right and x.right.right are not nil.
	  			* set x`s numLeft value = 0
	  			* set x`s numRight value = 0
	  			* x`s right child numLeft = 1
	  			 
	  		Case 2: x.right.left also exists in addition to Case 1
	  			* set x`s numLeft value = 0
	  			* set x numRight value = 1 + x's right child`s left child`s numLeft + numRight
	  			* set x`s right child`s leftNum value = 2 + x's right child`s left child`s numLeft + numRight
	  			
	  		Case 3: x.left also exists in addition to Case 1
	  			* set x`s numRight value = 0
	  			* x`s right child numLeft = 2 + x`s left child`s numLeft + numRight
	  			
	   		Case 4: x.left and x.right.left both exist in addition to Case 1
	   			* set x`s numRight value = 1 + x's right child`s left child`s numLeft + numRight
	  			* x`s right child numLeft = 3 + x`s left child`s numLeft + numRight + x's right child`s left child`s numLeft + numRight
	 */
	
	private void leftRotateFix(RedBlackNode<T> x){		
	// Case 1:
		if (isNil(x.getLeft()) && isNil(x.getRight().getLeft())){
			x.setNumLeft(0);
			x.setNumRight(0);
			x.getRight().setNumLeft(1);
		}
	// Case 2: 
		else if (isNil(x.getLeft()) && !isNil(x.getRight().getLeft())){
			x.setNumLeft(0);
			x.setNumRight(1 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight());
			x.getRight().setNumLeft(2 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight());
		}
	// Case 3: 
		else if (!isNil(x.getLeft()) && isNil(x.getRight().getLeft())){
			x.setNumRight(0);
			x.getRight().setNumLeft(2 + x.getLeft().getNumLeft() + x.getLeft().getNumRight());
		}
	// Case 4: 
		else{
			x.setNumRight(1 + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight());
			x.getRight().setNumLeft(3 + x.getLeft().getNumLeft() + x.getLeft().getNumRight() + x.getRight().getLeft().getNumLeft() + x.getRight().getLeft().getNumRight());
		}
	}

	/**
	 * The right rotate method follows similar logic to the left rotate method, so I will 
	 * sum the algorithm up in English and say this method moves the node's left child to 
	 * be the node's parent, and the node becomes the right child of its former left child.
	 *  Updates the numLeft and numRight values affected by the Rotate.
	 * 
	 * @param y node which the rightRotate is to be performed on.
	 */
	private void rightRotate(RedBlackNode<T> y){
		
		rightRotateFix(y);
        RedBlackNode<T> x = y.getLeft();
        y.setLeft(x.getRight());

        if (!isNil(x.getRight()))
            x.getRight().setParent(y);
        x.setParent(y.getParent());

        // y.parent is nil
        if (isNil(y.getParent()))
            root = x;

        // y is a right child of it's parent.
        else if (y.getParent().getRight() == y)
            y.getParent().setRight(x);

        // y is a left child of it's parent.
        else
            y.getParent().setLeft(x);
        x.setRight(y);
        y.setParent(x);
	}


	/*
	 * Method:       rightRotateFixup()
	 * Parameters:   RedBlackNode<T>
	 * Visibility:   private
	 * Description:  Checks the 4 cases which can occur where adding a node breaks the properties of the RedBlackTree. This method will set the 
	                 left and right number variables accordingly so the rotate function can classify the situation and restore the RedBlackTree properties
	 * Calls:        No other methods from RedBlackTree Class
	  
	  Variables:
	  		x = node the rotate will occur on
	  		
	  Logic:
	  Reversed from the leftRotateFixup method
	  This method will check the following cases, if a case qualifies the method will execute all * expressions
	 		Case 1: Only y, y.right and y.right.right are not nil.
	  			* set y`s numRight value = 0
	  			* set y`s numLeft value = 0
	  			* y`s left child numRight = 1
	  			 
	  		Case 2: y.left.right also exists in addition to Case 1
	  			* set y`s numRight value = 0
	  			* set y numLeft value = 1 + y's left child`s right child`s numLeft + numRight
	  			* set y`s left child`s numRight value = 2 + y's left child`s right child`s numLeft + numRight
	  			
	  		Case 3: y.right also exists in addition to Case 1
	  			* set y`s numLeft value = 0
	  			* y`s left child numRight = 2 + y`s right child`s numRight + numLeft
	  			
	   		Case 4: y.left and y.left.right both exist in addition to Case 1
	   			* set y`s numLeft value = 1 + y's left child`s right child`s numLeft + numRight
	  			* y`s left child numRight = 3 + y`s right child`s numLeft + numRight + y's left child`s right child`s numLeft + numRight
	 */
	private void rightRotateFix(RedBlackNode<T> y){	
		
	// Case 1: 
		if (isNil(y.getRight()) && isNil(y.getLeft().getRight())){
			y.setNumRight(0);
			y.setNumLeft(0);
			y.getLeft().setNumRight(1);
		}

	// Case 2: 
		else if (isNil(y.getRight()) && !isNil(y.getLeft().getRight())){
			y.setNumRight(0);
			y.setNumLeft(1 + y.getLeft().getRight().getNumRight() + y.getLeft().getRight().getNumLeft());
			y.getLeft().setNumRight(2 + y.getLeft().getRight().getNumRight() + y.getLeft().getRight().getNumLeft());
		}

	// Case 3: 
		else if (!isNil(y.getRight()) && isNil(y.getLeft().getRight())){
			y.setNumLeft(0);
			y.getLeft().setNumRight(2 + y.getRight().getNumRight() +y.getRight().getNumLeft());
		}

	// Case 4: 
		else{
			y.setNumLeft(1 + y.getLeft().getRight().getNumRight() + y.getLeft().getRight().getNumLeft());
			y.getLeft().setNumRight(3 + y.getRight().getNumRight() + y.getRight().getNumLeft() + y.getLeft().getRight().getNumRight() + y.getLeft().getRight().getNumLeft());
		}
	}
	
	/**
	 * This fixNodeData method updates the node counts for each node after a deletion operation 
	 * has occurred. First, initialize two variables, current and track, both of which are set 
	 * to a special node called nil. This node represents a null leaf node in the tree.
	 * 
	 * Then check whether x is the null node or not. If it is, it sets current to the parent of y 
	 * and track to y. Otherwise, it sets current to the parent of x and track to x.
	 * 
	 * Then enters a while loop that traverses up the tree from the node current to the root of 
	 * the tree. Within the loop, the method checks whether the node y has the same key as the 
	 * current node current. If they have different keys, the method decrements the number of 
	 * nodes to the left or right of the current node, depending on the relationship between their 
	 * keys. If they have the same key, the method checks whether the current node has any null 
	 * children and updates the node count appropriately. If the current node has two children, the 
	 * method determines whether track is the left or right child of current and decrements the node 
	 * count for the appropriate subtree.
	 * 
	 * Finally, the method updates track and current to their respective parent nodes, and the loop 
	 * continues until the root of the tree is reached.
	 * 
	 * @param x the RedBlackNode which was actually deleted from the tree
	 * @param y key, the value of the key that used to be in y
	 */
	private void fixNodeData(RedBlackNode<T> x, RedBlackNode<T> y){
		
		RedBlackNode<T> current = nil;
		RedBlackNode<T> track = nil;

		if (isNil(x)){
			current = y.getParent();
			track = y;
		}

		else{
			current = x.getParent();
			track = x;
		}

		while (!isNil(current)){
			
			if (y.getKey() != current.getKey()) {

				if (y.getKey().compareTo(current.getKey()) > 0)
					current.setNumRight(current.getNumRight() - 1);

				if (y.getKey().compareTo(current.getKey()) < 0)
					current.setNumLeft(current.getNumLeft() - 1);
			}

			else{
				
				if (isNil(current.getLeft()))
					current.setNumLeft(current.getNumLeft() - 1);
				
				else if (isNil(current.getRight()))
					current.setNumRight(current.getNumRight() - 1);

				else if (track == current.getRight())
					current.setNumRight(current.getNumRight() - 1);
				
				else if (track == current.getLeft())
					current.setNumLeft(current.getNumLeft() - 1);
			}

			track = current;
			current = current.getParent();
		}
	}
	
	/**
	 * Traverses the subtree rooted at the given node in-order and adds all keys greater than the specified key to the given list.
	 * @param node the root of the subtree to traverse
	 * @param key the key to compare against
	 * @param list the list to add matching keys to
	*/
    private void getGreaterThan(RedBlackNode<T> node, T key, List<T> list) {
        if (isNil(node)) {
            return;
            
        } else if (node.getKey().compareTo(key) > 0) {
            getGreaterThan(node.getLeft(), key, list);
            list.add(node.getKey());
            getGreaterThan(node.getRight(), key, list);
            
        } else {
            getGreaterThan(node.getRight(), key, list);
        }
    }
    
	/**
	 * @param node the RedBlackNode we must check to see whether its nil
	 * @return true if node is nil and false otherwise
	 */
	private boolean isNil(RedBlackNode<T> node){
		return node == nil;
	}
}
