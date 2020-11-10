import java.util.Scanner;

/**
 * 演示多生产者、多消费者模型下的 BUG
 */
public class MyBlockingArrayQueueWrongDemo {
    // 定义个队列对象-生产者线程是 Producer，消费者线是 main 线程
    // 队列是需要在生产者消费者之间共享的
    static MyBlockingArrayQueue queue = new MyBlockingArrayQueue();

    // 定义一个生产者线程类
    static class Producer extends Thread {
        @Override
        public void run() {
            try {
                int i = 0;
                while (true) {
                    queue.put(i);
                    i++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer1 = new Producer();
        producer1.start();
        Producer producer2 = new Producer();
        producer2.start();
        Producer producer3 = new Producer();
        producer3.start();

        while (true) {
            queue.take();
        }
    }
}
