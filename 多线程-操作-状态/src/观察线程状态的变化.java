import java.util.concurrent.TimeUnit;

public class 观察线程状态的变化 {

    public static class AThread extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 3; i++) {
                    System.out.println("working");
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {}
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AThread a = new AThread();
        System.out.println(a.getState());    //NEW
        a.start();
        System.out.println(a.getState());    //RUNABLE
        TimeUnit.SECONDS.sleep(2);
        System.out.println(a.getState());     //TIME_WAITING
        TimeUnit.SECONDS.sleep(2);
        System.out.println(a.getState());      //TERMINAED
    }
}
