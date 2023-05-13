package com.videolibrary.backend.infrastructure.sql.repository;

import com.videolibrary.backend.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> { }
