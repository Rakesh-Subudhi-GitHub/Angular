package com.exam.quiz.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.quiz.entity.Category;
import com.exam.quiz.service.ICategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private ICategoryService catService;
	
	//add category
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		
		Category addCategory = this.catService.addCategory(category);
		return ResponseEntity.ok(addCategory);
	}
	
	//get AllCategory
	@GetMapping("/")
	public Set<Category> getCategories()
	{
		return catService.listCategory();
	}
	
	//get category
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable Integer categoryId){
		
		return catService.getCategory(categoryId);
	}
	
	//update
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category)
	{
		return catService.updateCategory(category);
	}
	
	//delete
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable Integer categoryId)
	{
		catService.deleteCategory(categoryId);
	}
	
}//class

