package com.gmail.bogatyr.alexander.controller;

import com.gmail.bogatyr.alexander.LibraryApplication;
import com.gmail.bogatyr.alexander.entity.Book;
import com.gmail.bogatyr.alexander.entity.Catalog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexander on 12.02.16.
 */
//TODO will be refactored in future
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LibraryApplication.class)
public class BookControllerTest {

    @Autowired
    private BookController bookController;

    private Catalog catalog = new Catalog();

    @Before
    public void setUp() {
        @SuppressWarnings("unchecked")
        List<Book> books = new ArrayList(2);

        Book book = new Book();
        book.setId("1");
        book.setAuthor("author1");
        book.setTitle("title1");
        book.setGenre("genre1");
        book.setPrice(Float.parseFloat("1"));
        book.setPublishDate(new Date());
        book.setDescription("description1");
        books.add(book);

        Book book1 = new Book();
        book1.setId("2");
        book1.setAuthor("author2");
        book1.setTitle("title2");
        book1.setGenre("genre2");
        book1.setPrice(Float.parseFloat("2"));
        book1.setPublishDate(new Date());
        book1.setDescription("description2");
        books.add(book1);

        catalog.setBooks(books);
    }

    @Test
    public void testSave() {
        Book book = new Book();
        book.setId("3");
        book.setAuthor("author3");
        book.setTitle("title3");
        book.setGenre("genre3");
        book.setPrice(Float.parseFloat("3"));
        book.setPublishDate(new Date());
        book.setDescription("description3");
        this.catalog.getBooks().add(book);

        Catalog catalog = bookController.changeBook(this.catalog);
        Assert.assertNotNull(catalog);
        Assert.assertNotEquals(0, catalog.getBooks().size());
        long count = catalog.getBooks().stream().filter(e -> e.getId().equals(book.getId())).count();
        Assert.assertEquals(1L, count);
    }

    @Test
    public void testFind() {
        Catalog catalog = bookController.changeBook(null);
        Assert.assertNotNull(catalog);
        Assert.assertNotEquals(0, catalog.getBooks().size());
    }

    @Test
    public void testUpdate() {
        Catalog catalog = bookController.changeBook(null);
        Book book = catalog.getBooks().get(1);
        Assert.assertEquals("2", book.getId());
        Assert.assertEquals("author2", book.getAuthor());
        Assert.assertEquals("title2", book.getTitle());
        Assert.assertEquals("genre2", book.getGenre());

        List<Book> books = this.catalog.getBooks();
        Book newBook = books.get(1);
        newBook.setAuthor("author2-upd");
        newBook.setTitle("title2-upd");
        newBook.setGenre("genre2-upd");

        catalog = bookController.changeBook(this.catalog);
        book = catalog.getBooks().get(1);
        Assert.assertEquals("2", book.getId());
        Assert.assertEquals("author2-upd", book.getAuthor());
        Assert.assertEquals("title2-upd", book.getTitle());
        Assert.assertEquals("genre2-upd", book.getGenre());
    }

    @Test
    public void testDelete() {
        Catalog catalog = bookController.changeBook(this.catalog);
        Assert.assertNotNull(catalog);
        Assert.assertNotEquals(0, catalog.getBooks().size());
        Assert.assertEquals(2, catalog.getBooks().size());
    }
}
