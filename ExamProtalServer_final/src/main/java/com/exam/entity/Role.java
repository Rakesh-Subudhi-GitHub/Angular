package com.exam.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Role_Table")
@Setter
@Getter
@ToString
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer roleid;
	
	@NonNull
	@Column(name = "ROLE_NAME",length = 20)
	private String roleName;
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
	private Set<UserRole> userRole=new HashSet<>();
	
	//===========================================
	
	@CreationTimestamp
	@Column(name = "CREATE_DATE",updatable = false)
	private LocalDate createDate;
	
	@UpdateTimestamp
	@Column(name="UPDATE_DATE",insertable = false)
	private LocalDate updateDate;
	
	
}//class
