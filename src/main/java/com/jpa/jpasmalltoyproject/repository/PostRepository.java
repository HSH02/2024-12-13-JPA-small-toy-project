package com.jpa.jpasmalltoyproject.repository;

import com.jpa.jpasmalltoyproject.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
