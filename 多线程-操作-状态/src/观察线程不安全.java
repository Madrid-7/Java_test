public class 观察线程不安全 {

    static class Add extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                n++;
            }
        }
    }

    static class Sub extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                n--;
            }
        }
    }

    private static long n = 0;

    public static void main(String[] args) throws InterruptedException {
        Sub sub = new Sub();
        sub.start();
        Add add = new Add();
        add.start();

        add.join();
        sub.join();

        System.out.println(n);
    }
}
