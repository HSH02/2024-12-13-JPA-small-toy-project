package com.jpa.jpasmalltoyproject;

import com.jpa.jpasmalltoyproject.domain.Post;
import com.jpa.jpasmalltoyproject.repository.PostRepository;
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

    @Test
    void testSavePost() {
        Post post = Post.builder()
                .title("My First Post")
                .content("This is the content of my first post.")
                .build();

        postRepository.save(post);

        assertNotNull(post.getId());
    }

}
