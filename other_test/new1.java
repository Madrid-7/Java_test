public class new1 {
	public static void main(String[] args) {
		int sumOdd = 0;
		int sumEve = 0;
		
		for(int i = 1; i <= 100; i++) {
			if(i % 2 == 0) {
				sumEve += i;
			} else {
				sumOdd += i;
			}
		}
		System.out.println(sumEve);
		System.out.println(sumOdd);
		
	}
}