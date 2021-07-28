package com.exam.quiz.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

import com.exam.quiz.entity.Question;
import com.exam.quiz.entity.Quiz;
import com.exam.quiz.service.IQuestionService;
import com.exam.quiz.service.IQuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private IQuestionService questionService;

	@Autowired
	private IQuizService quizService;
	
	//add-Question
	@PostMapping("/")
	public Question addQuestion(@RequestBody Question question)
	{
		return questionService.addQuestion(question);
	}

	//update-Question
	@PutMapping("/")
	public Question updateQustion(@RequestBody Question question)
	{
		return questionService.updateQuestion(question);
	}
	
	//getAll-Question
	@GetMapping("/")
	public Set<Question> getAllQuestion()
	{
		return questionService.listQuestions();
	}
	
	//getSignle-Question
	@GetMapping("/{questionId}")
	public Question getQuestion(@PathVariable Integer questionId)
	{
		return questionService.getQuestion(questionId);
	}
	
	//delete Question
	@DeleteMapping("/{questionId}")
	public void deleteQuestion(@PathVariable Integer questionId)
	{
	   questionService.deleteQuestion(questionId);	
	}
	
	//getQuize of all Question for user limited
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionOfQuiz(@PathVariable Integer qid)
	{
		
		Quiz quiz = quizService.getQuiz(qid);
		Set<Question> questions = quiz.getQuestion();
	
		List<Question> list=new ArrayList(questions);
		
		if(list.size() > Integer.parseInt(quiz.getNumberOfQustions()))
		{
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQustions()));
			
		}
		
		//ignore the question
		list.forEach((q)->{
			q.setAnswer("");
		});
		
		
		Collections.shuffle(list);//random value of Question position set
		
		return ResponseEntity.ok(list);
		
	}//method
	
	//get all question  admin access the all question
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionOfQuizAdmin(@PathVariable("qid") Integer qid)
	{
		Quiz quiz = new Quiz();
		quiz.setQid(qid);
		Set<Question> questionOfQuiz=questionService.getQuestionsOfQuiz(quiz);
		
		return ResponseEntity.ok(questionOfQuiz);
	
		
	}//method
	
	
	//evaluate the Questions
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> question)
	{
		System.out.println(question);
		
		Double markGot = 0.0;
		Integer correctAnswers=0;
		Integer attempted=0;	
		
		for(Question q:question) 
		{
			//single questions and ans
			Question ques = questionService.getQues(q.getQuesid());
			
			if(ques.getAnswer().equals(q.getGivenAnswer()))
			{
				//correct
				correctAnswers = correctAnswers+1;
			}
			
			if(q.getGivenAnswer()!=null)
			{
				attempted++;
			}
		   
			Double markSingle = Double.parseDouble(question.get(0).getQuiz().getMaxMarks())
								/Double.parseDouble(question.get(0).getQuiz().getNumberOfQustions());
	 // this.markSingle=this.Questions[0].quiz.maxMarks/this.Questions[0].quiz.numberOfQustions; //poticular question marks
			
			markGot = markSingle*correctAnswers;	    
					
		}//for each
		
		Map<String, Object> map =Map.of("markGot",markGot,"correctAnswers",correctAnswers,"attempted",attempted);
	
		return ResponseEntity.ok(map);
	}
	
}//class
