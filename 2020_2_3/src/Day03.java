public class Day03 {
    public boolean canConstruct(String ransomNote, String magazine) {
        StringBuilder sb = new StringBuilder(magazine);
        int index;
        for (char c : ransomNote.toCharArray()) {
            index = sb.indexOf(String.valueOf(c));
            if (index >= 0) {
                sb.deleteCharAt(index);
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }
        int tmp = x;
        int y = 0;
        while(tmp > 0) {
            y = y * 10 + tmp % 10;
            tmp /= 10;
        }
        return x == y;
    }
}
