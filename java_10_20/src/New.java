/*
public class Times {
    public static void main(String args[]){
        for(int i=1;i<=9;i++){
            for(int j=1;j<=i;j++){
                System.out.print(j+"*"+i+"="+i*j+"\t");
            }
            System.out.println();
        }
    }
}

public class CommonDivisor{
    public static void main(String args[])
    {
        commonDivisor(24,32);
    }
    static int commonDivisor(int M, int N)
    {
        if(N<0||M<0)
        {
            System.out.println("ERROR!");
            return -1;
        }
        if(N==0)
        {
            System.out.println("the biggest common divisor is :"+M);
            return M;
        }
        return commonDivisor(N,M%N);
    }
}

public class Function{
    public static void main(String[] args){
        double n=0.0;
        double i=-1.0;
        double tem=0.0;
        double sum=0.0;
        for(n=1;n<101;n++){
            i=-i;
            tem=(1/n)*i;
            sum=sum+tem;
        }
        System.out.println(sum);
    }
}

public class Counting9 {
    public static void main(String[] args) {
        int num = 0;
        int count = 0;//计数器
        for (num = 1; num <= 100; num++) {
            //判断个位上是否有 9
            if (num % 10 == 9) {
                count++;
            }
            //判断十位上是否有 9
            if (num / 10 == 9) {
                count++;
            }
        }
        System.out.println(count);
    }
}


public class Ex11 {
    public static void main(String[] args) {
        int i,j,k,n;
        System.out.print("水仙花数为：");
        for(i = 1; i < 10; i++){//分别循环三位数的百、十、个位
            for(j = 0; j < 10; j++){
                for(k = 0; k < 10; k++){
                    n = i*100 + j * 10 + k;
                    if(n == (i*i*i)+(j*j*j)+(k*k*k)){
                        System.out.print(n+" ");
                    }
                }
            }
        }
    }
}


import java.util.Scanner;
public class Test{
   public static void main(String[] args){
         System.out.println("欢迎使用");
         for(int i=0;i<3;i++){
             System.out.println("请输入密码：");
             Scanner in = new Scanner(System.in);
             String password = in.next();
             if(password.equals("987654")){
                 System.out.println("登陆成功");
                 break;
             }
             else{
                 System.out.println("密码错误，请重新输入，你还有"+(2-i)+"次输入机会");
             }
         }
    }
}


public class Count1 {
    public static void main(String[] args) {
        int num = 23;
        int count = 0;
        while (num > 0) {
            if (num % 2 == 1) {
                count++;
            }
            num /= 2;
        }
        System.out.println(count);
    }
}

public class BinaryNumber {
    public static void main(String[] args) {
        int num = 14;
        int check = 0;
        odd(num);
        System.out.println();
        even(num);
    }
    public static void odd(int num) {
        for (int i = 30; i >= 0; i = i - 2) {
            if ((num & (1 << i)) != 0) {
                System.out.print("1 ");
            } else {
            System.out.print("0 ");
            }
        }
    }
    public static void even(int num) {
        for (int i = 31; i >= 0; i = i - 2) {
            if ((num & (1 << i)) != 0) {
                System.out.print("1 ");
            } else {
                System.out.print("0 ");
            }
        }
    }
}

public class Number {
    public static void main(String[] args) {
        int num = 123;
        Print(num);
    }
    public static void Print(int num) {
        //递归终止条件
        if (num > 9) {
            Print(num / 10);
        }
        System.out.print(num % 10 + " ");
    }
}


import java.util.Random;
import java.util.Scanner;
public class ToGuessNumber {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        //设置一个随机数
        int answerNum = random.nextInt(100) + 1;
        //提示用户输入一个数字
        System.out.println("请输入您猜的数字:");
        while(true) {
            int toGuess = sc.nextInt();
            //用户输入的数字和随机数比较，并给出提示
            if (toGuess > answerNum) {
                System.out.println("大了！");
            } else if (toGuess < answerNum) {
                System.out.println("小了！");
            } else {
                System.out.println("猜对了！");
                break;
            }
        }
        sc.close();
    }
}

 */