package com.persado.assignment.project.dao.book.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
}
