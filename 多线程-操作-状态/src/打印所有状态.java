public class 打印所有状态 {
    public static void main(String[] args) {
        Thread.State[] states = Thread.State.values();

        for (Thread.State st : states) {
            System.out.println(st);
        }
    }
}
