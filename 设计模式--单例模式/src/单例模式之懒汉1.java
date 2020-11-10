public class 单例模式之懒汉1 {

    private static 单例模式之懒汉1 instance = null;

    private 单例模式之懒汉1() {}

    public static 单例模式之懒汉1 getInstance() {
        if (instance == null) {
            instance = new 单例模式之懒汉1();
        }

        return instance;
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            单例模式之懒汉1 ins1 = 单例模式之懒汉1.getInstance();
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
