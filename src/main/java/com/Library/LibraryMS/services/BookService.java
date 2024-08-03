package com.Library.LibraryMS.services;

import com.Library.LibraryMS.Exceptions.AlreadyExistsException;
import com.Library.LibraryMS.Exceptions.InvalidInputException;
import com.Library.LibraryMS.Exceptions.NotFoundException;
import com.Library.LibraryMS.models.Book;
import com.Library.LibraryMS.repos.BookRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
   @Autowired
    BookRepo bookRepo;
   public List<Book> getBooks(){
       return bookRepo.findAll();
   }
    @Cacheable("bookById")
   public  Book getBookById(Long id){
       Optional<Book> returnedBook=bookRepo.findById(id);
       if(returnedBook.isPresent())
       {
           return returnedBook.get();
       }
       else {
           throw new NotFoundException("Book not found");
       }
   }
    @Transactional
    @CachePut("books")
    public Book addBook(Book book){
       if(validateBook(book)) {

           if(checkBookExistence(book)){
               throw new AlreadyExistsException("The book already Exists");
           }
           return bookRepo.save(book);
       }
       throw new InvalidInputException("Invalid input");
   }
    @Transactional
    @CachePut("books")
   public Book updateBook(Book book, Long id){
       if (id==book.getBookId()) {
           Optional<Book> oldBook = bookRepo.findById(id);
           if (oldBook.isPresent()) {
               if(validateBook(book)) {
                   return bookRepo.save(book);
               }
               throw new InvalidInputException("Invalid input");
           } else {
               throw new NotFoundException("Book not found");
           }
       }
       else {
           throw new InvalidInputException("Ensure that id in url and body match");
       }
   }
   @CacheEvict("books")
   public void deleteBook(Long id){
       if(bookRepo.findById(id).isPresent()) {
           bookRepo.deleteById(id);
       }
       else {
           throw new NotFoundException("Book not found");

       }
   }
   public Boolean validateBook(Book book){
       if(book.getTitle().isBlank()||book.getAuthor().isBlank()||book.getIsbn()==0||book.getPubYear()==0){
           return false;
       }
       return true;
   }
   public Boolean checkBookExistence(Book book){
       Optional<Book> existedBook=bookRepo.findByIsbnOrTitle(book.getIsbn(),book.getTitle());
       if(existedBook.isPresent()){
           return true;
       }
       return false;
   }

}
