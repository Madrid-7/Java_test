import java.util.concurrent.TimeUnit;

public class SimpleTimer {
    interface SimpleTimerTask {
        void run();
    }

    static class Worker extends Thread {
        long delay;
        SimpleTimerTask task;

        Worker(SimpleTimerTask task, long delay) {
            this.task = task;
            this.delay = delay;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(delay);
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void schedule(SimpleTimerTask task, long delay) {
        Worker worker = new Worker(task, delay);
        worker.start();
    }

    static class MyTask implements SimpleTimerTask {
        @Override
        public void run() {
            System.out.println("10 秒之后");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleTimer timer = new SimpleTimer();
        MyTask task = new MyTask();
        timer.schedule(task, 10 * 1000);

        int i = 0;
        while (true) {
            System.out.println(i++);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
