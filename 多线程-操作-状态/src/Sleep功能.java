import java.util.concurrent.TimeUnit;

public class Sleep功能 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println(start);
        //Thread.sleep(5000);
        TimeUnit.SECONDS.sleep(5);
        long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println(end - start);
    }
}
