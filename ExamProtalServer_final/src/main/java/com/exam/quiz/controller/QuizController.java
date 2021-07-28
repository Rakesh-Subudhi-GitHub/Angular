package com.exam.quiz.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.exam.quiz.entity.Quiz;
import com.exam.quiz.service.IQuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private IQuizService quizService;
	
	//add quiz
	@PostMapping("/")
	public Quiz addQuiz(@RequestBody Quiz quiz)
	{
		System.out.println("body:: "+quiz);
		return quizService.addQuiz(quiz);
	}
	
	//update Quiz
	@PutMapping("/")
	public Quiz updateQuiz(@RequestBody Quiz quiz)
	{
		System.out.println("body:: "+quiz);
		return quizService.updateQuiz(quiz);
	}
	
	//all quiz
	@GetMapping("/")
	public Set<Quiz> getAllQuizs()
	{
		System.out.println("value print or not check ........");
		System.out.println(quizService.listQuizes());
		return quizService.listQuizes();
	}
	
	//getQuiz
	@GetMapping("/{quizId}")
	public Quiz getQuiz(@PathVariable Integer quizId)
	{
		return quizService.getQuiz(quizId);
	}
	
	//delete quiz
	@DeleteMapping("/{quizId}")
	public void deleteQuiz(@PathVariable Integer quizId)
	{
	   quizService.deletQuiz(quizId);
	}
	
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesOfCategory(@PathVariable Integer cid)
	{
		Category category = new Category();
		category.setCid(cid);
		
		return quizService.getActiveQuizeOfCategory(category);
	}
	
	//get active quizzes
	@GetMapping("/active")
	public List<Quiz> getActiveQuizes()
	{
		return quizService.getActiveQuizes();
	}

	//get Active Quizzes of Category
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizesOfCategory(@PathVariable Integer cid)
	{
		Category category = new Category();
		category.setCid(cid);
		
		return quizService.getActiveQuizeOfCategory(category);
		
	}
	
	
}//class
