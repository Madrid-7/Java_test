public class 线程提速 {
    private static final long COUNT = 10_0000_0000L;
    private static final int N = 10;

    private static void calc() {
        long r = 0;

        for (int i = 0; i < COUNT; i++) {
            r += i;
        }

        System.out.println(r);
    }

    private static class CalcThread extends Thread {
        @Override
        public void run() {
            //一个线程计算一遍calc
            calc();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();

        CalcThread[] threads = new CalcThread[N - 1];
        //加主线程共 N个线程
        for (int i = 0; i < N - 1; i++) {
            CalcThread thread = new CalcThread();
            thread.start();
            threads[i] = thread;
        }

        calc();


        for (int i = 0; i < N - 1; i++) {
            threads[i].join();
        }

        long end = System.nanoTime();
        double time = (end - start) * 1.0 / 1000 /1000;
        System.out.println("time:" + time);
    }
}
