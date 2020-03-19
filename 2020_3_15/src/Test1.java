
public class Test1 {
    public static void main1(String[] args) {
        String str1 = "hello";
        String str2 = "he" + new String("llo");
        System.out.println(str1 == str2);      //false
    }

    private static void add(Byte b) {
        b = b++;
    }
    private static void test() {
        Byte a = 127;
        Byte b = 127;
        add(++a);
        System.out.println(a);        //-128
        add(b);
        System.out.println(b);         //127
    }
    public static void main2(String[] args) {
        test();
    }

    public static void main(String[] args) {

    }
}
