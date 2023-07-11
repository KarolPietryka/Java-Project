package com.persado.assignment.project.service.book.impl;

import com.persado.assignment.project.dao.book.entity.BookEntity;
import com.persado.assignment.project.dao.book.repo.BookRepository;
import com.persado.assignment.project.dao.loan.entity.LoanEntity;
import com.persado.assignment.project.dao.loan.entity.LoanRepository;
import com.persado.assignment.project.dao.user.entity.UserEntity;
import com.persado.assignment.project.dao.user.repo.UserRepository;
import com.persado.assignment.project.service.book.BookService;
import com.persado.assignment.project.service.exception.book.BookOnLoanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbBookService implements BookService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    @Autowired
    public DbBookService(
            BookRepository bookRepository,
            LoanRepository loanRepository,
            UserRepository userRepository
    ){
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBook(Long id) throws BookOnLoanException {
        boolean bookOnLoan = loanRepository.existsByBookId(id);
        if (bookOnLoan) {
            throw new BookOnLoanException("Book is currently on loan");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookEntity> getAllAvailableBooks() {
        return bookRepository.findByCopiesAvailableGreaterThan(0);
    }

    @Override
    public void loanBook(Long bookId, Long userId) {
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        //Return if already have loaned
        if(loanRepository.findByBookAndUser(book, user) != null){ return; }
        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        bookRepository.save(book);
        LoanEntity loan = new LoanEntity();
        loan.setBook(book);
        loan.setUser(user);
        loanRepository.save(loan);
    }

    @Override
    public List<BookEntity> findAllLoanedBooks() {
        return loanRepository.findAll().stream()
                .map(LoanEntity::getBook)
                .collect(Collectors.toList());
    }

    @Override
    public void returnBook(Long bookId, Long userId) {
        BookEntity book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + bookId));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + userId));

        // Now, we fetch the loan connecting the book and the user and delete it.
        LoanEntity loan = loanRepository.findByBookAndUser(book, user);
        if (loan == null) {
            throw new IllegalArgumentException("No loan found for given book id and user id");
        }

        loanRepository.delete(loan);
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);  // Increase the available copies of the book by one.
        bookRepository.save(book);
    }
}
