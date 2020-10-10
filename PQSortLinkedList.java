import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

public class PQSortLinkedList {
	
	public static Integer removeMin(LinkedList<Integer> a) {
		int minIndex = 0;
		Integer cur = a.get(0);
		for(int i = 0;i < a.size();i++) {
			if(a.get(i) < cur) {
				cur = a.get(i);
				minIndex = i;
			}
		}
		return a.remove(minIndex);
	}
	
	public static Integer removeMin(SinglyLinkedList<Integer> a) {
		int minIndex = 0;
		Integer cur = a.get(0);
		for(int i = 0;i < a.size();i++) {
			if(a.get(i) < cur) {
				cur = a.get(i);
				minIndex = i;
			}
		}
		a.remove(minIndex);
		return cur;
	}
	
	public static boolean Sorted(LinkedList<Integer> a) {
		for(int i = 1;i < a.size();i++) {
			if((a.get(i - 1) > a.get(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean Sorted(SinglyLinkedList<Integer> a) {
		for(int i = 1;i < a.size();i++) {
			if((a.get(i - 1) > a.get(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void randomFill(SinglyLinkedList<Integer> a, int n) {
		Random rnd = new Random();
		for(int i = 1;i < n;i++) {
			a.addLast(rnd.nextInt());
		}
	}
	
	public static void main(String[] args) {
		int n = 10;
		boolean JavaLinkedList = false;
		if(JavaLinkedList) {
	    
		LinkedList<Integer> arr = new Random().ints(0, 1000).distinct().limit(n).boxed().collect(Collectors.toCollection(LinkedList::new));
		
	    long startTime = System.nanoTime();
		LinkedList<Integer> PQ = new LinkedList<Integer>();
		
		while(!arr.isEmpty()) {
			PQ.addLast(arr.removeFirst());
		}
		
		
		while(!PQ.isEmpty()) {
			arr.addLast(removeMin(PQ));
		}
		
		long endTime = System.nanoTime();
		long finish = endTime - startTime ;
		
		System.out.println(n + " ," + finish + " , " + Sorted(arr));
		
		
		}else {
			while(n < 10000) {
			SinglyLinkedList<Integer> arr = new SinglyLinkedList<Integer>();
			
			randomFill(arr, n);
			
			
			
			long startTime = System.nanoTime();
			SinglyLinkedList<Integer> PQ = new SinglyLinkedList<Integer>();
			
			
			while(arr.isEmpty()) {
				PQ.addLast(arr.removeFirst());
				
			}
			
			
			
			while(PQ.isEmpty()) {
				arr.addLast(removeMin(PQ));
			}
			
			long endTime = System.nanoTime();
			long finish = endTime - startTime ;
			
			
			
			
			System.out.println(n + " ," + finish + " , " + Sorted(arr));
			n = (int) (n * 1.2);
			}
		}
		
		
	}
}
