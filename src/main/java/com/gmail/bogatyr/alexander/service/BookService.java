package com.gmail.bogatyr.alexander.service;

import com.gmail.bogatyr.alexander.dao.BookDao;
import com.gmail.bogatyr.alexander.entity.Book;
import com.gmail.bogatyr.alexander.entity.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by alexander on 11.02.16.
 */
@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookDao bookDao;

    public Catalog findAll() {
        return bookDao.findAll();
    }

    @SuppressWarnings("unchecked")
    public Catalog update(Catalog request) {
        logger.debug("update book logic started");
        if (Objects.nonNull(request) && !request.isEmpty()) {
            Catalog main = bookDao.findAll();
            if (Objects.isNull(main) || main.isEmpty()) {
                logger.debug("no entries in a storage");
                bookDao.save(request);
            } else {
                List<Book> requestBooks = request.getBooks();
                List<Book> mainBooks = main.getBooks();

                List<Book> removableBooks = mainBooks.stream().filter(e -> !requestBooks.contains(e)).collect(Collectors.toList());
                mainBooks.removeAll(removableBooks);
                logger.debug(String.format("has been removed %d", removableBooks.size()));
                requestBooks.stream().forEach(rb -> {
                    mainBooks.stream().forEach(mb -> {
                        if (mb.equals(rb)) {
                            mb.update(rb);
                            logger.debug(String.format("has been updated book with id %s", rb.getId()));
                        }
                    });
                });
                List<Book> newBooks = requestBooks.stream().filter(e -> !mainBooks.contains(e)).collect(Collectors.toList());
                mainBooks.addAll(newBooks);
                logger.debug(String.format("has been added %d", newBooks.size()));
                bookDao.save(main);
            }
        }
        return findAll();
    }
}
