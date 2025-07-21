package com.webapp.questionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.webapp.questionservice.dto.QuestionDTO;
import com.webapp.questionservice.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>{
	
	@Query("SELECT q.questionTitle from Question q")
	List<String> findAllQustionTitleList();
	
	@Query("SELECT new com.webapp.questionservice.dto.QuestionDTO(q.id, q.questionTitle) FROM Question q")
	List<QuestionDTO> findAllQuestions1();
	
//	@Query("SELECT q from Question q where q.category = :cat")
//	List<Question> findByCategory(@Param("cat") String cat);
	
	List<Question> findByCategory(String category);
	
	@Query("SELECT q from Question q where q.category= :category ORDER BY RANDOM() LIMIT :numQ")
	List<Question> findRandomQuestionByCategory(String category, int numQ);
	
	
	@Query("SELECT q.id from Question q where q.category= :cat ORDER BY RANDOM() LIMIT :numQ")
	List<Integer>  findRandomQueIdsByCat(String cat, Integer numQ);
	
}
