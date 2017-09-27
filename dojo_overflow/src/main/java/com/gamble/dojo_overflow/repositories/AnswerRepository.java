package com.gamble.dojo_overflow.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.dojo_overflow.models.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long>{
	
}
