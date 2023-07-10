package com.persado.assignment.project.dao.book.repo;

import com.persado.assignment.project.dao.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
