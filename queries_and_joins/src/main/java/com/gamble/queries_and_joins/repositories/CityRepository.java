package com.gamble.queries_and_joins.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.queries_and_joins.models.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long>{

}
