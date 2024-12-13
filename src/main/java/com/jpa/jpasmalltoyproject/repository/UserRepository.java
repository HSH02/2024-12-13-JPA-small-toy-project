package com.jpa.jpasmalltoyproject.repository;

import com.jpa.jpasmalltoyproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
