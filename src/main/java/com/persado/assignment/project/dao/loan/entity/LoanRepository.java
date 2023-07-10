package com.persado.assignment.project.dao.loan.entity;

import com.persado.assignment.project.dao.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    List<BookEntity> findBooksByUserId(Long userId);

    boolean existsByBookId(@NotNull Long bookId);
}
