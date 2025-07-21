package com.webapp.quizservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.quizservice.model.Quiz;

@Repository
public interface QuizRepo  extends JpaRepository<Quiz, Integer>{

	
}
