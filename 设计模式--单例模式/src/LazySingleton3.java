/**
 * 1. 懒汉（延迟加载）
 * 2. 正确
 * 3. 效率还比较高
 * 通过
 * 1. volatile 的引入，目的解决 ins = new ...(); 重排序带来的问题
 * 2. synchronized 的引入，目的是解决原子性问题
 * 3. 见方法注释
 */
public class LazySingleton3 {
    private static volatile LazySingleton3 ins = null;

    private LazySingleton3() {}

    // 1. 为什么要把加锁的过程放到 ins == null 里头 —— 提升效率
    // 2. 为什么需要进行二次判断 —— 抢锁成功，之后，条件可能发生了变化
    public static LazySingleton3 getInstance3() {
        if (ins == null) {
            synchronized (LazySingleton3.class) {
                if (ins == null) {
                    ins = new LazySingleton3(); // 这个代码是有重排序问题的
                    // 可能导致 ins 不为 null 时，但 ins 指向的对象
                    // 没有被完全初始化好
                }
            }
        }

        return ins;
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            LazySingleton3 ins1 = LazySingleton3.getInstance3();
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
