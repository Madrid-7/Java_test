public class WaitBelongsWhom {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();

        o.wait();
        o.notify();
        o.notifyAll();
    }
}
