import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Main {
    private static ArrayList<String> list=new ArrayList<String>();
    private static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        String str=sc.nextLine();
        while(!str.equals("###")) {
            list.add(str);
            str=sc.nextLine();
        }
        Collections.sort(list);
        list.forEach(a->System.out.print(a+" "));
    }
}
