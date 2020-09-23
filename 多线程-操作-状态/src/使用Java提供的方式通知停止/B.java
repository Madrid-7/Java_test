package 使用Java提供的方式通知停止;

import java.util.concurrent.TimeUnit;

public class B extends Thread {
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("znpy");
            try {
                TimeUnit.SECONDS.sleep(5);

            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
