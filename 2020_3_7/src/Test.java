import java.util.Scanner;

public class Test {
    public static int fun(int n) {
        if (n % 2 != 0 || n == 10 || n < 6) {
            return -1;
        }
        if (n % 8 == 0) {
            return n % 8;
        } else {
            return n % 8 +1;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println(fun(n));

    }
}
