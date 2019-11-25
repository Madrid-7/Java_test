package com.user;
import com.operation.*;

import java.util.Scanner;

public class NormalUser extends User{
    public NormalUser(String name) {
        this.name = name;
        this.operations = new IOperation[]{
                new ExitOperation(),
                new FindOperation(),
                new BorrowOperation(),
                new ReturnOperation(),
        };
    }

    @Override
    public int menu() {
        System.out.println("=========================");
        System.out.println("hello"+this.name+"欢迎使用图书管理系统");
        System.out.println("1.查找图书");
        System.out.println("2.借阅图书");
        System.out.println("3.归还图书");
        System.out.println("0.退出系统");
        System.out.println("=========================");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }
}
