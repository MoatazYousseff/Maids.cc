package com.Library.LibraryMS.repos;

import com.Library.LibraryMS.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
    Optional<Book> findByIsbnOrTitle(Long isbn, String title);

}
