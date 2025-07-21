package com.webapp.questionservice.exception;

public class QuestionNotFoundException extends RuntimeException{
	
	private String msg;
	
	public QuestionNotFoundException(String msg) {
		super(msg);
	}
	
}
