import java.util.Scanner;
public class Arrays {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] start = new int [10];
        for (int i = 0; i < start.length; i++) {
            start[i] = in.nextInt();
        }
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            start[x-1] += 10;
        }
        for (int i = 0; i < start.length - 1; i++) {
            System.out.print(start[i] + " ");
        }
        System.out.print(start[9]);
    }
}
