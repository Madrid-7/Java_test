public class Test3 {

    private static void reverse(String str) {
        char arr[] = str.toCharArray();
        for (int i = 0; i < arr.length/2; i++) {
            char tmp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = tmp;
        }
    }

    public static void main(String[] args) {
        String str1 = "i am stuent";

    }
}
