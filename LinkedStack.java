import static org.junit.jupiter.api.Assertions.assertEquals;

//implements the Stack ADT through the stack interface
//The methods are overwritten within the class
//Based on a singly linked list

//In this class I treat the top of the list as being the end as it allows nodes to be added easily
public class LinkedStack<E> implements Stack<E> {

	private SinglyLinkedList<E> list = new SinglyLinkedList<>(); //Declares list
	
	public LinkedStack() {
		
	}
	
	public static void main(String[] args) {
		LinkedStack<Integer> s = new LinkedStack<>();
		for(int i = 0; i < 10; ++i)
			s.push(i);
		//assertEquals("9 8 7 6 5 4 3 2 1 0 ",
		 System.out.println(s.toString());

	}
	
	public String toString() {
		return list.toString();
	}

	@Override
	public int size() { //returns size of list
		return list.size();
	}

	@Override
	public boolean isEmpty() { // Checks if list is empty
		return list.isEmpty();
	}

	@Override
	public void push(E e) { //Adds new Node to the end of the stack
		list.addLast(e);
	}

	@Override
	public E top() { //Returns value at the top of the stack
		return list.get(list.size() - 1);
	}

	@Override
	public E pop() { //Removes Node at end of the stack
		return list.removeLast();
	}

}