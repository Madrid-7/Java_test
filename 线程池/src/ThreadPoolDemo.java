import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

    private static long fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n-1) + fib(n-2);
    }

    private static class CalcTarget implements Runnable {
        private int n;

        CalcTarget(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            System.out.println(n + ": " + fib(n));
        }
    }

    public static void main(String[] args) {


        ExecutorService pool = new ThreadPoolExecutor(10, 10,
                0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            pool.execute(new CalcTarget(n));
        }

    }
}
