import java.util.Scanner;

import java.util.concurrent.TimeUnit;

public class 内存可见性解决 {


    private volatile static boolean state = true;

    private static class A extends Thread {
        @Override
        public void run() {
            int n = 0;
            while (state) {
                n++;

                //System.out.println(state);

                //System.out.println(n);
            }
            System.out.println("quit");
        }
    }

    public static void main(String[] args) {
        Thread t = new A();
        t.start();

        Scanner sc = new Scanner(System.in);
        System.out.println("please input something");
        sc.nextLine();
        state = false;

    }


}
