package com.Library.LibraryMS.controllers;

import com.Library.LibraryMS.models.Book;
import com.Library.LibraryMS.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping
    public ResponseEntity<List<Book>> getBooks(){
        return  ResponseEntity.ok(bookService.getBooks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book>getBookById(@PathVariable Long id){
        return  new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){

        return new ResponseEntity<>(bookService.addBook(book),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable Long id)
    {
        return new ResponseEntity<>(bookService.updateBook(book, id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return new ResponseEntity<>("successfully deleted",HttpStatus.ACCEPTED);
    }
}
