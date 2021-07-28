package com.exam.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "USER_ROLE")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userRoleid;
	
	//single user
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;//this user is indicate the UserTable role
	
	//set the user role
	@ManyToOne
	private Role role;
	
	
	
	
	//--------------------------------------------------
	@CreationTimestamp
	@Column(name = "CREATE_DATE",updatable = false)
	private LocalDate createDate;
	
	@UpdateTimestamp
	@Column(name="UPDATE_DATE",insertable = false)
	private LocalDate updateDate;
	
	
}//class
