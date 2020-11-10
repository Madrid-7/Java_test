public class 多线程代码 {
    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
