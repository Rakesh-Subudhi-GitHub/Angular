package com.exam.quiz.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.quiz.entity.Category;
import com.exam.quiz.entity.Quiz;
import com.exam.quiz.repo.QuizRepo;

@Service
public class QuizServiceImpl implements IQuizService {

	@Autowired
	private QuizRepo quizRepo;
	
	//save
	@Override
	public Quiz addQuiz(Quiz quiz) {
		return quizRepo.save(quiz);
	}

	//update
	@Override
	public Quiz updateQuiz(Quiz quiz) {
		
		return quizRepo.save(quiz);
	}

	//list of select
	@Override
	public Set<Quiz> listQuizes() {
		System.out.println("service class load");
		System.out.println(quizRepo.findAll());
		return new LinkedHashSet<>(quizRepo.findAll());
	}

	//get once
	@Override
	public Quiz getQuiz(Integer quizId) {
		
		return quizRepo.findById(quizId).get();
	}

	//delete
	@Override
	public void deletQuiz(Integer quizId) {
		
		quizRepo.deleteById(quizId);
		
		/*
		 * Quiz quiz=new Quiz();
		 * quiz.setQid(quizId);
		 * 
		 * quizRepo.delete(quiz);
		 */
		
		System.out.println("Delete the quize successfully");
	}

	
	//get all quizzes based on category
	@Override
	public List<Quiz> getQuizzesOfCategory(Category category) {
		
		return quizRepo.findByCategory(category);
	}

	//get activeQuiz only
	@Override
	public List<Quiz> getActiveQuizes() {
		
		return quizRepo.findByActive(true);
	}

	//get ActiveQuizeOfCategory
	@Override
	public List<Quiz> getActiveQuizeOfCategory(Category category) {
		
		return quizRepo.findByCategoryAndActive(category, true);
	}

	
}//class
