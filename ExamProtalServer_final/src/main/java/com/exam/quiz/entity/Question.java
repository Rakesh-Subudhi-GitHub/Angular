package com.exam.quiz.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "QUESTION")
@Setter
@Getter
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "QUESTION_ID")
	private Integer quesid;
	
	@Column(name = "CONTENT",length = 1000)
	private String content;
	
	@Column(name = "IMG_QUESTION")
	private String img;
	
	@Column(name = "OPTION1")
	private String option1;
	
	@Column(name = "OPTION2")
	private String option2;
	
	@Column(name = "OPTION3")	
	private String option3;
	
	@Column(name = "OPTION4")
	private String option4;
	
	@Column(name = "ANSWER")
	private String answer;
	
	@Transient
	private String givenAnswer;
	
	@Column
	private String title;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;
	
	
	@CreationTimestamp
	@Column(name = "CREATE_DATE",updatable = false)
	private LocalDate createDate;
	
	@UpdateTimestamp
	@Column(name="UPDATE_DATE",insertable = false)
	private LocalDate updateDate;
	
	
	
}//class
