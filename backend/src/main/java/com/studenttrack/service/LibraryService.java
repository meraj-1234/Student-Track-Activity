package com.studenttrack.service;

import com.studenttrack.model.Book;
import com.studenttrack.model.Borrow;
import com.studenttrack.repository.BookRepository;
import com.studenttrack.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Borrow borrowBook(Long studentId, Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        if (!book.isAvailability()) {
            throw new RuntimeException("Book not available");
        }
        
        book.setAvailability(false);
        bookRepository.save(book);

        Borrow borrow = Borrow.builder()
                .studentId(studentId)
                .bookId(bookId)
                .issueDate(LocalDate.now())
                .build();
        
        return borrowRepository.save(borrow);
    }

    public void returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId).orElseThrow();
        borrow.setReturnDate(LocalDate.now());
        borrowRepository.save(borrow);

        Book book = bookRepository.findById(borrow.getBookId()).orElseThrow();
        book.setAvailability(true);
        bookRepository.save(book);
    }

    public List<Borrow> getBorrowedBooks(Long studentId) {
        return borrowRepository.findByStudentId(studentId);
    }
}
