//implements the Queue ADT through the queue interface
//The interface methods are overwritten
//Is based on a singly linked list
public class LinkedQueue<E> implements Queue<E>{

	private SinglyLinkedList<E> list = new SinglyLinkedList<>(); //data storage
	
	public static void main(String[] args) {
		LinkedQueue dll = new LinkedQueue();
		dll.enqueue(1);
		dll.enqueue(2);
		dll.enqueue(3);
		dll.enqueue(4);
		dll.enqueue(5);
		System.out.println(dll.first());
		System.out.println(dll.size());
		dll.dequeue();
		System.out.println(dll.first());
	}
	
	public LinkedQueue() {
		
	}
	
	public String toString() {
		return list.toString();
	}
	
	public int size() { //returns size
		return list.size();
	}

	@Override
	public boolean isEmpty() { //checks if empty
		return list.isEmpty();
	}

	@Override
	public void enqueue(E e) { //Adds element to queue
		list.addLast(e);
	}

	
	public E first() { //return first element in queue
		return list.get(0);
	}

	@Override
	public E dequeue() { //Removes element from queue
		return list.removeFirst();
	}
	
}
