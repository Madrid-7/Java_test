import java.util.*;
public class Main {
    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        int num[] = new int[10];

        for (int i = 0; i < 10; i++) {
            num[i] = in.nextInt();
        }

        for (int i = 1; i < 10; i++) {
            if (num[i] != 0) {
                System.out.print(i);
                num[i]--;
                break;
            }
        }

        while (num[0] != 0) {
            System.out.print(0);
            num[0]--;
        }

        for (int i = 1; i < 10; i++) {
            while (num[i] != 0) {
                System.out.print(i);
                num[i]--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = n * n - n + 1;
            System.out.print(m);
            for (int i = 1; i < n; i++) {
                m += 2;
                System.out.print("+" + m);
            }
        }
        System.out.println();
    }
}
