package com.library.scheduler;

import com.library.entity.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.UUID;

public class BookGenerator {

    public static List<Book> generateBooks(int count) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Book book = new Book();
            book.title = "Könyv " + UUID.randomUUID();
            book.author = "Szerző " + UUID.randomUUID();
            book.quantity = ThreadLocalRandom.current().nextInt(1, 6);
            books.add(book);
        }
        return books;
    }
}
