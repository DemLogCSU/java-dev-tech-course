package com.example.demoweb.repository;

import com.example.demoweb.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}