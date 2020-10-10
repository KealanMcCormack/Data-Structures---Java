
public class CollatzAlg {
	
	public static void main(String[] args) {
		System.out.println(Collatz(871));
	}
		
	public static int Collatz(int n) {
		if(n < 1) {
			return 0;
		}
		
		if(n == 1) {
			return 1;
		}
		
		if(n%2 == 0) {
			return Collatz(n/2);
		}else {
			return Collatz((n/3) + 1);
		}
		
		
	}
	
	
}
