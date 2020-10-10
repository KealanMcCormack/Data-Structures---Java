import static org.junit.jupiter.api.Assertions.assertEquals;

//Implements queue ADT through the queue interface
//Each function is overwritten in this class
//Class hold basic information to form a queue

public class ArrayQueue<E> implements Queue<E> {

	
	public static final int CAPACITY = 1000; //initialise variables
	private E[] data; //Queue storage
	private int size = 0;
	private int front = 0;
	
	public ArrayQueue() { //Sets capacity
		this(CAPACITY);
	}
	
	@SuppressWarnings({"unchecked"})
	public ArrayQueue(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	public static void main(String[] args) {
		ArrayQueue<Integer> s = new ArrayQueue<>();
		for(int i = 0; i < 10; ++i)
			s.enqueue(i);
		//assertEquals("0 1 2 3 4 5 6 7 8 9 ", 
				System.out.println(s.toString());

	}
	
	public String toString() {
		String a = "";
		for(int i = front;i < front + size;i++) {
			a += data[front + i] + " ";
		}
		
		return a;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void enqueue(E e) { //Adds to back of queue
		if(CAPACITY == size) {
			throw new IllegalArgumentException("Full");
		}
		int a = (front + size) % CAPACITY;
		data[a] = e;
		size++;
	}

	@Override
	public E first() { //returns first item in queue
		if(isEmpty()) {
			return null;
		}
		return data[front];
	}

	@Override
	public E dequeue() { //Removes from front of queue
		
		if(isEmpty()) {
			return null;
		}
		
		E temp = data[front];
		data[front] = null;
		front = (front + 1) % CAPACITY;
		size--;
		return temp;
		
	}

}