import java.util.Iterator;


public class CircularLinkedList<E> implements List<E>{
	private Node<E> tail = null;
	private int size;
	
	private static class Node<E> {
		private E element;
		private Node<E> next;
		
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}
		
		public E getElement() {
			return element; 
		}
		
		public Node<E> getNext(){
			return next;
		}
		
		public void setNext(Node<E> n) {
			next = n;
		}
	}
	
	public CircularLinkedList(){
		
	}
	
	@Override
	public Iterator<E> iterator() {
		return new CircularLinkedListIterator();
	}
	
private class CircularLinkedListIterator implements Iterator<E> {	
	Node curr;
	public CircularLinkedListIterator() {
		curr = tail;
	}

	@Override
	public boolean hasNext() {
		return curr.getNext() != tail;
	}

	@Override
	public E next() {
		E res = (E) curr.getElement();
		curr = curr.getNext();
		return res;
	}
}	


@Override
public boolean isEmpty() {
	if(tail != null) {
		return true;
	}
return false;
}

@Override
public E get(int i) {
	i--;
	if(tail == null) {
		throw new RuntimeException("No such index");
	}
	
	Node cur = tail;
	Node prev = null;
	
	
	while(cur != null && i > 0) {
		i--;
		prev = cur;
		cur = cur.next;
	}
	
	if(cur == null) {
		throw new RuntimeException("No such index");
	}
	
	return (E) cur.getElement();
}

@Override
public void add(int i, E e) {
	i--;
	
	Node cur = tail;
	Node prev = null;

	while(cur != null && i > 0) {
		i--;
		prev = cur;
		cur = cur.next;
	}
	
	Node n = new Node<E>(e, cur);
	prev.next = n;
	
}

@Override
public E remove(int i) {
	
	if(tail == null) {
		throw new RuntimeException("cannot delete");
	}
	
	Node cur = tail;
	Node prev = null;
	
	if(i == 0) {
		removeFirst();
		return null;
	}
	while(cur != null && i > 0) {
		i--;
		prev = cur;
		cur = cur.next;
	}
	
	if(cur == null) {
		throw new RuntimeException("cannot delete");
	}
	
	prev.next = cur.next;
	
	return null;
}

public void remove(String key) {
	if(tail == null) {
		throw new RuntimeException("cannot delete");
	}
	
	if(tail.getElement().equals(key)) {
		tail = tail.next;
		return;
	}
	
	Node cur = tail;
	Node prev = null;
	
	while(cur != null && !cur.getElement().equals(key)) {
		prev = cur;
		cur = cur.next;
	}
	
	if(cur == null) {
		throw new RuntimeException("cannot delete");
	}
	
	prev.next = cur.next;
}


@Override
public int size() {
	return size;
}	


@Override
public E removeFirst() {
	if(tail == null) {
		throw new RuntimeException("cannot delete");
	}
	Node cur = tail;

	if(cur.next == tail) {
		tail = null;
		return null;
	}
	
	while(cur.next != tail) {
		cur = cur.next;
	}
	cur.next = tail.next;
	tail = tail.next;
	return null;
}

@Override
public E removeLast() {
	if(tail == null) {
		throw new RuntimeException("cannot delete");
	}
	Node cur = tail;
	Node prev = null;
	
	
	while(cur.next != tail) {
		prev = cur;
		cur = cur.next;
	}
	
	prev.next = tail;//Will struggle for lists less than 3 nodes
	
	return null;
}

@Override
public void addFirst(E e) {
	tail = new Node<E>(e, tail);
	size++;
	
}

@Override
public void addLast(E e) {
	Node<E> newest = new Node<E>(e, tail);
	Node<E> last = tail;
	if(last == null) {
		tail = newest;
		tail.setNext(tail);
	}else {
		while(last.getNext() != tail) {
			last = last.getNext();
		}
		last.setNext(newest);
	}
	size++;
	
}

@Override
public E set(int i, E e) {
	return null;
}

@Override
public String toString() {
	
	Node<E> cur = tail;
	String a = "";
	while(cur.getNext() != tail) {
		a = a + cur.getElement() + ",";
		cur = cur.next;
	}
	a = a + cur.getElement() + ",";
	return a;
}

public void rotate() {
	tail = tail.getNext();
}

public static void main(String[] args) {
	CircularLinkedList<Integer> ll = new CircularLinkedList<Integer>();
	for(int i = 10; i < 20; ++i) {
		ll.addLast(i);
	}
	System.out.println(ll.toString());
	ll.removeFirst();
	System.out.println(ll); 
	ll.removeLast();
	
	ll.rotate();
	System.out.println(ll);

	ll.removeFirst();
	ll.rotate();
	System.out.println(ll);

	ll.removeLast();
	ll.rotate();
	System.out.println(ll);

	for (Integer e : ll) {
		System.out.println("value: " + e);
	}
 }
}
