package com.webapp.questionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
public class QuestionDTO {
	private Integer id;
	private String questionText;
	
	 public QuestionDTO(Integer id, String questionText) {
	        this.id = id;
	        this.questionText = questionText;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public String getQuestionText() {
	        return questionText;
	    }
}
