package com.gmail.bogatyr.alexander.entity;

import org.springframework.util.CollectionUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by alexander on 11.02.16.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalog {

    @XmlElement(name = "book")
    public List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(books);
    }
}
