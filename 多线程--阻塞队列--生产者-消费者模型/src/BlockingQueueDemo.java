import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> q1 = new ArrayBlockingQueue<>(100);
        BlockingQueue<String> q2 = new LinkedBlockingDeque<>();
        BlockingQueue<String> q3 = new PriorityBlockingQueue<>();

        q1.put("");
        String take = q1.take();
        System.out.println(take);
    }
}
