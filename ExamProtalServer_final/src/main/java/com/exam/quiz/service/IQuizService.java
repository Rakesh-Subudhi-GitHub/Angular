package com.exam.quiz.service;

import java.util.List;
import java.util.Set;

import com.exam.quiz.entity.Category;
import com.exam.quiz.entity.Quiz;

public interface IQuizService {

	public Quiz addQuiz(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> listQuizes();
	
	public Quiz getQuiz(Integer quizId);
	
	public void deletQuiz(Integer quizId);
	
	
	public List<Quiz> getQuizzesOfCategory(Category category);
	
	public List<Quiz> getActiveQuizes();
	
	public List<Quiz> getActiveQuizeOfCategory(Category category);
	
	
}
