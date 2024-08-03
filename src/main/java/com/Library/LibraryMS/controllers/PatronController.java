package com.Library.LibraryMS.controllers;

import com.Library.LibraryMS.models.Book;
import com.Library.LibraryMS.models.Patron;
import com.Library.LibraryMS.services.BookService;
import com.Library.LibraryMS.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patrons")
public class PatronController {
    @Autowired
    PatronService patronService;
    @GetMapping
    public ResponseEntity<List<Patron>> getPatrons(){
        return  ResponseEntity.ok(patronService.getPatrons());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patron>getPatronById(@PathVariable Long id){
        return  new ResponseEntity<>(patronService.getPatronById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron){

        return new ResponseEntity<>(patronService.addPatron(patron),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Patron> updateBook(@RequestBody Patron patron,@PathVariable Long id)
    {
        return new ResponseEntity<>(patronService.updatePatron(patron, id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteBook(@PathVariable Long id){
        patronService.deletePatron(id);
        return new ResponseEntity<>("successfully deleted",HttpStatus.ACCEPTED);
    }

}
