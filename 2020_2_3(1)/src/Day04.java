public class Day04 {
    public int lengthOfLastWord(String s) {
        if(s == null) {
            return 0;
        }
        String[] arr = s.split(" ");
        if(arr.length == 0) {
            return 0;
        }
        return arr[arr.length].length();
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        while(m > 0 && n > 0) {
            if(nums1[m-1] > nums2[n-1]) {
                nums1[m+n-1] = nums1[m-1];
                m--;
            } else {
                nums1[m+n-1] = nums2[n-1];
                n--;
            }
        }
        if(m <= 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
        }
    }
}
