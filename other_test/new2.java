public class new2 {
	public static void main(String[] args) {
		int num = 5;
		int ret = 1;
		int sum = 0;
		for(int i = 1; i <= num; i++) {
			ret *= i;
			sum += ret;
		}
		System.out.println("sum = " + sum);
	}
}