class A<T> {

    private T value;

    public A(T value) {
        this.value = value;
    }

    public T get() {
        return this.value;
    }
}

public class Test2 {
    public static void main(String[] args) {
        A<String> a = new A<>("xxx");
        System.out.println(a.get());
    }
}
