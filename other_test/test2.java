public class test2 {
	public static void main(String[] args) {
		int a = 10;
            int b = 20;
            int c = 30;
            int Max = a;
            int Min = a;
            if(Max < b) {
                Max = b;
            }
            if(b < c) {
                Max = c;
            }
            if(Min > b) {
                Min = b;
            }
            if(b > c) {
                Min = c;
            }
            System.out.println("Max = "+Max+",Min = "+Min);
	}
}