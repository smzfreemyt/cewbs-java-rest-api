package com.cewb.app.service;

import com.cewb.app.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {

    Page<Post> findAll(int pageNum);

    Post findById(Long id);

    Post save(Post post);

    Post delete(Long id);

    List<Post> findByCategory(long categoryId);

    List<Post> findAll();
}
