import java.util.concurrent.*;

public class BlockdingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> q1 = new ArrayBlockingQueue<>(100);
        BlockingQueue<String> q2 = new LinkedBlockingQueue<>();
        BlockingQueue<String> q3 = new PriorityBlockingQueue<>();
    }
}
