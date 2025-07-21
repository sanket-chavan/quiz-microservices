package com.webapp.questionservice.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.questionservice.dto.QuestionDTO;
import com.webapp.questionservice.exception.CategoryNotFoundException;
import com.webapp.questionservice.exception.QuestionNotFoundException;
import com.webapp.questionservice.model.Question;
import com.webapp.questionservice.model.QuestionWrapper;
import com.webapp.questionservice.model.Response;
import com.webapp.questionservice.repository.QuestionRepo;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepo queRepo;
	
	public List<String> allQuestion(){
		return queRepo.findAllQustionTitleList();
	}
	
	public List<QuestionDTO> allQuestions1(){
		List<QuestionDTO> allQuestions1 = queRepo.findAllQuestions1();
		System.out.println(allQuestions1.getFirst());
		return allQuestions1;
	}

	public Question addQuestion(Question que) {
		System.out.println(que);
		return queRepo.save(que);
	}
	
	public List<Question> getQuestionByCategory(String cat){
		List<Question> byCategory = queRepo.findByCategory(cat);
		if(byCategory.isEmpty()) {
			throw new CategoryNotFoundException("No questions found for category: "+ cat);
		}
		return byCategory;
	}
	
	public List<Integer> getQuestionsForQuiz(String catName, Integer numQues){
		List<Integer> randQuesId =  queRepo.findRandomQueIdsByCat(catName, numQues);
		if(randQuesId.isEmpty()) {
			throw new CategoryNotFoundException("No ques found for category: "+ catName);
		}
	
		return randQuesId;
	}
	
	public List<QuestionWrapper> getQuestionsFromId(List<Integer> qIds){
		if(qIds == null || qIds.isEmpty()) {
			throw new IllegalArgumentException("Question ID list cannot be null or empty");
		}
		List<QuestionWrapper> qWList = qIds.stream()
										.map(id -> {
										Question q =   queRepo.findById(id)
												.orElseThrow(()->new QuestionNotFoundException("Question not found for id: "+id));
										QuestionWrapper qW = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
										return qW;
										})
										.collect(Collectors.toList());
		return qWList;
												
	}
	
	public Integer getScore(List<Response> responses) {
		int right = 0;
		for(Response res : responses) {
			Question que =  queRepo.findById(res.getId()).orElseThrow(()->new QuestionNotFoundException("Question not found for id: "+res.getId()));
			if(que.getRightAnswer().equals(res.getResponse())) {
				right++;
			}
		}
		return right;
	}
	
}
