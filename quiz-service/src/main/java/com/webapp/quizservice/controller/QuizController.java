package com.webapp.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.quizservice.dto.QuizDto;
import com.webapp.quizservice.model.QuestionWrapper;
import com.webapp.quizservice.model.Quiz;
import com.webapp.quizservice.model.Response;
import com.webapp.quizservice.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizSer;
	
// /create?category=Java&numQ=5&title=JQuiz	
	@PostMapping("/create")
	public ResponseEntity<Quiz> createQuiz(
			@RequestBody QuizDto quizDto){
		
      	Quiz quiz = quizSer.createQuiz(quizDto.getCatName(), quizDto.getNumQues(), quizDto.getTitle());
      	return ResponseEntity.ok(quiz);
	}
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer id){
		List<QuestionWrapper> quizQuestions = quizSer.getQuizQuestions(id);
		return ResponseEntity.ok(quizQuestions);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
		return ResponseEntity.ok(quizSer.calculateResult(id, responses));
	}
	
}
