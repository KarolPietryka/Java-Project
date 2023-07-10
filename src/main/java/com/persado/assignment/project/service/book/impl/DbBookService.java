package com.persado.assignment.project.service.book.impl;

import com.persado.assignment.project.dao.book.entity.BookEntity;
import com.persado.assignment.project.dao.book.repo.BookRepository;
import com.persado.assignment.project.dao.loan.entity.LoanRepository;
import com.persado.assignment.project.service.book.BookService;
import com.persado.assignment.project.service.exception.book.BookOnLoanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbBookService implements BookService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    @Autowired
    public DbBookService(BookRepository bookRepository, LoanRepository loanRepository){
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
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
}
