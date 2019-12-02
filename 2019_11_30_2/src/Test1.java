public class Test1 {

     private static int[] toArray(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;
        int[] arr3 = new int[arr1.length+arr2.length];
        while (i < arr1.length && j < arr2.length) {
            if(arr1[i] < arr2[j]) {
                arr3[i+j] = arr1[i];
                i++;
            } else {
                arr3[i+j] = arr2[j];
                j++;
            }
        }
        if(i == arr1.length) {
            System.arraycopy(arr2, j, arr3, i+j, arr2.length-j);
        } else {
            System.arraycopy(arr1, i, arr3, i+j, arr1.length-i);
        }
        return arr3;
    }

    public static void main(String[] args) {
        int[] array1 = {1,3,6,9};
        int[] array2 = {2,4,6,8};
        int[] array3 = toArray(array1, array2);
        for (int x : array3) {
            System.out.print(x+" ");
        }
    }
}
