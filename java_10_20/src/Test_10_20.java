public class Test_10_20 {
    public static int[] transForm(int[] array) {
        int[] arr2 = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arr2[i] = array[i] * 2;
        }
        return arr2;
    }

    public static String toString(int[] array) {
        String str = "[";
        for (int i = 0; i < array.length; i++) {
            str += array[i];
            if (i != array.length - 1) {
                str += ",";
            }
        }
        str += "]";
        return str;
    }

    public static int arrMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int arrMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static double arrAve(int[] arr) {
        double sum = 0.0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum/arr.length;
    }

    public static int[] arrReverse(int[] arr) {
        int[] array = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            array[i] = arr[arr.length - 1 - i];
        }
        return array;
    }

/*    public static int[] arrChange(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if(arr[left] % 2 == 0) {

            }
        }
    }

 */

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int[] array2 = transForm(array);
        for (int x : array2) {
            System.out.println(x);
        }
        System.out.println(toString(array));

    }
}
