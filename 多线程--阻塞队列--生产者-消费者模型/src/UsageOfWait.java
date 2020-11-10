import java.util.Scanner;

public class UsageOfWait {
    static Object o = new Object();

    static class Print extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 50; i++) {
                    System.out.println(i);

                    if (i == 30) {
                        // 我等在 o 上
                        synchronized (o) { // 把 o 锁起来
                            o.wait();   // o 这把锁难道不是 Print 线程还持有么？
                        } // 才会释放锁
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Print print = new Print();
        print.start();

        //Thread.sleep(2 * 1000);
        /*
        System.out.println("输入任意字符，以唤醒 Print 线程");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        */

        synchronized (o) { // 主线程需要请求 o 这把锁的时候，难道不是 o 还被 Print 持有么？
            o.notify();
        }
    }
}
