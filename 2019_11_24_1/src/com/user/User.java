package com.user;

import com.book.*;
import com.operation.IOperation;

public abstract class User {
    public String name;

    public abstract int menu();

    public IOperation[] operations;

    public void doOperation(int choice, BookList bookList) {
        operations[choice].work(bookList);
    }
}
