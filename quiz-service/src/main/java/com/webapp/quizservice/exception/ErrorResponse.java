package com.webapp.quizservice.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	private LocalDateTime timeStamp;
	private String message;
	private int status;
	
	public ErrorResponse(String message, int status) {
		this.timeStamp = LocalDateTime.now();
		this.message = message;
		this.status = status;
	}
	
}
