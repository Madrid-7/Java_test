import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

        char[] arr = str2.toCharArray();

        Set<Character> set = new TreeSet<>();

        for (char ch: arr) {
            set.add(ch);
        }

        for (int i = 0; i < str1.length(); i++) {
            if (!set.contains(str1.charAt(i))) {
                System.out.print(str1.charAt(i));
            }
        }
    }

    public static void main2(String[] args) {
        StringBuilder a = new StringBuilder("A");
        StringBuilder b = new StringBuilder("B");
        operate(a, b);
        System.out.println(a + "." + b);       //: AB.B
    }

    static void operate(StringBuilder a, StringBuilder b) {
        a.append(b);
        b = a;
    }

    public static void main(String[] args) {
        int x, y;
        x = 5 >> 2;
        y = x >>> 2;
        System.out.println(x + "---" + y);
    }
}
