package com.persado.assignment.project.dao.loan.entity;

import com.persado.assignment.project.dao.book.entity.BookEntity;
import com.persado.assignment.project.dao.user.entity.UserEntity;
import lombok.Data;
import javax.persistence.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "loans", indexes = {
    @Index(name = "loan_user_index", columnList = "user_id"),
    @Index(name = "loan_book_index", columnList = "book_id"),
    @Index(name = "loan_loanedAt_index", columnList = "loanedAt")
})
public class LoanEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private BookEntity book;

    private LocalDateTime loanedAt;

    private LocalDateTime returnedAt;
}