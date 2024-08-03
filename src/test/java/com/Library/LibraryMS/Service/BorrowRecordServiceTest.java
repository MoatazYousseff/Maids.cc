package com.Library.LibraryMS.Service;

import com.Library.LibraryMS.models.Book;
import com.Library.LibraryMS.models.BorrowRecord;
import com.Library.LibraryMS.models.Patron;
import com.Library.LibraryMS.repos.BookRepo;
import com.Library.LibraryMS.repos.BorrowRecordRepo;
import com.Library.LibraryMS.repos.PatronRepo;
import com.Library.LibraryMS.services.BorrowRecordService;
import com.Library.LibraryMS.services.PatronService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)

public class BorrowRecordServiceTest {
    @InjectMocks
    BorrowRecordService borrowRecordService;


    @Mock
    BorrowRecordRepo borrowRecordRepo;
    @Mock
    PatronRepo patronRepo;
    @Mock
    BookRepo bookRepo;
    Book book=new Book();
    Patron patron=new Patron();
    BorrowRecord borrowRecord=new BorrowRecord();
    @BeforeEach
    public void setValues(){
        book.setBookId(2L);
        book.setAuthor("jack");
        book.setIsbn(123456789L);
        book.setTitle("the secret");
        book.setPubYear(2021);
        patron.setPatronId(3L);
        patron.setName("jack");
        patron.setPhone(123456789L);
        patron.setEmail("thecret@gmail.com");
        borrowRecord.setId(1L);
        borrowRecord.setBook(book);
        borrowRecord.setPatron(patron);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecord.setDueDate(LocalDate.now().plusDays(7));
    }

    @Test
    @DisplayName("Borrow book")
    public void borrowBook(){

        Mockito.when(bookRepo.findById(book.getBookId())).thenReturn(Optional.of(book));
        Mockito.when(patronRepo.findById(patron.getPatronId())).thenReturn(Optional.of(patron));
        Mockito.when(borrowRecordRepo.save(Mockito.any(BorrowRecord.class))).thenReturn(borrowRecord);
        BorrowRecord result=borrowRecordService.borrowBook(book.getBookId(), patron.getPatronId());
        Assert.assertNotNull(result);
        Assert.assertEquals("the secret",result.getBook().getTitle());
    }
    @Test
    @DisplayName("return book throws an exception when inserting null values")
    public void returnBookNullValues() {
        Assert.assertThrows(Exception.class,()->{borrowRecordService.editRecord(null,null);});
    }
}
