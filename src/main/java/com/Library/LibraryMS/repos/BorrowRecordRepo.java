package com.Library.LibraryMS.repos;

import com.Library.LibraryMS.models.Book;
import com.Library.LibraryMS.models.BorrowRecord;
import com.Library.LibraryMS.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowRecordRepo extends JpaRepository<BorrowRecord,Long> {

    BorrowRecord findByBookAndPatron (Book book, Patron patron);
    Optional<BorrowRecord> findByBookAndReturned(Book book, Boolean returned);
}
