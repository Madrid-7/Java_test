import java.util.Scanner;
public class Sushu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int k = 0;
        for(int j = 2; j < x; j++) {
            if(x%j == 0) {
                k = 1;
                break;
            }
        }
        if(k == 0) {
            System.out.print(x + "是素数");
        } else {
            System.out.print(x + "不是素数");
        }
    }
}
