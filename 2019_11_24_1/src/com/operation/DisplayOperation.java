package com.operation;

import com.book.BookList;

public class DisplayOperation implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("显示书籍");
        for (int i = 0; i < bookList.getUsedSize(); i++) {
            System.out.println(bookList.getBook(i));
        }
    }
}
