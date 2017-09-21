package com.gamble.drivers_license.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.drivers_license.models.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

//	    List<Person> findByArtistContaining(String search);
//	    Long countByTitleContaining(String search);
//	    Long deleteByTitleStartingWith(String search);
//	    List<Song> findTop10ByOrderByRatingDesc();

	}


