import java.util.Scanner;

public class NoThreadPool {
    static class Deliver extends Thread {
        String 快递;

        public Deliver(String 快递) {
            this.快递 = 快递;
        }

        @Override
        public void run() {
            System.out.println("开始送快递: " + 快递);
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("快递送完了: " + 快递);
        }
    }

    public static void main(String[] args) {
        // 主线程就是前台
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String 快递 = scanner.nextLine();
            Thread worker = new Deliver(快递);
            worker.start();
        }
    }
}
