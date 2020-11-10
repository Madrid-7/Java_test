import java.util.Scanner;

public class 试试volatile的可见性问题 {
    static volatile boolean flag = true;

    static class MyThread extends Thread {
        @Override
        public void run() {
            int n = 0;
            while (flag) {
                n++;
            }
            System.out.println(n);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        t.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("随便输入什么，让线程停止");
        scanner.nextLine();
        flag = false; // 其他的错误情况是，这里的修改，子线程看不到
        Thread.sleep(5 * 1000);
        System.out.println(t.isAlive());
    }
}
