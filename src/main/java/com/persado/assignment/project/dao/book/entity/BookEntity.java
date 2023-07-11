package com.persado.assignment.project.dao.book.entity;

import com.persado.assignment.project.dao.loan.entity.LoanEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Lob
    private String summary;

    @NotBlank
    @Size(max = 255)
    private String isbn;

    private int copiesPurchased;

    private int copiesAvailable;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<LoanEntity> loans;
}
