package com.persado.assignment.project.service.book;

import com.persado.assignment.project.dao.book.entity.BookEntity;
import com.persado.assignment.project.service.exception.book.BookOnLoanException;

import java.util.List;

public interface BookService {
    List<BookEntity> getAllBooks();
    void deleteBook(Long id) throws BookOnLoanException;
    List<BookEntity> getAllAvailableBooks();
    void loanBook(Long bookId, Long userId);
    List<BookEntity> findAllLoanedBooks();

    void returnBook(Long bookId, Long userId);
}
