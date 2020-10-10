import static org.junit.jupiter.api.Assertions.assertEquals;

//implements the stack ADT through the stack interface
//each method is overwritten
//Based on an array held as a variable in the class

public class ArrayStack<E> implements Stack<E>{
	
	public static final int CAPACITY = 1000;
	private E[] data; //data storage
	private int t = -1;
	
	public ArrayStack() {//Constructor
		this(CAPACITY);
	}
	
	public String toString() {
		String a = "";
		for(int i = 0;i <= t;i++) {
			a += data[i] + " ";
		}
		
		return a;
	}
	
	@SuppressWarnings({"unchecked"})
	public ArrayStack(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	@Override
	public int size() { //Returns current size of stack
		return (t + 1);
	}

	@Override
	public boolean isEmpty() { //Checks if stack is Empty
		return t == -1;
	}

	@Override
	public void push(E e) { //Adds a new value to the stack
		if((t + 1) == CAPACITY) {
			throw new StackOverflowError("Error full");
		}
		t++;
		data[t] = e;
	}

	@Override
	public E pop() { // Removes the top value from the stack
		if(isEmpty()) {
			return null;
		}
		
		E temp = data[t];
		data[t] = null;
		t--;
		return temp;
	}

	@Override
	public E top() { // Returns the value at the top of the stack
		
		if(isEmpty()) {
			return null;
		}
		
		return data[t];
	}
	
	public static void main(String[] args) {
		ArrayStack<Integer> s = new ArrayStack<>();
		for(int i = 0; i < 10; ++i)
			s.push(i);
		//assertEquals(10, s.size());
		//assertEquals("0 1 2 3 4 5 6 7 8 9 ", 
				System.out.println(s.toString());
		
	}
	
}