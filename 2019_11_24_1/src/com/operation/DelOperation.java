package com.operation;

import com.book.Book;
import com.book.BookList;

public class DelOperation implements IOperation {
    @Override
    public void work(BookList bookList) {
        System.out.println("删除书籍");
        System.out.println("输入要删除的书名:");
        String name = sc.next();
        int i = 0;
        Book book = null;
        //遍历bookList数组
        for ( ;i < bookList.getUsedSize(); i++) {
            book = bookList.getBook(i);
            if(book.getName().equals(name)) {
                break;
            }
        }

        if(i == bookList.getUsedSize()) {
            System.out.println("没有此书");
            return;
        }

        //i号下标的书籍就是要删除的书籍

        for (int j = i; j < bookList.getUsedSize()-1; j++) {
            //后一个覆盖前一个
            //[j] ==>  [j+1]
            //10          18
            //bookList.getBook(j) = bookList.getBook(j+1);
            book = bookList.getBook(j+1);
            bookList.setBooks(j,book);
        }
        int curSize = bookList.getUsedSize();
        bookList.setUsedSize(curSize-1);
        System.out.println("删除成功！");
    }
}
