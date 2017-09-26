package com.gamble.products_and_categories.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamble.products_and_categories.models.Category;
import com.gamble.products_and_categories.repositories.CategoryRepository;

@Service
public class CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}

	public List<Category> allCategories() {
		// TODO Auto-generated method stub
		return (List<Category>) categoryRepository.findAll();
	}

	public List<Category> findByNameNotIn(List<String> names) {
		// TODO Auto-generated method stub
		return (List<Category>) categoryRepository.findByNameNotIn(names);
	}

	public Category findCategoryById(long category_id) {
		// TODO Auto-generated method stub
		return categoryRepository.findOne(category_id);
	}

	public void updateCategory(Category category) {
		categoryRepository.save(category);		
	}
}
