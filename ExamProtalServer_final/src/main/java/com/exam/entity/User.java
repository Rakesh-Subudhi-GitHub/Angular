package com.exam.entity;

import java.time.LocalDate;
import java.util.Collection;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "User")
@ToString
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userid;
	
	@Column(name = "USER_NAME",length = 30)
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "FIRST_NAME",length = 30)
	private String fastname;
	
	@Column(name = "LAST_NAME",length = 30)
	private String lastname;
	
	@Column(name = "EMAIL",length = 50)
	private String email;
	
	@Column(name = "PHONE",length = 20)
	private String phone;
	
	@Column(name = "ENABLE")
	private Boolean enable;
	
	@Column(name = "PROFILE_IMAGE")
	private String profile_img;
	
	//user many roles
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
	@JsonIgnore
	private Set<UserRole> userRole=new HashSet<>();
	
	
	//============================================================
	
	@CreationTimestamp
	@Column(name = "CREATE_DATE",updatable = false)
	private LocalDate createDate;
	
	@UpdateTimestamp
	@Column(name="UPDATE_DATE",insertable = false)
	private LocalDate updateDate;

	//================================================================
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<Authority> set=new HashSet<>();
		
		this.userRole.forEach(Role->{
			set.add(new Authority(Role.getRole().getRoleName())); //collect the role help of userRole 
		});
		
		return set;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return enable;
	}

//=============================================================================	
	
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty("password")
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFastname() {
		return fastname;
	}

	public void setFastname(String fastname) {
		this.fastname = fastname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}


}//class
