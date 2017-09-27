package com.gamble.dojo_overflow.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.dojo_overflow.models.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long>{

	Question findById(int id);
	

}
