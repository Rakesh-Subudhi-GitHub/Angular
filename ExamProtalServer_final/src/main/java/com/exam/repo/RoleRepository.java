package com.exam.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Serializable> {

}
