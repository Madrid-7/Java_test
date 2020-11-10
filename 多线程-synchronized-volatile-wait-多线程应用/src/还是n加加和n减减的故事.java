public class 还是n加加和n减减的故事 {
    static long n = 0;
    static final long COUNT = 100_0000;

    static class n加加 extends Thread {
        @Override
        public void run() {
            for (long i = 0; i < COUNT; i++) {
                synchronized (还是n加加和n减减的故事.class) {
                    n++;
                }
            }
        }
    }

    static class n减减 extends Thread {
        @Override
        public void run() {
            for (long i = 0; i < COUNT; i++) {
                synchronized (还是n加加和n减减的故事.class) {
                    n--;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread npp = new n加加();
        Thread nss = new n减减();
        npp.start();
        nss.start();

        npp.join();
        nss.join();

        System.out.println(n);
    }
}
