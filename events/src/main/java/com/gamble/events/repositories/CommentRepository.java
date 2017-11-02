package com.gamble.events.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.events.models.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{


}
