package com.studenttrack.controller;

import com.studenttrack.model.Book;
import com.studenttrack.model.Borrow;
import com.studenttrack.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(libraryService.getAllBooks());
    }

    @PostMapping("/borrow")
    public ResponseEntity<Borrow> borrowBook(@RequestParam Long studentId, @RequestParam Long bookId) {
        return ResponseEntity.ok(libraryService.borrowBook(studentId, bookId));
    }

    @PostMapping("/return/{borrowId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long borrowId) {
        libraryService.returnBook(borrowId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/borrowed/{studentId}")
    public ResponseEntity<List<Borrow>> getBorrowedBooks(@PathVariable Long studentId) {
        return ResponseEntity.ok(libraryService.getBorrowedBooks(studentId));
    }
}
