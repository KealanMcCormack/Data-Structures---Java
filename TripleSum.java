public class TripleSum {
	
	public static void main(String[] args) {
		int[] array;
		array = new int[] {30, -30, -20, -10, 40, 0, 10, 15};
		System.out.println(Sum(array));
	}
	
	public static int Sum(int[] arr) {
		int sum = 0;
		for(int count = 0; count < arr.length - 2;count++) {
			for(int i = (count + 1);i < arr.length - 1;i++){
				for(int k= (i + 1);k < arr.length;k++) {
					System.out.println(arr[count] + " " + arr[i] + " " + arr[k]);
					if(arr[count] + arr[i] + arr[k] == 0) {
						sum++;
						System.out.println("This one");
					}
				}
			}
		}
	  return sum;
	}
}
