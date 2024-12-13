package com.jpa.jpasmalltoyproject;

import com.jpa.jpasmalltoyproject.domain.Post;
import com.jpa.jpasmalltoyproject.domain.User;
import com.jpa.jpasmalltoyproject.repository.PostRepository;
import com.jpa.jpasmalltoyproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JpaSmallToyProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSavePost() {
        // User 엔티티 생성
        User user = User.builder()
                .username("testuser")
                .password("password")
                .build();

        userRepository.save(user); // User 저장

        // Post 엔티티 생성
        Post post = Post.builder()
                .title("My First Post")
                .content("This is the content of my first post.")
                .user(user) // 작성자 설정
                .build();

        postRepository.save(post); // Post 저장

        assertNotNull(post.getId());
    }

}
