package 使用Java提供的方式通知停止;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        B b = new B();
        b.start();

        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        b.interrupt();
    }
}
