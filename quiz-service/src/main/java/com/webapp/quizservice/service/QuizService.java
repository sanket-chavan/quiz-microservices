package com.webapp.quizservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.webapp.quizservice.exception.CategoryNotFoundException;
import com.webapp.quizservice.exception.QuizNotFoundException;
import com.webapp.quizservice.feign.QuizInterface;
import com.webapp.quizservice.model.QuestionWrapper;
import com.webapp.quizservice.model.Quiz;
import com.webapp.quizservice.model.Response;
import com.webapp.quizservice.repository.QuizRepo;

@Service
public class QuizService {
	
	@Autowired
	private QuizRepo quizRepo;
	
	@Autowired
	QuizInterface quizInterface;
	
	public Quiz createQuiz(String category, int numQ, String title) {
		
		List<Integer> questionsForQuiz = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		if(questionsForQuiz == null || questionsForQuiz.isEmpty()) {
			throw new CategoryNotFoundException("No questions found for category: "+ category);
		}
	
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQueIds(questionsForQuiz);
		Quiz savedQuiz = quizRepo.save(quiz);
		
		return savedQuiz;

	}
	
	public List<QuestionWrapper> getQuizQuestions(Integer id) {
		
		Quiz quiz =  quizRepo.findById(id).orElseThrow(()-> new QuizNotFoundException("Quiz not found with id: "+ id));
		
		List<QuestionWrapper> questionsFromId = quizInterface.getQuestionsFromId(quiz.getQueIds()).getBody();	
		
		return questionsFromId;
	}
	
	public Integer calculateResult(Integer id, List<Response> responses) {

		ResponseEntity<Integer> score = quizInterface.getScore(responses);
		
		return score.getBody();
	}
	
}
