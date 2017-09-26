package com.gamble.products_and_categories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.products_and_categories.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{

	List<Category> findByNameNotIn(List<String> names);

}
