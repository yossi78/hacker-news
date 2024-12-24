package com.example.hacker_news.service;
import com.example.hacker_news.exception.ResourceNotFoundException;
import com.example.hacker_news.model.Post;
import com.example.hacker_news.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
public class PostService {


    private final PostRepository postRepository;


    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public Post createPost(Post post) {
        return postRepository.save(post);
    }


    public Post getPost(Long postId) {
        Post post = checkPostExistance(postId);
        return post;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        if(posts==null){
            log.error("The posts have not been found");
            throw new ResourceNotFoundException("The posts have not been found");
        }
        log.info("Retrieved all posts, total count: " + posts.size());
        return posts;
    }



    public void deletePost(Long postId) {
        checkPostExistance(postId);
        postRepository.deleteById(postId);
    }


    public Post updatePost(Long postId, Post updatedPost) {
        checkPostExistance(postId);
        updatedPost.setId(postId);
        return postRepository.save(updatedPost);
    }


    private Post checkPostExistance(Long postId){
        Post post =  postRepository.findById(postId).orElse(null);
        if(post==null){
            log.error("The post has not been found , postId="+postId);
            throw new ResourceNotFoundException("The post has not been found");
        }
        log.info("Find post by id: " + postId);
        return post;
    }





}
