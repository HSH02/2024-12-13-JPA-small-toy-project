package com.jpa.jpasmalltoyproject.repository;

import com.jpa.jpasmalltoyproject.domain.Post;
import com.jpa.jpasmalltoyproject.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional          // 각 테스트 별로 트랙잭션을 롤백하여 DB 초기화
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateAndSavePost(){
        // Given
        User user = User.builder()
                .username("testuser")
                .password("password")
                .build();
        userRepository.save(user);

        Post post = Post.builder()
                .title("Test Post")
                .content("This is a test post content.")
                .user(user)
                .build();

        // When
        Post savedPost = postRepository.save(post);

        // Then         assertNotNull;
        assertNotNull(savedPost.getId());       // ID가 생성되었는지 확인
        assertEquals("Test Post", savedPost.getTitle());
        assertEquals(user, savedPost.getUser());
    }

    @Test
    void testUpdatePost() {
        // Given
        User user = createTestUser();
        Post post = Post.builder()
                .title("Initial Title")
                .content("Initial Content")
                .user(user)
                .build();
        postRepository.save(post);

        // When
        post.setTitle("Updated Title");
        post.setContent("Updated Content");
        postRepository.save(post);

        // Then
        Post updatedPost = postRepository.findById(post.getId()).orElseThrow();
        assertEquals("Updated Title", updatedPost.getTitle());
        assertEquals("Updated Content", updatedPost.getContent());
    }

    @Test
    void testDeletePost() {
        // Given
        User user = createTestUser();
        Post post = Post.builder()
                .title("Post to be deleted")
                .content("Content of the post to be deleted")
                .user(user)
                .build();
        postRepository.save(post);

        // When
        postRepository.delete(post);

        // Then
        Optional<Post> deletedPost = postRepository.findById(post.getId());
        assertTrue(deletedPost.isEmpty());
    }

    @Test
    void testRelationshipWithUser() {
        // Given
        User user = createTestUser();
        Post post1 = Post.builder()
                .title("First Post")
                .content("Content of first post")
                .user(user)
                .build();
        Post post2 = Post.builder()
                .title("Second Post")
                .content("Content of second post")
                .user(user)
                .build();

        postRepository.save(post1);
        postRepository.save(post2);

        // When
        List<Post> userPosts = postRepository.findAll();

        // Then
        assertEquals(2, userPosts.size());
        assertTrue(userPosts.stream().allMatch(p -> p.getUser().equals(user)));
    }

    private User createTestUser() {
        User user = User.builder()
                .username("testuser")
                .password("password")
                .build();
        return userRepository.save(user);
    }
}
