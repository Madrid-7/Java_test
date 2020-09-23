package 使用自定义的标志位通知停止;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Condition condition = new Condition();

        B b = new B(condition);
        //定义变量并传入引用，使得 B线程看的条件和 main 修改的条件是同一个对象
        //B 线程才能看到修改

        b.start();

        sc.nextLine();
        condition.running = false;

    }
}
