package com.exam.quiz.entity;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Data
@Entity
@Table(name = "CATEGORIES")
@ToString
public class Category {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "CATEGORY_ID")
	private Integer cid;
	
	@Column(name = "CATEGORY_TITLE")
	private String title;
	
	@Column(name = "CATEGORY_DESC")
	private String description;
	
	@OneToMany(mappedBy = "category",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Quiz> quiz=new LinkedHashSet<>();
	
	
	@CreationTimestamp
	@Column(name = "CREATE_DATE",updatable = false)
	private LocalDate createDate;
	
	@UpdateTimestamp
	@Column(name="UPDATE_DATE",insertable = false)
	private LocalDate updateDate;
	
}//class
