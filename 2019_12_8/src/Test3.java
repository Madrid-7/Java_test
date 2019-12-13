import java.util.ArrayList;
import java.util.List;

class T {}

class A extends T{}

class B extends A{}

public class Test3 {

    public static void fun(List<Object> list) {}

    public static void main(String[] args){

        List<Class<? extends T>> list = new ArrayList<>();
        list.add(B.class);

        List<String> list1 = new ArrayList<>();
        fun(list1);
    }

}