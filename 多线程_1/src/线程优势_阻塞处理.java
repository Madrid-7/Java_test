import java.util.Scanner;

public class 线程优势_阻塞处理 {

    private static long fib (int n) {
        if(n < 2) {
            return  n;
        }

        return fib(n - 1) + fib(n - 2);
    }

    private static class FibThread extends Thread {
        private int n;

        FibThread(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            System.out.println(n+ "-fib->" + fib(n));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();

            FibThread thread = new FibThread(n);
            thread.start();
        }
    }
}
