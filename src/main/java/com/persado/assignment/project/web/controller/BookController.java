package com.persado.assignment.project.web.controller;

import com.persado.assignment.project.dao.book.entity.BookEntity;
import com.persado.assignment.project.dao.book.repo.BookRepository;
import com.persado.assignment.project.service.book.BookService;
import com.persado.assignment.project.service.book.impl.DbBookService;
import com.persado.assignment.project.service.exception.book.BookOnLoanException;
import com.persado.assignment.project.service.exception.user.UserHasBooksException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService dbBookService;

    @GetMapping("/newBook")
    public String showNewBookPage(Model model) {
        model.addAttribute("book", new BookEntity());
        return "newBook";
    }

    @PostMapping("/newBook")
    public String createNewBook(BookEntity book) {
        bookRepository.save(book);
        return "redirect:/";  // redirects to the home page after creating the book
    }

    @GetMapping("/manageBooks")
    public String manageBooks(Model model) {
        model.addAttribute("books", dbBookService.getAllBooks());
        return "manageBooks";
    }

    @PostMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Long id, Model model) {
        try {
            dbBookService.deleteBook(id);
        } catch (BookOnLoanException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return "redirect:/manageBooks";
    }
}
