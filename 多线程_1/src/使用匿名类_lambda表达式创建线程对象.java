public class 使用匿名类_lambda表达式创建线程对象 {
    public static void main(String[] args) {

        Thread t1 = new Thread() {
            @Override
            public void run() {

            }
        };

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        Thread t3 = new Thread(() -> {
            //run() 方法

        });
    }
}
