import java.sql.SQLOutput;

public class yield展示 {
    //yield把线程从CPU上拉下来，即从running->ready

    private static class otherThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("other");
            }
        }
    }


    public static void main(String[] args) {
        Thread t = new otherThread();
        t.start();

        while (true) {
            System.out.println("hello");
            Thread.yield();
        }
    }
}
