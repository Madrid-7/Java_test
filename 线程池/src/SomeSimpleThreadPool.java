import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SomeSimpleThreadPool {
    public static void main(String[] args) {
        ExecutorService p1 = Executors.newFixedThreadPool(10);
        ExecutorService p2 = Executors.newCachedThreadPool();
    }
}
