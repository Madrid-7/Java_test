public class 单例模式之懒汉2 {
    private static 单例模式之懒汉2 instance = null;

    private 单例模式之懒汉2() {}

    public synchronized static 单例模式之懒汉2 getInstance() {
        if (instance == null) {
            instance = new 单例模式之懒汉2();
        }

        return instance;
    }

    public static 单例模式之懒汉2 getInstance2() {
        synchronized (单例模式之懒汉2.class) {
            if (instance == null) {
                instance = new 单例模式之懒汉2();
            }

            return instance;
        }
    }

    private static Object lock = new Object();
    public static 单例模式之懒汉2 getInstance3() {
        synchronized (lock) {
            if (instance == null) {
                instance = new 单例模式之懒汉2();
            }

            return instance;
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            单例模式之懒汉2 ins1 = 单例模式之懒汉2.getInstance();
            System.out.println(ins1);
        }
    }

    public static void main(String[] args) {
        MyThread[] threads = new MyThread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new MyThread();
        }
        for (int i = 0; i < 20; i++) {
            threads[i].start();
        }
        /*
        单例模式之懒汉1 ins1 = 单例模式之懒汉1.getInstance();
        单例模式之懒汉1 ins2 = 单例模式之懒汉1.getInstance();
        单例模式之懒汉1 ins3 = 单例模式之懒汉1.getInstance();

        System.out.println(ins1 == ins2);
        System.out.println(ins2 == ins3);
        */
    }
}
