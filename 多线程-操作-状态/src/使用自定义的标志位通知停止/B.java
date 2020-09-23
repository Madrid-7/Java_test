package 使用自定义的标志位通知停止;

public class B extends Thread {

    private Condition condition;

    B(Condition condition) {
        this.condition = condition;
    }

    @Override
    public void run() {
        while (condition.running) {
            System.out.println("znpy");
        }
    }

}
