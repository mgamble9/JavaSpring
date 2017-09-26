package com.gamble.products_and_categories.controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gamble.products_and_categories.models.Category;
import com.gamble.products_and_categories.models.Product;
import com.gamble.products_and_categories.services.CategoryService;
import com.gamble.products_and_categories.services.ProductService;

@Controller
public class ProductsAndCategoriesCtrl {
	
	private final ProductService productService;
	private final CategoryService categoryService;
	
    public ProductsAndCategoriesCtrl(ProductService productService,
    									CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }

	
	@RequestMapping("/")
	public String home() {
		return "/WEB-INF/views/index.jsp";
	}
	
    @RequestMapping("/products/new")
    public String newProduct(@ModelAttribute("product") Product product) {
        return "/WEB-INF/views/product.jsp";
    }
    
    @PostMapping("/products/new")
    public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "/WEB-INF/views/product.jsp";
        }else{
        		float price_input = product.getPrice();
        		System.out.println("Price Input: " + price_input);
        		String price_string = new DecimalFormat("0.00").format(price_input);
        		product.setPrice(Float.parseFloat(price_string));
            productService.addProduct(product);
            return "redirect:/";
        }
    }

    @RequestMapping("/categories/new")
    public String newCategory(@ModelAttribute("category") Category category) {
    		return "/WEB-INF/views/category.jsp";
    }
    
    @PostMapping("/categories/new")
    public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "/WEB-INF/views/category.jsp";
        } else {
            categoryService.addCategory(category);
            return "redirect:/";
        }
    }
    
	@RequestMapping("/products/{id}")
    public String editProduct(@PathVariable("id") int id, Model model) {
    		Product product = productService.findProductById((long) id);
    		if (product != null) {
	    		List<String> categories_to_ignore_list = new ArrayList<String>();
	    		List<Category> categories_available_list = new ArrayList<Category>();
	    		List<Category> product_category_list = product.getCategories();
	    		for (Category cat: product_category_list) {
	    			categories_to_ignore_list.add(cat.getName());
	    			System.out.println("added name: " + cat.getName());
	    		}	    		
	    		model.addAttribute("product", product);
	    		System.out.println("unavailable categories: " + categories_to_ignore_list);
	    		if (categories_to_ignore_list.isEmpty()) {
	    			categories_available_list = categoryService.allCategories();
	    		} else {
	    			categories_available_list =  categoryService.findByNameNotIn(categories_to_ignore_list);
	    		}
	    		System.out.println("available categories: " + categories_available_list);
	    		model.addAttribute("category_list", categories_available_list);
	    		return "/WEB-INF/views/product_edit.jsp";
    		} else {
    			return "redirect:/";
    		}   		
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable("id") int id,
    								@RequestParam(value="category_id",required=true) long category_id) {
			//System.out.println("id of category to add is: " + category_id);
			//System.out.println("id of product to update is: " + id);
			Product product = productService.findProductById((long) id);
			List<Category> category_list = product.getCategories();
			Category category = categoryService.findCategoryById(category_id);
			//System.out.println("category to add : " + category.getName());
			category_list.add(category);
			product.setCategories(category_list);
    			productService.updateProduct(product);      
            return "redirect:/products/" + id;
        
    }

    @RequestMapping("/categories/{id}")
    public String editCategory(@PathVariable("id") int id, Model model) {
    		Category category = categoryService.findCategoryById((long) id);
    		if (category != null) {
	    		List<String> products_to_ignore_list = new ArrayList<String>();
	    		List<Product> category_product_list = category.getProducts();
	    		for (Product prod: category_product_list) {
	    			products_to_ignore_list.add(prod.getName());
	    			System.out.println("added name: " + prod.getName());
	    		}	    		
	    		model.addAttribute("category", category);
	    		List<Product> products_available_list =  productService.findByNameNotIn(products_to_ignore_list);

	    		model.addAttribute("product_list", products_available_list);
	    		return "/WEB-INF/views/category_edit.jsp";
    		} else {
    			return "redirect:/";
    		}   		
    }
    
    @PostMapping("/categories/{id}")
    public String updateCategory(@PathVariable("id") int id,
    								@RequestParam(value="product_id",required=true) long product_id) {
			//System.out.println("id of category to add is: " + category_id);
			//System.out.println("id of product to update is: " + id);
			Category category = categoryService.findCategoryById((long) id);
			List<Product> product_list = category.getProducts();
			Product product = productService.findProductById(product_id);
			//System.out.println("category to add : " + category.getName());
			product_list.add(product);
			category.setProducts(product_list);
    			categoryService.updateCategory(category);      
            return "redirect:/categories/" + id;
        
    }

    
}
