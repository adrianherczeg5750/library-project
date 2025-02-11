package com.library.scheduler;

import com.library.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class BookImport implements Callable<Integer> {

    private final int bookCount;
    private final BookService bookService;

    public BookImport(int bookCount, BookService bookService) {
        this.bookCount = bookCount;
        this.bookService = bookService;
    }

    @Override
    public Integer call() {
        List<Book> books = BookGenerator.generateBooks(bookCount);

        int portion = 100;
        int all = 0;

        for (int i = 0; i < books.size(); i += portion) {
            int end;
            if (i + portion > books.size()) {
                end = books.size();
            } else {
                end = i + portion;
            }

            List<Book> group = new ArrayList<>();
            for (int j = i; j < end; j++) {
                group.add(books.get(j));
            }
            bookService.addBooks(group);

            all += group.size();
        }

        return all;
    }

}
