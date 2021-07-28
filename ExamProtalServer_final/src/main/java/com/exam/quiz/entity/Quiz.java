package com.exam.quiz.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "QUIZES")
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "QUIZES_ID")
	private Integer qid;
	
	@Column(name = "QUIZES_TITLE")
	private String title;
	
	@Column(name = "QUIZE_DESC")
	private String description;
	
	@Column(name = "MAXIMUM_MARK",length = 10)
	private String maxMarks;
	
	@Column(name = "NUMBER_OF_QUSTION",length = 10)
	private String numberOfQustions;
	
	@Column(name = "QUIZE_ACTIVE")
	private boolean active=false;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	
	@OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Question> question=new HashSet<>();
	
	
	
	
	@CreationTimestamp
	@Column(name = "CREATE_DATE",updatable = false)
	private LocalDate createDate;
	
	@UpdateTimestamp
	@Column(name="UPDATE_DATE",insertable = false)
	private LocalDate updateDate;
	
}//class
