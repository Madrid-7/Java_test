import java.util.Scanner;

public class Test{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int emptySum = sc.nextInt();
            if (emptySum == 0) return;

            int more = 0;

            while (emptySum > 2) {
                more += emptySum / 3;
                emptySum = emptySum / 3 + emptySum % 3;
            }

            if (emptySum == 2){
                more++;
            }
            System.out.println(more);
        }
    }
}
