package com.persado.assignment.project.dao.user.repo;

import com.persado.assignment.project.dao.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
