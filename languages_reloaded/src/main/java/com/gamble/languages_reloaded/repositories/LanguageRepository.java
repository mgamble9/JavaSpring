package com.gamble.languages_reloaded.repositories;
import com.gamble.languages_reloaded.models.Languages;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<Languages, Long> {
}
