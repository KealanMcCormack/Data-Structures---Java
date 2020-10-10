//Implements the AbstractBinaryTree interface which itself implements the AbstractTree and Binary
//Tree interfaces which both implement the tree interface
//Abstract Binary tree implements methods for tree traversal - unused in this class
//Abstract tree implements helper functions
//Binary tree implements position helper functions - unused
//tree provides templates for functions
//Class contains a nested node class

import java.util.ArrayList;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E>{
  /** Nested static class for a binary tree node. */
  static class Node<E> implements Position<E> {
	  private E element;
	  private Node<E> parent;
	  private Node<E> left;
	  private Node<E> right;
	
	  
	public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
		element = e;
		parent = above;
		left = leftChild;
		right = rightChild;
	}
	
	public E getElement() {return element;} //accessors
	public Node<E> getParent() {return parent;}
	public Node<E> getLeft() {return left;}
	public Node<E> getRight() {return right;}
	
	public void setElement(E e) {element = e;} //mutators
	public void setParent(Node<E> parentNode) {parent = parentNode;}
	public void setLeft(Node<E> leftChild) {left = leftChild;}
	public void setRight(Node<E> rightChild) {right = rightChild;}
	
	public String toString() {
		return new StringBuilder("(").append(element).append(")").toString();
	}
	
}
  
  public Position<E> addRoot(E e) throws IllegalStateException{ //add new root
	  if(!isEmpty()) throw new IllegalStateException("Tree is not empty");
      root = createNode(e, null, null, null);
      size = 1;
      return root;
  
  }
   

  /** Factory function to create a new node storing element e. */
  protected Node<E> createNode(E e, Node<E> parent,
                                  Node<E> left, Node<E> right) {
	  
    return new Node<E>(e, parent, left, right);
   }
  

  // LinkedBinaryTree instance variables
  /** The root of the binary tree */
  protected Node<E> root = null;     // root of the tree

  /** The number of nodes in the binary tree */
  private int size = 0;              // number of nodes in the tree

  // constructor
  /** Constructs an empty binary tree. */
  public LinkedBinaryTree() { }      // constructs an empty binary tree

  // nonpublic utility
  /**
   * Verifies that a Position belongs to the appropriate class, and is
   * not one that has been previously removed. Note that our current
   * implementation does not actually verify that the position belongs
   * to this particular list instance.
   *
   * @param p   a Position (that should belong to this tree)
   * @return    the underlying Node instance for the position
   * @throws IllegalArgumentException if an invalid position is detected
   */
  protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node))
      throw new IllegalArgumentException("Not valid position type");
    Node<E> node = (Node<E>) p;       // safe cast
    if (node.getParent() == node)     // our convention for defunct node
      throw new IllegalArgumentException("p is no longer in the tree");
    return node;
  }

  // accessor methods (not already implemented in AbstractBinaryTree)
  /**
   * Returns the number of nodes in the tree.
   * @return number of nodes in the tree
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns the root Position of the tree (or null if tree is empty).
   * @return root Position of the tree (or null if tree is empty)
   */
  @Override
  public Position<E> root() {
    return root;
  }

  /**
   * Returns the Position of p's parent (or null if p is root).
   *
   * @param p    A valid Position within the tree
   * @return Position of p's parent (or null if p is root)
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  @Override
  public Position<E> parent(Position<E> p) throws IllegalArgumentException {
	Node<E> node = validate(p);
	return node.getParent();
  }

  /**
   * Returns the Position of p's left child (or null if no child exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the left child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */
  @Override
  public Position<E> left(Position<E> p) throws IllegalArgumentException {
	Node<E> node = validate(p);
	return node.getLeft();
  }

  /**
   * Returns the Position of p's right child (or null if no child exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the right child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */
  @Override
  public Position<E> right(Position<E> p) throws IllegalArgumentException {
	  Node<E> node = validate(p);
		return node.getRight();
  }

  // update methods supported by this class
  /**
   * Places element e at the root of an empty tree and returns its new Position.
   *
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalStateException if the tree is not empty
   */
  

  public void insert(E e){
      //recursively add from root
      root = addRecursive(root, e);
      ++size;
  }
  
  //recursively add Nodes to binary tree in proper position
  private Node<E> addRecursive(Node<E> p, E e){
	  if(p == null) {
		  return createNode(e, null, null, null);
	  }
	if((int) e < (int) p.getElement()) {
		addLeftRecursive(p, e);
	}else {
		addRightRecursive(p, e); 
	}
	return p;
  }
  
  private void addLeftRecursive(Node<E> p, E e) {
	  addRecursive(p.getLeft(), e);
  }
  
  private void addRightRecursive(Node<E> p, E e) {
	  addRecursive(p.getRight(), e);
  }

  
  /**
   * Creates a new left child of Position p storing element e and returns its Position.
   *
   * @param p   the Position to the left of which the new element is inserted
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   * @throws IllegalArgumentException if p already has a left child
   */
  public Position<E> addLeft(Position<E> p, E e)
                          throws IllegalArgumentException {
	  Node<E> parent = validate(p);
	  if(parent.getLeft() != null) throw new IllegalArgumentException("p already has a left child");
	  Node<E> child = createNode(e, parent, null, null);
	  parent.setLeft(child);
	  size++;
	 return child;
  }

  /**
   * Creates a new right child of Position p storing element e and returns its Position.
   *
   * @param p   the Position to the right of which the new element is inserted
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   * @throws IllegalArgumentException if p already has a right child
   */
  public Position<E> addRight(Position<E> p, E e)
                          throws IllegalArgumentException {
	  Node<E> parent = validate(p);
	  if(parent.getRight() != null) throw new IllegalArgumentException("p already has a right child");
	  Node<E> child = createNode(e, parent, null, null);
	  parent.setRight(child);
	  size++;
	 return child;
  }

  /**
   * Replaces the element at Position p with element e and returns the replaced element.
   *
   * @param p   the relevant Position
   * @param e   the new element
   * @return the replaced element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  public E set(Position<E> p, E e) throws IllegalArgumentException {
	Node<E> node = validate(p);
	E temp = node.getElement();
	node.setElement(e);
	return temp;
  }

  /**
   * Attaches trees t1 and t2, respectively, as the left and right subtree of the
   * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
   *
   * @param p   a leaf of the tree
   * @param t1  an independent tree whose structure becomes the left child of p
   * @param t2  an independent tree whose structure becomes the right child of p
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   * @throws IllegalArgumentException if p is not a leaf
   */
  public void attach(Position<E> p, LinkedBinaryTree<E> t1,
                    LinkedBinaryTree<E> t2) throws IllegalArgumentException {
	Node<E> node = validate(p);
	if(isInternal(p)) throw new IllegalArgumentException("not a leaf");
	size += t1.size() + t2.size();
	if(!t1.isEmpty()) {
		t1.root.setParent(node);
		node.setLeft(t1.root);
		t1.root = null;
		t1.size = 0;
	}
	if(!t2.isEmpty()) {
		t2.root.setParent(node);
		node.setRight(t2.root);
		t2.root = null;
		t2.size = 0;
	}
	
  }

  /**
   * Removes the node at Position p and replaces it with its child, if any.
   *
   * @param p   the relevant Position
   * @return element that was removed
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   * @throws IllegalArgumentException if p has two children.
   */
  public E remove(Position<E> p) throws IllegalArgumentException {
	Node<E> node = validate(p);
	if(numChildren(p) == 2)
		throw new IllegalArgumentException("Cannot have two children");
	
	Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
	if(child != null) {
		child.setParent(node.getParent());
	}
	if(node == root) {
		root = child;
	}else {
		Node<E> parent = node.getParent(); //Moves child into position in tree
		if(node == parent.getLeft()) {
			parent.setLeft(child);
		}else {
			parent.setRight(child);
		}
	}
	size--;
	E temp = node.getElement();
	node.setElement(null); //Remove all value from the node
	node.setLeft(null);
	node.setRight(null);
	node.setParent(node);
	return temp;
	
  }
  
  public String toString() {
	  StringBuilder sb = new StringBuilder();
	  sb.append("[");
	  for(Position<E> p : positions()) {
		  sb.append(p.getElement());
		  sb.append(", ");
	  }
	  sb.append("]");
	  return sb.toString();
  }
  
  public void createLevelOrder(java.util.ArrayList<E> arr) {
	  root = createLevelOrderHelper(arr, root, 0);
  }
  
  private Node<E> createLevelOrderHelper(java.util.ArrayList<E> arr, Node<E> p, int i){
	  if(i < arr.size()) {
		  Node<E> n = createNode(arr.get(i), p, null, null);
		  n.left = createLevelOrderHelper(arr, n.left, 2 * i + 1);
		  n.right = createLevelOrderHelper(arr, n.right, 2 * i + 2);
		  ++size;
		  return n;
	  }
	  return p;
  }
  
  public void createLevelOrder(E[] arr) { //Order tree
	  root = createLevelOrderHelper(arr, root, 0);
  }
  
  private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i){
	  if(i < arr.length) {
		  Node<E> n = createNode(arr[i], p, null, null);
		  n.left = createLevelOrderHelper(arr, n.left, 2 * i + 1);
		  n.right = createLevelOrderHelper(arr, n.right, 2 * i + 2);
		  ++size;
		  return n;
	  }
	  return p;
  }
  
  
  @Override
  public Iterable<Position<E>> positions(){
	  return inorder();
  }
  
  public Iterable<Position<E>> inorder(){//implements inorder traversal of the tree
	  ArrayList<Position<E>> snapshot = new ArrayList<Position<E>>(); 
	  if(!isEmpty()) {
		  inorderSubtree(root(), snapshot);
	  }
	  return snapshot;
  }
  
  private void inorderSubtree(Position<E> p, ArrayList<Position<E>> snapshot) {//Moves through tree recursively
	  if(left(p) != null) {
		  inorderSubtree(left(p), snapshot);
	  }
	  snapshot.add(p);
	  if(right(p) != null) {
		  inorderSubtree(right(p), snapshot);
	  }
  }
  
  public static void main(String [] args) {
	  LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

		Integer [] arr = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
		bt.createLevelOrder(arr);
		System.out.println(bt.inorder().toString());
	  
	  
  }
  
  public static Node<Integer> Flatten(Node<Integer> root, Node<Integer> previous){
	  if(root == null) {
		  return previous;
	  }
	  
	  if(previous != null) {
		  previous.right = root;
	  }
	  
	  Node<Integer> right = root.right;
	  return Flatten(right, Flatten(root.left, root));
  }
} 