package com.persado.assignment.project.service.user.impl;

import com.persado.assignment.project.dao.book.entity.BookEntity;
import com.persado.assignment.project.dao.loan.entity.LoanRepository;
import com.persado.assignment.project.dao.user.entity.UserEntity;
import com.persado.assignment.project.dao.user.repo.UserRepository;
import com.persado.assignment.project.service.exception.user.UserHasBooksException;
import com.persado.assignment.project.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbUserService implements UserService {

    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    @Autowired
    public DbUserService(UserRepository userRepository, LoanRepository loanRepository) {
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) throws UserHasBooksException {
        List<BookEntity> loanedBooks = loanRepository.findBooksByUserId(id);
        if (!loanedBooks.isEmpty()) {
            throw new UserHasBooksException("User has books on loan", loanedBooks);
        }
        userRepository.deleteById(id);
    }
}

