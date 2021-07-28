package com.exam.quiz.service;

import java.util.Set;

import com.exam.quiz.entity.Category;

public interface ICategoryService {

	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Set<Category> listCategory();
	
	public Category getCategory(Integer categoryId);
	
	public void deleteCategory(Integer categoryId);
	
}
