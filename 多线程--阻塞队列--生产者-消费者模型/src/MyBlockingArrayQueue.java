import java.util.Scanner;

/*
单生产者-单消费者情况

1. 是不是线程安全的？   是否有共享，是否有修改?
2. 怎么修改成线程安全的版本？ 通过加锁解决
                              volatile 不可以解决问题
3. 生产者在队列满时等待-消费者在队列空时等待
4. 生产者需要唤醒可能在等消费者；消费者需要唤醒可能在等的生产者
*/
public class MyBlockingArrayQueue {
    int[] array = new int[1];  // 下标处的数据可能出现生产者和消费者修改同一处的情况
    int front = 0;  // 只有消费者修改 front
    int rear = 0;   // 只有生产者修改 rear
    int size = 0;   // size 是生产者消费者都会修改的

    // 生产者才会调用 put
    synchronized void put(int value) throws InterruptedException {
        // 考虑满的情况
        while (size == array.length) {
            // 队列已满
            //throw new RuntimeException("队列已满");
            wait(); // 被欢迎的线程就会接着往下执行
                    // 它以为是消费者线程把它唤醒了，所以，它就以为队列已经不满了
                    // 但实际上，它可能会被生产者唤醒，所以，队列已经不满的推断是不成立的
                    // 修改方式，把 if 换成 while
        }
        // 通过 while 循环，保证了，走到这里时，size 一定不等于 array.length

        array[rear] = value;
        rear++;
        if (rear == array.length) {
            rear = 0;
        }
        //rear = (rear + 1) % array.length;
        size++;     // 我们需要保障的是 size++ 的原子性，所以 volatile 无法解决
        System.out.println(size); // 1 - 10
        notifyAll();   // 我们以为我们唤醒的是消费者线程，但实际可能唤醒了生产者线程
    }

    // 调用 take 的一定是消费者
    synchronized int take() throws InterruptedException {
        // 考虑空的情况
        while (size == 0) {
            // 空的
            //throw new RuntimeException("队列已空");
            wait();
        }

        int value = array[front];
        front++;
        if (front == array.length) {
            front = 0;
        }
        //front = (front + 1) % array.length;
        size--;
        System.out.println(size); // 0 - 9
        notifyAll();

        return value;
    }
}
