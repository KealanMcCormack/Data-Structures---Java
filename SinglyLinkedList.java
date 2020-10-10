import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;


//Implements the List ADT
//Implements a Singly Linked List with methods from the list interface
//Overwrites the methods and includes a Node class as a sub class
public class SinglyLinkedList<E> implements List<E> {

	private int size = 0;
	private Node<E> head;
	
	private class Node<E> { //Sets up Node class
		private E element;
		
		private Node<E> next;
		
		public Node(E e, Node<E> n) { //Node constructor
			element = e;
			next = n;
		}
		
		public E getElement() {return element;} 
		
		public void setNext(Node<E> n) {next = n;}
		
		public Node<E> getNext(){return next;}
	}
	
	public E first() {
		if(head == null) {
			return null;
		}
		
		return head.getElement();
	}
	
	public E last() {
		if(head == null) {
			return null;
		}
		
		Node<E> cur = head;
		while(cur.getNext() != null) {
			cur = cur.getNext();
		}
		
		return cur.getElement();
	}
	
	@Override
	public boolean isEmpty() { // Checks if the LinkedList is empty 
		if(size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public E get(int i) { // Finds the Node at index i and returns 
		if(head == null) {
			throw new RuntimeException("No such index");
		}
		
		Node<E> cur = head;
		
		while(cur != null && i > 0) {
			i--;
			cur = cur.next;
		}
		
		if(cur == null) {
			throw new RuntimeException("No such index");
		}
		
		return cur.getElement();
	}

	@Override
	public void add(int i, E e) { // Adds a node with the given value at a specific index
		Node<E> cur = head;
		Node<E> prev = null;
		
		while(cur != null && i > 0) {
			i--;
			prev = cur;
			cur = cur.next;
		}
		
		if(cur == null) {
			throw new RuntimeException("No such index");
		}
		
		Node<E> n = new Node<E>(e, cur);
		prev.next = n;

	}

	@Override
	public E remove(int i) { //Removes node at specific index
		if(head == null) {
			throw new RuntimeException("No such index");
		}
		
		Node<E> cur = head;
		Node<E> prev = null;
		
		if(i == 0) {
			E temp = head.getElement();
			removeFirst();
			return temp;
		}
		
		while(cur != null && i > 0) {
			i--;
			prev = cur;
			cur = cur.getNext();
		}
		
		if(cur == null) {
			throw new RuntimeException("Cannot delete, index not found");
		}
		
		E temp = cur.getElement();
		prev.next = cur.next;
		size--;
		return temp;
	}

	@Override
	public Iterator<E> iterator() {
		return new SinglyLinkedListIterator();
	}
	
	private class SinglyLinkedListIterator implements Iterator<E>{
		Node<E> cur = null;
		
		public SinglyLinkedListIterator() {
			cur = head; 
		}
		
		@Override
		public boolean hasNext() {
			return cur != null;
		}
		
		@Override
		public E next() {
			E res = cur.getElement();
			cur = cur.getNext();
			return res;
		}
		
	}

	@Override
	public int size() { //returns size of the Linked List
		return size;
	}	
	

	@Override
	public E removeFirst() { // Removes first node of the Linked List
		if(head == null) {
			throw new RuntimeException("Cannot delete as the list is empty");
		}
		
		Node<E> cur = head;
		
		if(cur.next == null) {
			E temp = head.getElement();
			head = null;
			size--;
			return temp;
		}
		
		E temp = head.getElement();
		head = cur.next;
		size--;
		return temp;
				
	}

	@Override
	public E removeLast() { //Removes last Node in the Linked List
		if(head == null) {
			throw new RuntimeException("Cannot delete as the list is empty");
		}
		
		Node<E> cur = head;
		Node<E> prev = head;
		Node<E> preprev = null;
		
		while(cur != null) {
			preprev = prev;
			prev = cur;
			cur = cur.next;
		}
		
		E temp = prev.getElement();
		preprev.next = null;
		size--;
		return temp;
	}

	@Override
	public void addFirst(E e) { // Adds a Node at the start of the Linked List
		head = new Node<E>(e, head);
		size++;
	}

	@Override
	public void addLast(E e) { // Adds a Node at the end of the Linked List
		Node<E> newest = new Node<E>(e, null);
		Node<E> last = head;
		if(last == null) {
			head = newest;
		}
		else {
			while(last.getNext() != null) {
				last = last.getNext();
			}
			last.setNext(newest);
		}
		size++;
	}
	
	@Override
	public String toString() { //adds elements of the Linked List to a String
		
       Node<E> cur = head;
		
		String a = "[";
		
		while(cur != null) {
			if(cur.getElement() != null) {
				a = a  + cur.getElement();
				if(cur.getNext() != null) {
					a += ", ";
				}
			}
			cur = cur.next;
		}
		
		a += "]";
		
		return a;
		
	}
	
	public static void main(String[] args) {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
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