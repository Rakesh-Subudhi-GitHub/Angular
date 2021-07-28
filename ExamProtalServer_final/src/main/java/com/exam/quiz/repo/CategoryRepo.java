package com.exam.quiz.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.quiz.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Serializable>{

}
