package com.gamble.products_and_categories.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamble.products_and_categories.models.Category;
import com.gamble.products_and_categories.models.Product;
import com.gamble.products_and_categories.repositories.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public void addProduct(Product product) {
		productRepository.save(product);
	}
	
	public void updateProduct(Product product) {
		productRepository.save(product);
	}

    public Product findProductById(Long id) {
        return productRepository.findOne(id);
    }

	public List<Product> findByNameNotIn(List<String> names) {
		// TODO Auto-generated method stub
		return (List<Product>) productRepository.findByNameNotIn(names);
	}

}
