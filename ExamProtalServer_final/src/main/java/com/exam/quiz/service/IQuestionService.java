package com.exam.quiz.service;

import java.util.Set;

import com.exam.quiz.entity.Question;
import com.exam.quiz.entity.Quiz;

public interface IQuestionService {

	public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Set<Question> listQuestions();
	
	public Question getQuestion(Integer questionId);
	
	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
	
	public void deleteQuestion(Integer questionId);
	
	public Question getQues(Integer quesid);
}
