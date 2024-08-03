package com.Library.LibraryMS.services;

import com.Library.LibraryMS.Exceptions.AlreadyExistsException;
import com.Library.LibraryMS.Exceptions.InvalidInputException;
import com.Library.LibraryMS.Exceptions.NotFoundException;
import com.Library.LibraryMS.models.Book;
import com.Library.LibraryMS.models.Patron;
import com.Library.LibraryMS.repos.BookRepo;
import com.Library.LibraryMS.repos.PatronRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PatronService {
    @Autowired
    PatronRepo patronRepo;
    public List<Patron> getPatrons(){
        return patronRepo.findAll();
    }
    @Cacheable("patronById")
    public  Patron getPatronById(Long id){
        Optional<Patron> returnedPatron=patronRepo.findById(id);
        if(returnedPatron.isPresent())
        {
            return returnedPatron.get();
        }
        else {
            throw new NotFoundException("Patron not found");
        }
    }
    @Transactional
    @CachePut("patrons")

    public Patron addPatron(Patron patron){
        if(validatePatron(patron)) {
            if(checkPatronExistence(patron))
            {
                throw new AlreadyExistsException("This patron already exists");
            }
            return patronRepo.save(patron);
        }
        throw new InvalidInputException("Invalid input");
    }
    @Transactional
    @CachePut("patrons")
    public Patron updatePatron(Patron patron, Long id){
        if (id==patron.getPatronId()) {
            Optional<Patron> oldPatron = patronRepo.findById(id);
            if (oldPatron.isPresent()) {
                if(validatePatron(patron)) {
                    return patronRepo.save(patron);
                }
                throw new InvalidInputException("Invalid input");
            } else {
                throw new NotFoundException("Patron not found");
            }
        }
        else {
            throw new InvalidInputException("Ensure that id in url and body match");
        }
    }
    @CacheEvict("patrons")
    public void deletePatron(Long id){
        if(patronRepo.findById(id).isPresent()) {
            patronRepo.deleteById(id);
        }
        else {
            throw new NotFoundException("Patron not found");

        }
    }
    public Boolean validatePatron(Patron patron){
    if(patron.getEmail().isBlank()||patron.getName().isBlank()||patron.getPhone()==0){
            return false;
        }
        return true;
    }
    public Boolean checkPatronExistence(Patron patron){
        Optional<Patron> existedPatron=patronRepo.findByEmailOrPhone(patron.getEmail(),patron.getPhone());
        if(existedPatron.isPresent()){
            return true;
        }
        return false;
    }
}
