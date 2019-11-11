import java.util.Scanner;

public class Arrays_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] srr=str.split(" ");
        int[] arr = new int [srr.length];

        for (int i = 0; i < srr.length; i++) {
            arr[i] = Integer.parseInt(srr[i]);
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        for (int i = 0; i < arr.length - 3; i++) {
            arr[i] = arr[i + 1];
            System.out.print(arr[i]+" ");
        }
        System.out.print(arr[arr.length - 2]);
    }
}
