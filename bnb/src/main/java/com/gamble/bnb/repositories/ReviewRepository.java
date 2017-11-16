package com.gamble.bnb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.bnb.models.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

}
