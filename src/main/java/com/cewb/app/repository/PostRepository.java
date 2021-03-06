package com.cewb.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cewb.app.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
