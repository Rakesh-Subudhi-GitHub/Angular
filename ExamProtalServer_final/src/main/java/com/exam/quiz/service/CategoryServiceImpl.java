package com.exam.quiz.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.quiz.entity.Category;
import com.exam.quiz.repo.CategoryRepo;


@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	//add
	@Override
	public Category addCategory(Category category) {
		
		return categoryRepo.save(category);
	}

	//update
	@Override
	public Category updateCategory(Category category) {
	
		return categoryRepo.save(category);
	}

	//list
	@Override
	public Set<Category> listCategory() {
		
		return new LinkedHashSet<>(categoryRepo.findAll());
	}

	//get once
	@Override
	public Category getCategory(Integer categoryId) {
		
		return categoryRepo.findById(categoryId).get();
	}

	//delete
	@Override
	public void deleteCategory(Integer categoryId) {
		
		  Category category=new Category(); 
		  category.setCid(categoryId);
		  
		  // remove category directly
		  categoryRepo.delete(category);
		 
		
		//categoryRepo.deleteById(categoryId);

	}

	
}//class
