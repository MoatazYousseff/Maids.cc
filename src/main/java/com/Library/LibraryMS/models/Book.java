package com.Library.LibraryMS.models;

import jakarta.persistence.*;
import org.springframework.cache.annotation.EnableCaching;

@Table(name = "book")
@Entity

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;
    private String author;
    private int pubYear;
    private  Long isbn;
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPubYear() {
        return pubYear;
    }

    public void setPubYear(int pubYear) {
        this.pubYear = pubYear;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }


    public Book(Long bookId, String title, String author, int pubYear, Long isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.pubYear = pubYear;
        this.isbn = isbn;
    }

    public Book() {
    }
}
