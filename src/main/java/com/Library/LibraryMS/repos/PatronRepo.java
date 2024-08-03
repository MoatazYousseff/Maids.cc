package com.Library.LibraryMS.repos;

import com.Library.LibraryMS.models.Book;
import com.Library.LibraryMS.models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronRepo extends JpaRepository<Patron,Long> {
    Optional<Patron> findByEmailOrPhone(String email, Long phone);

}
