package com.persado.assignment.project.web.controller;

import com.persado.assignment.project.dao.book.entity.BookEntity;
import com.persado.assignment.project.dao.book.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

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
}
