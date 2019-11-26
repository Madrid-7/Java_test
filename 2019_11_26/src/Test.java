public class Test {
    public static void main(String[] args) {
        String str = "12345fg432";
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if(array[i] > '9' || array[i] < '0') {
                System.out.println("false");
            }
        }
    }
}