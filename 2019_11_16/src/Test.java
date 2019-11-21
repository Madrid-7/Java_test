/*
public class Test {

    public static void main(String[] args) {
        String s = "hello";
        try {
            s = s+" world";
            s.toUpperCase();
            String[] a = s.split("o");
            System.out.println(a.length);
        } catch (Exception e) {
            System.out.print(s);
        } finally {
            System.out.print(s);
        }
    }
}

public class Test {
    public static void main(String [] args) {
        try {
            badMethod();
            System.out.print("A");
        } catch (RuntimeException ex) {
            System.out.print("B");
        } catch (Exception ex1) {
            System.out.print("C");
        } finally {
            System.out.print("D");
        }
        System.out.print("E");
    }
    public static void badMethod() {
        throw new RuntimeException();
    }
}

 */
import java.util.*;
public class Test{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        int count=0;
        while(count<n) {
            try {
                String temp=sc.next();
                int i=Integer.parseInt(temp);
                a[count]=i;
                count++;
            }catch(Exception e) {
                System.out.println(e);
            }
        }
        System.out.println(Arrays.toString(a));
        sc.close();
    }
}
