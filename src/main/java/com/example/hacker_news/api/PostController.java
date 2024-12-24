package com.example.hacker_news.api;
import com.example.hacker_news.exception.ResourceNotFoundException;
import com.example.hacker_news.model.Post;
import com.example.hacker_news.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/posts")
@Slf4j
public class PostController {
    private final PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        try {
            Post savedPost = postService.createPost(post);
            return new ResponseEntity<>(savedPost, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to save post to DB ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GetMapping(path = "/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable("postId") Long postId) {
        try {
            Post post = postService.getPost(postId);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Failed to get post from DB ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Failed to get all posts from DB", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Post updatedPost) {
        try {
            Post post = postService.updatePost(postId, updatedPost);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Failed to get post from DB ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId) {
        try {
            postService.deletePost(postId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Failed to get post from DB ");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }



    }


}
