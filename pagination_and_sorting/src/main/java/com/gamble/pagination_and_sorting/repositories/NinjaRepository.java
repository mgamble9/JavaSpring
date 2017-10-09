package com.gamble.pagination_and_sorting.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.gamble.pagination_and_sorting.models.Ninja;

public interface NinjaRepository extends PagingAndSortingRepository<Ninja, Long>{
}
