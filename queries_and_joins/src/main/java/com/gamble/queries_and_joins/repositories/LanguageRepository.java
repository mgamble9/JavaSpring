package com.gamble.queries_and_joins.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.queries_and_joins.models.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Long>{
	
}
