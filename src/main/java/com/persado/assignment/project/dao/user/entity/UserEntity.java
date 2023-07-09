package com.persado.assignment.project.dao.user.entity;

import com.persado.assignment.project.dao.loan.entity.LoanEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String firstName;

    @NotBlank
    @Size(max = 255)
    private String lastName;

    @NotBlank
    private String address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LoanEntity> loans = new ArrayList<>();
}