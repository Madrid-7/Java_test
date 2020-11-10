import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class JavaTimerDemo {
    static class MyTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("已经是 10 秒之后了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimerTask task = new MyTask();
        Timer timer = new Timer();
        timer.schedule(task, 10 * 1000);

        int i = 0;
        while (true) {
            System.out.println(i++);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
