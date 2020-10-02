public class synchronized_test {

    //方法修饰符
    public static synchronized void staticMethod() {

    }

    public synchronized void method() {

    }


    //作为代码块 -- 同步代码块
    public void someMethod() {
        Object o = new Object();
        synchronized (o) {

        }
    }
}
