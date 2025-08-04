package com.webapp.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.questionservice.dto.QuestionDTO;
import com.webapp.questionservice.model.Question;
import com.webapp.questionservice.model.QuestionWrapper;
import com.webapp.questionservice.model.Response;
import com.webapp.questionservice.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	//sample checking
	@Autowired
	private QuestionService queSer;
	
	@Autowired
	Environment environment;
	
	@GetMapping("questions")
	private String allQuestion() {
		
		return "Hello from question";
	}
	
	@GetMapping("allQuestions")
	private List<String> allQue() {
		return queSer.allQuestion();
	}
	
	@GetMapping("allQuestions1")
	private ResponseEntity<List<QuestionDTO>> getAllQuestions() {
		return ResponseEntity.ok(queSer.allQuestions1());
	}
	
	@PostMapping("question")
	private Question addQuestion(@RequestBody Question question) {
		System.out.println("question in controller: "+ question);
		return queSer.addQuestion(question);
	}
	
//	@GetMapping("/category")
//	private List<Question> allQuestionByCategory(@RequestParam String cat){
//		System.out.println("C: "+ cat);
//		return queSer.getQuestionByCategory(cat);
//	}
	
	@GetMapping("/category/{cat}")
	private ResponseEntity<List<Question>> allQuestionByCategory(@PathVariable("cat") String category){
//		return queSer.getQuestionByCategory(category);
		return ResponseEntity.ok(queSer.getQuestionByCategory(category));
	}
	
	@GetMapping("/generate")
	private ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String catName, @RequestParam Integer numQues){
		return ResponseEntity.ok(queSer.getQuestionsForQuiz(catName, numQues));
	}
	
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> queIds){
		System.out.println(environment.getProperty("local.server.port"));
		return ResponseEntity.ok(queSer.getQuestionsFromId(queIds));
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return ResponseEntity.ok(queSer.getScore(responses));
	}
	
	@DeleteMapping("{id}")
	private ResponseEntity<String> deleteQuestionById(@PathVariable("id") int id){
		return ResponseEntity.ok(queSer.deleteQuestionById(id));
	}
	
}
