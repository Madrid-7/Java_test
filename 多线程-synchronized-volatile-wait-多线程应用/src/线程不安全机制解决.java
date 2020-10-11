public class 线程不安全机制解决 {


    static class Add extends Thread {
        private Object lock ;
        Add(Object o) {
            this.lock = o;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                synchronized (lock) {
                    n++;
                }
            }
        }
    }

    static class Sub extends Thread {
        private Object lock ;
        Sub(Object o) {
            this.lock = o;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                synchronized (lock) {
                    n--;
                }
            }
        }
    }

    private static long n = 0;

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Sub sub = new Sub(lock);
        sub.start();
        Add add = new Add(lock);
        add.start();

        add.join();
        sub.join();

        System.out.println(n);
    }


}
