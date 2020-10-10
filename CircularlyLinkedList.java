import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;


//implements the List ADT
//Overwrites the methods and includes a Node class as a sub class
//Includes additionally functionality associated with circular lists i.e. rotate function
public class CircularlyLinkedList<E> implements List<E> {

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
	
	private Node<E> tail = null; //Variable set up
	
	private int size = 0;
	
	public E first() {
		if(tail == null) {
			return null;
		}
		
		return tail.getNext().getElement();
	}
	
	public E last() {
		if(tail == null) {
			return null;
		}
		
		return tail.getElement();
	}

	@Override
	public int size() { //Accessor for size
		return size;
	}

	@Override
	public boolean isEmpty() { //Checks if LinkedList is empty
		return size == 0;
	}

	@Override
	public E get(int i) { //Returns value at index
		if(tail == null) {
			throw new RuntimeException("No such index");
		}
		
		Node<E> cur = tail.getNext();
		
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
	public void add(int i, E e) { //Adds a vairable at the specific index
		Node<E> cur = tail.getNext();
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
	public E remove(int i) { //Removes Node at index
		if(tail == null) {
			throw new RuntimeException("No such index");
		}
		
		Node<E> cur = tail.getNext();
		Node<E> prev = null;
		
		if(i == 0) {
			E temp = cur.getElement();
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
	public E removeFirst() { //Removes first node
		if(tail == null) {
			throw new RuntimeException("Cannot delete as the list is empty");
		}
		
		Node<E> cur = tail.getNext();
		
		if(cur == tail) {
			E temp = cur.getElement();
			tail = null;
			size--;
			return temp;
		}
		
		E temp = cur.getElement();
		tail.next = cur.next;
		size--;
		return temp;
	}

	@Override
	public E removeLast() { //removes last Node
		if(tail == null) {
			throw new RuntimeException("Cannot delete as the list is empty");
		}
		
		Node<E> cur = tail.getNext();
		Node<E> prev = tail.getNext();
		
		while(cur != tail) {
			prev = cur;
			cur = cur.next;
		}
		
		E temp = tail.getElement();
		prev.next = tail.next;
		tail = prev;
		return temp;
	}

	@Override
	public Iterator<E> iterator() { // Allows iterator to function
		return new CircularlyLinkedListIterator();
	}
	
	private class CircularlyLinkedListIterator implements Iterator<E>{
		Node<E> cur = null;
		int j = 0;
		
		public CircularlyLinkedListIterator() {
			cur = tail.getNext(); 
		}
		
		@Override
		public boolean hasNext() {
			if(j == 0 && cur.getNext() != null) {
				j++;
				return true;
				
			}
			return cur != tail.getNext();
		}
		
		@Override
		public E next() {
			E res = cur.getElement();
			cur = cur.getNext();
			return res;
		}
		
	}
	

	@Override
	public void addFirst(E e) { //Adds new Node to start of list
		if(size == 0) {
			tail = new Node<E>(e, null);
			tail.setNext(tail);
		}else {
			Node<E> node = new Node<E>(e, tail.getNext());
			tail.setNext(node);
		}
		
		size++;
	}

	@Override
	public void addLast(E e) { //Adds new node to the end of the list
		addFirst(e);
		rotate();
	}

	public void rotate() { //Rotates the List
		if(tail != null) {
			tail = tail.getNext();
		}
	}
	
	@Override
	public String toString() { //adds elements of the Linked List to a String
		if(tail == null) {
			return null;
		}
		
        Node<E> cur = tail.getNext();
		
		String a = "[";
		
		while(cur != tail) {
			if(cur.getElement() != null) {
				a = a  + cur.getElement();
				if(cur.getNext() != tail) {
					a += ", ";
				}
			}
			cur = cur.next;
		}
		
		a += ", " + tail.getElement() + "]";
		
		return a;
		
	}
	
	
	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);
		
		ArrayList<Integer> buf = new ArrayList<>();
		for(Integer i : ll) {
			buf.add(i);
		}
		//assertEquals("[0, 1, 2, 3, 4]", 
		System.out.println(buf.toString());

	}

	@Override
	public E set(int i, E e) {
		// TODO Auto-generated method stub
		return null;
	}
}