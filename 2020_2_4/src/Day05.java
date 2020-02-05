import java.util.HashSet;
import java.util.Set;

public class Day05 {

    String str=new String("hello");
    char[]ch={'a','b'};
    public static void main(String args[]){
        Day05 ex=new Day05();
        ex.change(ex.str,ex.ch);
        System.out.print(ex.str+" and ");
        System.out.print(ex.ch);
    }
    public void change(String str,char ch[]){
        str = "test ok";
        ch[0]='c';
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int x: nums) {
            if (set.contains(x)) return true;
            set.add(x);
        }
        return false;
    }

    public boolean isLongPressedName(String name, String typed) {
        int len_n = name.length();
        int len_m = typed.length();
        if(len_m < len_n) return false;
        int i = 0;
        int j = 0;
        while (i < len_n && j < len_m) {
            if(name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if(j > 0 && typed.charAt(j) == typed.charAt(j-1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == len_n;
    }
}


