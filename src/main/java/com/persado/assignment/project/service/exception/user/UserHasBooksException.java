package com.persado.assignment.project.service.exception.user;

import com.persado.assignment.project.dao.book.entity.BookEntity;

import java.util.List;

public class UserHasBooksException extends Exception {
    private List<BookEntity> books;

    public UserHasBooksException(String message, List<BookEntity> books) {
        super(message);
        this.books = books;
    }

    public List<BookEntity> getBooks() {
        return books;
    }
}