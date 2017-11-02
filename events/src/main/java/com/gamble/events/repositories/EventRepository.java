package com.gamble.events.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.events.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long>{

	@Query("SELECT e FROM Event e WHERE e.state = ?1 ORDER BY e.event_date ASC")
	List<Event> findByState(String state);
	
	@Query("SELECT e FROM Event e WHERE e.state != ?1 ORDER BY e.event_date ASC")
	List<Event> findAllNotInState(String state);
}
