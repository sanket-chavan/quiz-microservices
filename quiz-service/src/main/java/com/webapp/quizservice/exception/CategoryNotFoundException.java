package com.webapp.quizservice.exception;

public class CategoryNotFoundException extends RuntimeException{
	public CategoryNotFoundException(String message) {
		super(message);
	}
}
