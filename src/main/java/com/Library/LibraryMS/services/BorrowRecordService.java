package com.Library.LibraryMS.services;

import com.Library.LibraryMS.Exceptions.AlreadyExistsException;
import com.Library.LibraryMS.Exceptions.InvalidInputException;
import com.Library.LibraryMS.Exceptions.NotFoundException;
import com.Library.LibraryMS.models.Book;
import com.Library.LibraryMS.models.BorrowRecord;
import com.Library.LibraryMS.models.LogInInfo;
import com.Library.LibraryMS.models.Patron;
import com.Library.LibraryMS.repos.BookRepo;
import com.Library.LibraryMS.repos.BorrowRecordRepo;
import com.Library.LibraryMS.repos.PatronRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class BorrowRecordService {
    @Autowired
    BorrowRecordRepo borrowRecordRepo;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    PatronRepo patronRepo;

    public BorrowRecord borrowBook(Long bookId, Long patronId, LogInInfo logInInfo){
        Optional<Book> borrowedBook=bookRepo.findById(bookId);
        Optional<Patron> borrower=patronRepo.findById(patronId);
        if(borrower.isPresent()&&borrowedBook.isPresent())
        {
            if(borrower.get().getPhone()!=logInInfo.getPhone()&&!borrower.get().getEmail().equals(logInInfo.getEmail()))
            {
                throw new InvalidInputException("Invalid email or phone for this patron");
            }
            if(borrowRecordRepo.findByBookAndReturned(borrowedBook.get(),false).isPresent()) {
            throw new AlreadyExistsException("This book is already borrowed");
            }

                BorrowRecord borrowRecord = new BorrowRecord(borrowedBook.get(), borrower.get(), LocalDate.now(), LocalDate.now().plusDays(7));

                return borrowRecordRepo.save(borrowRecord);

        }
        else {
            throw new NotFoundException("The book or the parton is not found");
        }
    }
    public BorrowRecord editRecord(Long bookId,Long patronId,LogInInfo logInInfo)
    {
        Optional<Book> borrowedBook=bookRepo.findById(bookId);
        Optional<Patron> borrower=patronRepo.findById(patronId);
        if(borrower.isPresent()&&borrowedBook.isPresent())
        {
            if(borrower.get().getPhone()!=logInInfo.getPhone()&&!borrower.get().getEmail().equals(logInInfo.getEmail()))
            {
                throw new InvalidInputException("Invalid email or phone for this patron");
            }
            if(!borrowRecordRepo.findByBookAndReturned(borrowedBook.get(),false).isPresent()) {
                throw new NotFoundException("Unable to do this action: no borrowing record for this book");
            }
            BorrowRecord borrowRecord= borrowRecordRepo.findByBookAndPatron(borrowedBook.get(),borrower.get());
            borrowRecord.setReturned(true);
            borrowRecord.setReturnDate(LocalDate.now());
            return borrowRecordRepo.save(borrowRecord);

        }
        else {
            throw new NotFoundException("The book or the parton is not found");
        }
    }
}
