import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    /*
    public static int minWay(int n) {
        int f0 = 0;
        int f1 = 1;

        int min1 = 0;
        int min2 = 0;

        while (true) {
            int sum = f0 + f1;
            f0 = f1;
            f1 = sum;

            if (f1 < n) {
                min1 = n - f1;
            } else {
                min2 = f1 - n;
                break;
            }
        }
        return min1 > min2 ? min2 :min1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(minWay(sc.nextInt()));
    }
    */
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] arr = str.split(" ");
            for (String s: arr) {
                set.add(s);
            }
        }
        System.out.print(set.size());
    }
}
