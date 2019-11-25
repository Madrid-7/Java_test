package com.operation;

import com.book.BookList;

public class ExitOperation implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("Exit");
        System.exit(-1);
    }
}
