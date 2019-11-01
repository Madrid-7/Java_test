import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int w = 5;
        int h = 6;
        Rectangle r1 = new Rectangle(w, h);
        Rectangle r2 = new Rectangle(w, h);
        Rectangle r3 = new Rectangle();
        System.out.println(r1.getArea());
        System.out.println(r1.getPerimeter());
        System.out.println(r1.getWidth());
        System.out.println(r1.getHeight());
        System.out.println(r1.toString());
        System.out.println(r1.equals(r2));
        System.out.println(r1.equals(r3));
    }
}
