import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        // 创建了一个
        // 最多有 10 个正式员工
        // 最多有 20 个员工包括正式和临时
        // 临时工空闲时间最多有 60 SECONDS
        // 传递任务的队列是一个 ArrayBlockingQueue（容量是 30）
        // 执行默认的拒绝策略：处理不过来之后抛出异常
        ExecutorService p1 = new ThreadPoolExecutor(
                10,
                20,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(30)
        );
        // ExecutorService 引用可以指向一个线程池对象

        // 怎么提交任务
        p1.execute(new Runnable() {
            @Override
            public void run() {
            }
        });

        p1.submit(new Runnable() {
            @Override
            public void run() {
            }
        });

        // 怎么关闭线程池
        p1.shutdown();  // 推荐使用上面的
        p1.shutdownNow();
    }
}
