package com.persado.assignment.project.dao.loan.entity;

import com.persado.assignment.project.dao.book.entity.BookEntity;
import com.persado.assignment.project.dao.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    LoanEntity findByBookAndUser(BookEntity book, UserEntity user);
    List<BookEntity> findBooksByUserId(Long userId);

    boolean existsByBookId(@NotNull Long bookId);
}
