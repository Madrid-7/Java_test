public class 手动创建线程 {
    static class MyThread extends Thread {
        @Override
        public void run() {

        }
    }

    public static void main1(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();


    }


    static class MyRunnable implements Runnable {
        @Override
        public void run() {

        }
    }

    public static void main(String[] args) {
        MyRunnable r1 = new MyRunnable();
        MyRunnable r2 = new MyRunnable();

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

    }
}
