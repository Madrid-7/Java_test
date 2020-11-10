import java.util.Scanner;

public class MyBlockingArrayQueueDemo {
    // 定义个队列对象-生产者线程是 Producer，消费者线是 main 线程
    // 队列是需要在生产者消费者之间共享的
    static MyBlockingArrayQueue queue = new MyBlockingArrayQueue();

    // 定义一个生产者线程类
    static class Producer extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    System.out.println("准备放入 " + i);
                    queue.put(i);
                    System.out.println("放入成功");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        producer.start();



        Thread.sleep(2 * 1000);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            scanner.nextLine();
            System.out.println(queue.take());
        }
    }
}
