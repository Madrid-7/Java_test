import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入一串字符a");
        String a = in.next();
        System.out.println("请再输入一串字符b");
        String b = in.next();
        String c = a + b;
        c = c.toUpperCase();
        System.out.println(c);
        System.out.println(c.substring(0, 1) + c.substring(c.length()-1));
    }
}

