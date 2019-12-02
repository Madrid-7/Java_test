public class Test2 {

    private static String toStrnum(String str) {
        int count = 1;
        char ch = str.charAt(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length(); i++) {
            if(str.charAt(i) == ch) {
                count++;
            } else {
                sb.append(count).append(ch);
                ch = str.charAt(i);
                count = 1;
            }
        }
        return sb.append(count).append(ch).toString();
    }

    public static void main(String[] args) {
        String str = "aabbccdeeff";
        String str1 = toStrnum(str);
        System.out.println(str1);
    }
}
