import java.util.Scanner;

public class Main {
    public static String reverse (String str, int start, int end) {
        char[] arr = str.toCharArray();
        while(start < end) {
            char tmp = arr[start];
            arr[start] = arr[end-1];
            arr[end-1] = tmp;
            start++;
            end--;
        }
        String str1 = new String(arr);
        return str1;
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        String str = sc.next();
        str = reverse(str, 0, k);
        str = reverse(str, k, str.length());
        str = reverse(str, 0, str.length());
        System.out.println(str);
    }

    public static void main2(String[] args) {
        String str = "abcdef";
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length/2; i++) {
            char tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length-1-i] = tmp;
        }
        String str1 = new String(arr);
        System.out.println(str1);

    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String str1[] = str.split(" ");
        String str2 = "";
        for (int i = 0; i < str1.length; i++) {
            str2 += str1[i];
        }
        System.out.println(str2);
    }
}
