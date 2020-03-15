import java.util.Scanner;

public class Main {
    static int n;
    static int[] arr;

    static int count(int i, int sum) {
        if(sum == 0) return 1;
        if(i == n || sum < 0) return 0;
        return count(i+1,sum - arr[i]) + count(i+1, sum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            n = sc.nextInt();
            arr = new int[n];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println(count(0, 40));
        }
    }
}
