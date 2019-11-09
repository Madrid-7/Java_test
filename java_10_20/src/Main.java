/*
class Rectangle {

    private double width;
    private double height;
    Rectangle() {
        width = 1;
        height = 1;
    }
    Rectangle(double w, double h) {
        width = w;
        height = h;
    }
    public double getArea() {
        return width * height;
    }
    public double getPerimeter() {
        return 2 * (width + height);
    }
}
public class Main {

/*
public class leapYear{
    public static void main(String[] args){
        int year;
        for(year=1000;year<2001;year++){
            if((year%400==0)||(year%4==0&&year%100!=0)){
                System.out.printf("%d ",year);
            }
        }
    }
}


    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

*/

import java.util.Scanner;
public class Main{
    public static void main(String args[]){
        int num=0,chartra=0,blak=0,other=0;
        Scanner sc =new Scanner(System.in);
        String result=sc.nextLine();
        char x[]=result.toCharArray();
        for(int i=0;i<x.length;i++) {
            if(Character.isDigit(x[i])){
                 num++;
            } else if (Character.isLetter(x[i])) {
                chartra++;
            } else if (Character.isSpace(x[i])) {
                blak++;
            } else{
                other++;
            }
        }
        System.out.println("字母个数："+chartra);
        System.out.println("数字个数："+num);
        System.out.println("空格个数："+blak);
        System.out.println("其他字符个数："+other);
    }
}
