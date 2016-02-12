package com.gmail.bogatyr.alexander.entity;

import com.gmail.bogatyr.alexander.adapter.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * Created by alexander on 11.02.16.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    @XmlAttribute
    public String id;
    @XmlElement
    public String author;
    @XmlElement
    public String title;
    @XmlElement
    public String genre;
    @XmlElement
    public Float price;
    @XmlElement(name = "publish_date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date publishDate;
    @XmlElement
    public String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void update(Book book) {
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.genre = book.getGenre();
        this.price = book.getPrice();
        this.publishDate = book.getPublishDate();
        this.description = book.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id.equals(book.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id='").append(id).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", price=").append(price);
        sb.append(", publishDate=").append(publishDate);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
