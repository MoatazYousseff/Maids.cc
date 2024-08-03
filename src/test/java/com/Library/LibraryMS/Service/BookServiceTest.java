package com.Library.LibraryMS.Service;
import com.Library.LibraryMS.models.Book;
import com.Library.LibraryMS.repos.BookRepo;
import com.Library.LibraryMS.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)

public class BookServiceTest {
    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepo bookRepo;
    @Mock
    private Book book1=new Book();
    private Book book2=new Book();

    @Test
    @DisplayName("get all books")
    public void testGetAllBooks(){
        List<Book>books=new ArrayList<>();
        books.add(book1);
        books.add(book2);
        Mockito.when(bookRepo.findAll()).thenReturn(books);
        List<Book>result=bookService.getBooks();
        Assert.assertNotNull(result);
        Assert.assertEquals(2,result.size());
    }
    @Test
    @DisplayName("Delete book")
    public void testDeleteBook(){

        Mockito.when(bookRepo.findById(book1.getBookId())).thenReturn(Optional.of(book1));
        bookService.deleteBook(book1.getBookId());
        Mockito.verify(bookRepo,Mockito.times(1)).deleteById(book1.getBookId());
    }
    Book book=new Book();

    @BeforeEach
    public void setValues(){
        book.setBookId(1L);
        book.setAuthor("jack");
        book.setIsbn(123456789L);
        book.setTitle("the secret");
        book.setPubYear(2021);

    }
    @Test
    @DisplayName("Add book")
    public void testAddBook(){

        Mockito.when(bookRepo.save(Mockito.any(Book.class))).thenReturn(book);
        Book result=bookService.addBook(book);
        Assert.assertNotNull(result);
        Assert.assertEquals("the secret",result.getTitle());
    }
    @Test
    @DisplayName("Edit book")
    public void testEditBook(){
        Mockito.when(bookRepo.findById(book.getBookId())).thenReturn(Optional.of(book));
        Book editedBook=new Book();
        editedBook.setPubYear(2005);
        editedBook.setIsbn(512346789L);
        editedBook.setTitle("mufasa");
        editedBook.setAuthor("grace");
        editedBook.setBookId(1L);
        Mockito.when(bookRepo.save(editedBook)).thenReturn(editedBook);
        Book result=bookService.updateBook(editedBook,editedBook.getBookId());
        Assert.assertNotNull(result);
        Assert.assertEquals("mufasa",result.getTitle());
    }
}
