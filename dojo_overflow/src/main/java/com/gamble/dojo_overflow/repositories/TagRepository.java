package com.gamble.dojo_overflow.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.dojo_overflow.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long>{

	Tag findTagBySubject(String subject);
	
}
