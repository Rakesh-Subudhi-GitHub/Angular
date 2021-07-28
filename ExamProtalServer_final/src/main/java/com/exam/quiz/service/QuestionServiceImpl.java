package com.exam.quiz.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.quiz.entity.Question;
import com.exam.quiz.entity.Quiz;
import com.exam.quiz.repo.QuestionRepo;


@Service
public class QuestionServiceImpl implements IQuestionService {

	
	@Autowired
	private QuestionRepo questionRepo;
	
	@Override
	public Question addQuestion(Question question) {
		return questionRepo.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		
		return questionRepo.save(question);
	}

	@Override
	public Set<Question> listQuestions() {
		return new LinkedHashSet<> ( questionRepo.findAll());
	}

	@Override
	public Question getQuestion(Integer questionId) {
		
		return questionRepo.findById(questionId).get();
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		
		return questionRepo.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Integer questionId) {
		
		Question question=new Question();
		question.setQuesid(questionId);
		
		questionRepo.delete(question);
		
//		questionRepo.deleteById(questionId);
		
	System.out.println("Question is deleted");
	
	}

	@Override
	public Question getQues(Integer quesid) {
		
		return questionRepo.getById(quesid);
		
	}

	
}//class
