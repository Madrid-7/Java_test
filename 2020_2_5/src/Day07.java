public class Day07 {
    public int[] sortArrayByParity(int[] A) {
        int right = A.length - 1;
        int left = 0;
        while(left < right) {
            if (A[left] % 2 == 1 && A[right] % 2 == 0) {
                int tmp = A[left];
                A[left] = A[right];
                A[right] = tmp;
            }
            if(A[left] % 2 == 0) {
                left++;
            }
            if (A[right] % 2 == 1) {
                right--;
            }
        }
        return A;
    }

    public int pivotIndex(int[] nums) {
        int sum = 0, leftsum = 0;
        for (int x: nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) return i;
            leftsum += nums[i];
        }
        return -1;
    }
}
