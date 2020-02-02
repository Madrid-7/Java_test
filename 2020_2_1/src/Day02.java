public class Day02 {
    public int removeElement(int[] nums, int val) {
        int left = nums.length - 1;
        int right = 0;
        while (left >= right) {
            if(nums[right] == val && nums[left] != val) {
                int tmp = nums[right];
                nums[right] = nums[left];
                nums[left] = tmp;
            }
            if(nums[right] != val) {
                right++;
            }
            if(nums[left] == val) {
                left--;
            }
        }
        return left + 1;
    }

    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    public int searchInsert2(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int right = nums.length-1;
        int left = 0;
        while(left <= right) {
            int mid = right + (left - right)/2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
