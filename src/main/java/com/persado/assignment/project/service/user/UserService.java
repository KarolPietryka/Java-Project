package com.persado.assignment.project.service.user;

import com.persado.assignment.project.dao.book.entity.BookEntity;
import com.persado.assignment.project.dao.user.entity.UserEntity;
import com.persado.assignment.project.service.exception.user.UserHasBooksException;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers();

    void deleteUser(Long id) throws UserHasBooksException;
}
