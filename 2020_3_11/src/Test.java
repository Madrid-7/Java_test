import java.util.Scanner;

public class Test {
    public static void main1(String[] args) {
        System.out.println(Math.round(11.5));      //12
    }

    public static void main2(String[] args) {
        int i = 0;
        Integer j = new Integer(0);
        System.out.println(i == j);            //true
        System.out.println(j.equals(i));       //true
    }

    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int sum = 1;
        for (int i = n; i > 0; i--) {
            sum *= i;
        }
        int count = 0;
        while (true) {
            if(sum %10 == 0) {
                count++;
            } else {
                System.out.println(count);
                return;
            }
            sum/=10;
        }
    }

    public static void main(String[] args) {
        byte b1 = 1, b2 = 2, b3, b6;
        final byte b4 = 4, b5 = 6;
        b6 = b4 + b5;

        //  b3 = (b1 + b2);   error

    }

}
