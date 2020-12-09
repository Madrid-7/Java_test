package Test;

class MyThread1 implements Runnable {
    private boolean flag;
    private Object obj;
    public MyThread1(boolean flag, Object obj) {
        super();
        this.flag = flag;
        this.obj = obj;
    }
    public void waitMethod() {
        synchronized (obj) {
            try {
                while (true) {
                    System.out.println("wait()方法开始.. " +
                            Thread.currentThread().getName());
                    obj.wait();
                    System.out.println("wait()方法结束.. " +
                            Thread.currentThread().getName());
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void notifyMethod() {
        synchronized (obj) {
            try {
                System.out.println("notify()方法开始.. " + Thread.currentThread().getName());
                obj.notify();
                System.out.println("notify()方法结束.. " + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {
        if (flag) {
            this.waitMethod();
        } else {
            this.notifyMethod();
        }
    }
}

public class notify {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        MyThread waitThread = new MyThread(true, object);
        MyThread notifyThread = new MyThread(false, object);
        Thread thread1 = new Thread(waitThread, "wait线程");
        Thread thread2 = new Thread(notifyThread, "notify线程");
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        System.out.println("main方法结束");
    }
}
