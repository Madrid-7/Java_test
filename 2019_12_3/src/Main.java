import java.util.Scanner;

public class Main {

    public static int Inch(char[] ch, char c, int count) {
        for(int j = 0; j < count; j++) {
            if(ch[j] == c)
                return j;
        }
        return -1;
    }

    public static void Del2(String str) {
        char[] arr = str.toCharArray();
        char[] ch = new char[20];
        int[] num = new int[20];
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            if(-1 == Inch(ch, arr[i], count)) {
                ch[count++] = arr[i];
            } else {
                num[Inch(ch, arr[i], count)]++;
            }
        }
        int min = num[0];
        for(int i = 1; i < count; i++) {
            if(min > num[i])
                min = num[i];
        }
        for(int i = 0; i < arr.length; i++) {
            if(num[Inch(ch, arr[i], count)] != min)
                System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String str1 = sc.nextLine();
            Del2(str1);
        }
    }
}
