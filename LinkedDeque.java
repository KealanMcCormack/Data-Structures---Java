//Implements a version of the Deque ADT by implementing the Deque interface
//The methods in the interface are overridden in the class
//The class is based on a Doubly Linked List
public class LinkedDeque<E> implements Deque<E>{

	private DoublyLinkedList<E> list = new DoublyLinkedList<E>();
	
	public String toString() {
		return list.toString();
	}
	
	public static void main(String[] args) {
		LinkedDeque<Integer> s = new LinkedDeque<>();
		for(int i = 0; i < 10; ++i)
			s.addLast(i);
		for(int i = 0; i < 6; ++i)
			s.removeLast();
		
		System.out.println(s.toString());
	}
	
	@Override
	public int size() { //returns size of list
		return list.size();
	}

	@Override
	public boolean isEmpty() { //Checks if list is empty 
		return list.isEmpty();
	}

	@Override
	public E first() { //Returns first element in list
		return list.get(0);
	}

	@Override
	public E last() { //returns last element in list
		return list.get(size() - 1);
	}

	@Override
	public void addFirst(E e) { //Adds new element in first position
		list.addFirst(e);
	}

	@Override
	public void addLast(E e) { //Adds new element in last position
		list.addLast(e);
	}

	@Override
	public E removeFirst() { //Removes first element
		return list.removeFirst();
	}

	@Override
	public E removeLast() { //Removes last element
		return list.removeLast();
	}

}