import java.util.Scanner;
public class Age {
    public static void main (String[] args) {
        int age;
        Scanner sc = new Scanner(System.in);
        age = sc.nextInt();
        if (age > 0 && age < 18 ) {
            System.out.println("少年");
        } else if (age < 29) {
            System.out.println("青年");
        } else if (age < 56) {
            System.out.println("中年");
        } else if (age >= 56) {
            System.out.println("老年");
        }
    }
}
