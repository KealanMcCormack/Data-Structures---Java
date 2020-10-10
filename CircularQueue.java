//Implements the Queue ADT through the queue interface
//Each method of the interface is overwritten
//Is based on a circularly linked list

public class CircularQueue<E> implements Queue<E>{

	private CircularlyLinkedList<E> list = new CircularlyLinkedList<E>(); //data store
	
	public static void main(String[] args) {
		CircularQueue<Integer> dll = new CircularQueue<Integer>();
		dll.enqueue(1);
		dll.enqueue(2);
		dll.enqueue(3);
		dll.enqueue(4);
		dll.enqueue(5);
		System.out.println(dll.toString());
		System.out.println(dll.size());
		dll.dequeue();
		System.out.println(dll.first());
	}
	
	public String toString() {
		return list.toString();
	}
	
	public CircularQueue() { //Constructor
		
	}
	
	@Override
	public int size() { //Returns size
		return list.size();
	}

	@Override
	public boolean isEmpty() { //Checks if list is empty
		return list.isEmpty();
	}

	@Override
	public void enqueue(E e) { //Adds new value to end of Queue
		list.addLast(e);
	}

	@Override
	public E first() {  //Returns value at front of the Queue
		return list.get(0);
	}

	@Override
	public E dequeue() { //Removes value at front of the Queue
		return list.removeFirst();
	}

}