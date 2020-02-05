public class Day06 {
    public int[] sortedSquares(int[] A) {
        int right = A.length - 1;
        int left = 0;
        int count = right;
        int[] B = new int[A.length];
        while(A[left] <= 0 && A[right] > 0) {
            if(A[left]*A[left] >= A[right]*A[right]) {
                B[count--] = A[left]*A[left];
                left++;
            } else {
                B[count--] = A[right]*A[right];
                right--;
            }
        }
        while (left < A.length && A[left] <= 0) {
            B[count--] = A[left]*A[left];
            left++;
        }
        while (right >= 0 && A[right] > 0) {
            B[count--] = A[right]*A[right];
            right--;
        }
        return B;
    }

    public String reverseOnlyLetters(String S) {
        int left = 0;
        int right = S.length() - 1;
        char[] arr = S.toCharArray();
        while (left < right) {
            if (!Character.isLetter(arr[left])) {
                left++;
            }
            if (!Character.isLetter(arr[right])) {
                right--;
            }
            if (Character.isLetter(arr[right]) && Character.isLetter(arr[left])) {
                char tmp = arr[right];
                arr[right] = arr[left];
                arr[left] = tmp;
                right--;
                left++;
            }
        }
        return new String(arr);
    }
}
