package com.gamble.bnb.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.bnb.models.Listing;

@Repository
public interface ListingRepository extends CrudRepository<Listing, Long> {

	List<Listing> findByAddressContaining(String search_str);

}
