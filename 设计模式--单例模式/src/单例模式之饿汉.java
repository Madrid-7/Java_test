public class 单例模式之饿汉 {
    // 一开始初始化，饿的不行了
    private static 单例模式之饿汉 instance = new 单例模式之饿汉();

    private 单例模式之饿汉() {}

    public static 单例模式之饿汉 getInstance() {
        return instance;
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            单例模式之饿汉 ins1 = 单例模式之饿汉.getInstance();
            System.out.println(ins1);
        }
    }

    // 理论上，多线程环境下也是正确的
    public static void main(String[] args) {
        MyThread[] threads = new MyThread[20];
        for (int i = 0; i < 20; i++) {
            threads[i] = new MyThread();
        }
        for (int i = 0; i < 20; i++) {
            threads[i].start();
        }
        /*
        单例模式之饿汉 ins1 = 单例模式之饿汉.getInstance();
        单例模式之饿汉 ins2 = 单例模式之饿汉.getInstance();
        单例模式之饿汉 ins3 = 单例模式之饿汉.getInstance();

        System.out.println(ins1 == ins2);
        System.out.println(ins2 == ins3);
        */
    }
}
