import java.util.concurrent.TimeUnit;

public class 代码重排序 {
    private static class Counter {
        private int n1 = 0;
        private int n2 = 0;
        private int n3 = 0;
        private int n4 = 0;
        private int n5 = 0;
        private int n6 = 0;
        private int n7 = 0;
        private int n8 = 0;
        private int n9 = 0;
        private int n10 = 0;
        public void write() {
            n1 = 1;
            n2 = 2;
            n3 = 3;
            n4 = 4;
            n5 = 5;
            n6 = 6;
            n7 = 7;
            n8 = 8;
            n9 = 9;
            n10 = 10;
        }
        public void read() {
            System.out.println("n1 = " + n1);
            System.out.println("n2 = " + n2);
            System.out.println("n3 = " + n3);
            System.out.println("n4 = " + n4);
            System.out.println("n5 = " + n5);
            System.out.println("n6 = " + n6);
            System.out.println("n7 = " + n7);
            System.out.println("n8 = " + n8);
            System.out.println("n9 = " + n9);
            System.out.println("n10 = " + n10);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread thread1 = new Thread(() -> {
            counter.read();
        }, "读");
        Thread thread2 = new Thread(() -> {
            counter.write();
        }, "写");
        thread1.start();
        thread2.start();
    }
}
