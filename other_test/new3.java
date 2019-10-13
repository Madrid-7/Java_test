public class new3 {
	
	public static void firstNum(int x) {
		if(x >= 10) {
			firstNum(x/10);
		}
		System.out.println(x%10);
	}
	
	public static int sum(int num) {
		int tmp = 0;
		if(num != 1)
		{
			tmp = sum(num-1);
		}
		return tmp + num;
	}
	
	public static int sumAll(int x) {
		int tmp = 0;
		if(x>=10) {
			tmp = sumAll(x/10);
		}
		return tmp + x%10;
	}
			
		
	public static void main(String[] args) {
		int x = 1234;
		firstNum(x);
		x = sum(10);
		System.out.println(x);
		x = sumAll(1729);
		System.out.println(x);
	}
}