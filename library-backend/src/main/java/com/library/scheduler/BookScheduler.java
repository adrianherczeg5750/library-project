package com.library.scheduler;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.scheduler.Scheduled;
import io.smallrye.common.annotation.Blocking;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@ApplicationScoped
public class BookScheduler {

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private final BookService bookService;

    public BookScheduler(BookService bookService) {
        this.bookService = bookService;
    }

    @Blocking
    @Scheduled(cron = "0 0 20 ? * MON-FRI")
    public void scheduleBooks() {
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int bookCount = ThreadLocalRandom.current().nextInt(2000, 4001);
            BookImport task = new BookImport(bookCount, bookService);
            futures.add(executorService.submit(task));
        }
    }
}
