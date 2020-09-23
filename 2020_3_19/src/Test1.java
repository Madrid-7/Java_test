public class Test1 {
    public static void main1(String[] args) {
        String s1 = "abc" +"def";
        String s2 = new String(s1);
        if(s1.equals(s2)) {
            System.out.println(1);
        }
        if(s1==s2) {
            System.out.println(2);
        }
    }

    public static void main(String[] args) {
        int i = 5;
        int s = (i++)+(++i)+(i--)+(--i);
        System.out.println(s);   //24
    }
}



