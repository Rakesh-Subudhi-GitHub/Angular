package com.exam.quiz.repo;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.quiz.entity.Question;
import com.exam.quiz.entity.Quiz;

public interface QuestionRepo extends JpaRepository<Question, Serializable>{

	Set<Question> findByQuiz(Quiz quiz);

}
