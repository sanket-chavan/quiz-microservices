package com.webapp.questionservice.exception;

public class CategoryNotFoundException extends RuntimeException{
	public CategoryNotFoundException(String message) {
		super(message);
	}
}
