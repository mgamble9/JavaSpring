package com.gamble.products_and_categories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamble.products_and_categories.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

	List<Product> findByNameNotIn(List<String> names);


}
