package com.webapp.questionservice.exception;

public class NoQuestionException extends RuntimeException{
	public NoQuestionException(String msg) {
		super(msg);
	}
}
