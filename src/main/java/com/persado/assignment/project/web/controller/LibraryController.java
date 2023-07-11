package com.persado.assignment.project.web.controller;

import com.persado.assignment.project.dao.book.entity.BookEntity;
import com.persado.assignment.project.service.book.BookService;
import com.persado.assignment.project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LibraryController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping("/returnBook")
    public String showReturnBookPage(Model model) {
        List<BookEntity> loanedBooks = bookService.findAllLoanedBooks();
        model.addAttribute("loanedBooks", loanedBooks);
        return "returnBook";
    }

    @PostMapping("/returnBook/{id}")
    public String registerReturn(@PathVariable Long id, @RequestParam Long userId) {
        bookService.returnBook(id, userId);
        return "redirect:/returnBook";
    }
}
