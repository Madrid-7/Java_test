public class Test{
    private static final long COUNT = 10_0000_0000L;

    public static void main(String[] args) throws InterruptedException {
        // 使用多线程方式
        concurrency();
        // 使用单线程方式
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long begin = System.nanoTime();

        // 利用一个线程计算 a 的值
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < COUNT; i++) {
                    a--;
                }
            }
        });
        thread.start();

        // 主线程内计算 b 的值
        int b = 0;
        for (long i = 0; i < COUNT; i++) {
            b--;
        }
        // 等待 thread 线程运行结束
        thread.join();

        // 统计耗时
        long end = System.nanoTime();
        double ms = (end - begin) * 1.0 / 1000 / 1000;
        System.out.printf("多线程: %f 毫秒%n", ms);

    }
    private static void serial() {
        // 全部在主线程内计算 a、b 的值
        long begin = System.nanoTime();
        int a = 0;
        for (long i = 0; i < COUNT; i++) {
            a--;
        }
        int b = 0;
        for (long i = 0; i < COUNT; i++) {
            b--;
        }
        long end = System.nanoTime();
        double ms = (end - begin) * 1.0 / 1000 / 1000;
        System.out.printf("单线程: %f 毫秒%n", ms);
    }
}

