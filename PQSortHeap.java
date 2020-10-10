import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

public class PQSortHeap{
	
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
	
	public static boolean Sorted(LinkedList<Integer> a) {
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
		
	    while(n < 10000) {
		LinkedList<Integer> arr = new Random().ints(0, 1000).distinct().limit(n).boxed().collect(Collectors.toCollection(LinkedList::new));
		
	    long startTime = System.nanoTime();
		HeapPriorityQueue<Integer, Integer> PQ = new HeapPriorityQueue<Integer, Integer>();
		
		while(!arr.isEmpty()) {
			Integer val = arr.removeFirst();
			PQ.insert(val, val);
		}
		
		
		while(!PQ.isEmpty()) {
			arr.addLast(PQ.removeMin().getValue());
		}
		
		long endTime = System.nanoTime();
		long finish = endTime - startTime ;
		
		System.out.println(n + ", " + finish + ", " + Sorted(arr));
	    n = (int) (n * 1.2);
	    }
		
		
		
	}
}