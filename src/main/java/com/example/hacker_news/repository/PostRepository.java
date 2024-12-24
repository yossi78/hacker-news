package com.example.hacker_news.repository;
import com.example.hacker_news.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post, Long> {
}
