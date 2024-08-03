package com.Library.LibraryMS.models;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Table(name = "BorrowingRecord")
@Entity
public class BorrowRecord {
    @ManyToOne
    @JoinColumn(name = "bookId",referencedColumnName = "bookId",nullable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "patronId",referencedColumnName = "patronId",nullable = false)
    private Patron patron;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate borrowDate;

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    private LocalDate dueDate;
    private LocalDate returnDate;

    public BorrowRecord() {
    }

    @NotNull
    private Boolean returned = false;

    public BorrowRecord(Book book, Patron patron, LocalDate borrowDate, LocalDate dueDate) {
        this.book = book;
        this.patron = patron;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }



    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

}
