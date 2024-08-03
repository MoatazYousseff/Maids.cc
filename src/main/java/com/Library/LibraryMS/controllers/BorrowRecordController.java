package com.Library.LibraryMS.controllers;

import com.Library.LibraryMS.models.BorrowRecord;
import com.Library.LibraryMS.models.LogInInfo;
import com.Library.LibraryMS.services.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class BorrowRecordController {
@Autowired
    BorrowRecordService borrowRecordService;
    @PostMapping("borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId, @RequestBody LogInInfo logInInfo){
        return new ResponseEntity<>(borrowRecordService.borrowBook(bookId,patronId,logInInfo), HttpStatus.CREATED);
    }
    @PutMapping("return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowRecord> returnBook(@PathVariable Long bookId,@PathVariable Long patronId,@RequestBody LogInInfo logInInfo){
        return new ResponseEntity<>(borrowRecordService.editRecord(bookId,patronId,logInInfo), HttpStatus.OK);
    }
}
