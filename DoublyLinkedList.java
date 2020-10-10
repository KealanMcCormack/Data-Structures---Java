import java.util.Iterator;

//implements the List ADT
//Overwrites the methods and includes a Node class as a sub class

public class DoublyLinkedList<E> implements List<E> {
	
	Node<E> header;  //Intialise variables
	
	Node<E> trailer;
	
	private int size = 0;
	
	public DoublyLinkedList() { //Constructor
		header = new Node<E>(null, null, null);
		trailer = new Node<E>(null, header, null);
		header.setNext(trailer);
	}

	private class Node<E> { //Sets up Node class
		private E element;
		
		private Node<E> next;
		
		private Node<E> prev;
		
		public Node(E e, Node<E> p, Node<E> n) { //Node constructor
			element = e;
			prev = p;
			next = n;
		}
		
		public E getElement() {return element;} //Accessor and mutator methods for Node
		
		public void setNext(Node<E> n) {next = n;}
		
		public Node<E> getNext(){return next;}
		
		public void setPrev(Node<E> p) {prev = p;}
		
		public Node<E> getPrev() {return prev;}
	}
	
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) { //Adds a new node with a given value between two Nodes
		Node<E> newest = new Node<E>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}
	
	public E first() {
		return header.getNext().getElement();
	}
	
	public E last() {
		return trailer.getPrev().getElement();
	}
	
	@Override
	public int size() { //Returns size of LinkedList
		return size;
	}

	@Override
	public boolean isEmpty() { //Checks if LinkedList is empty
		return size == 0;
	}

	@Override
	public E get(int i) { //returns Node at index
		if(header == null) {
			throw new RuntimeException("No such index");
		}
		
		Node<E> cur = header.getNext();
		
		while(cur != null && i > 0) { //Moves through List
			i--;
			cur = cur.getNext();
		}
		
		if(cur == null) {
			throw new RuntimeException("No such index");
		}
		
		return cur.getElement();
	}

	@Override
	public void add(int i, E e) { //Adds a new Node at index
		if(header == null) {
			throw new RuntimeException("No such index");
		}
		
		Node<E> cur = header.getNext();
		
		while(cur != null && i > 0) {
			i--;
			cur = cur.getNext();
		}
		
		if(cur == null) {
			throw new RuntimeException("No such index");
		}
		
		addBetween(e, cur.getPrev(), cur);
		
	}

	@Override
	public E remove(int i) { //removes Node at index
		if(header == null) {
			throw new RuntimeException("No such index");
		}
		
		Node<E> cur = header.getNext();
		
		while(cur != null && i > 0) {
			i--;
			cur = cur.getNext();
		}
		
		if(cur == null) {
			throw new RuntimeException("No such index");
		}
		
		return remove(cur);
	}
	
	private E remove(Node<E> node) { //Removes specified Node
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		return node.getElement();
	}

	@Override
	public Iterator<E> iterator() { //Calls Iterator
		return new DoublyLinkedListIterator();
	}
	
	private class DoublyLinkedListIterator implements Iterator<E>{ 
		Node<E> curr;
		
		public DoublyLinkedListIterator() {
			curr = header.getNext();
		}
		
		@Override
		public boolean hasNext() {
			return curr.getElement() != null;
		}
		
		@Override
		public E next() {
			E res = curr.getElement();
			curr = curr.getNext();
			return res;
		}
	}

	@Override
	public E removeFirst() { //Removes first element
		if(isEmpty()) return null;
		return remove(header.getNext());
	}

	@Override
	public E removeLast() { //removes last element
		if(isEmpty()) return null;
		return remove(trailer.getPrev());
	}
	

	@Override
	public void addFirst(E e) { //adds new element in first position
		addBetween(e, header, header.getNext());
	}

	@Override
	public void addLast(E e) { //adds new element in last position
		addBetween(e, trailer.getPrev(), trailer);
	}
	
	@Override
	public String toString() { //adds elements of the Linked List to a String
		
		Node<E> cur = header.getNext();
		
		String a = "[";
		
		while(cur != null) {
			if(cur.getElement() != null) {
				a = a  + cur.getElement();
				if(cur.getNext() != trailer) {
					a += ", ";
				}
			}
			cur = cur.next;
		}
		
		a += "]";
		
		return a;
		
	}
	
	public static void main(String[] args) {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		ll.add(2, -1);
		System.out.println(ll.toString());
	}

	@Override
	public E set(int i, E e) {
		// TODO Auto-generated method stub
		return null;
	}

	
}