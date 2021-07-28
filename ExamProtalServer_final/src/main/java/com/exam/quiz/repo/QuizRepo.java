package com.exam.quiz.repo;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.quiz.entity.Category;
import com.exam.quiz.entity.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Serializable>{

	public List<Quiz> findByCategory(Category category);
	
	public List<Quiz> findByActive(Boolean b);
	
	public List<Quiz> findByCategoryAndActive(Category category,Boolean b);
	
}
