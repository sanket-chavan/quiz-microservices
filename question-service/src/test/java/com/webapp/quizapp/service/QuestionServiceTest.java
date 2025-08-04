
package com.webapp.quizapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.webapp.questionservice.dto.QuestionDTO;
import com.webapp.questionservice.exception.NoQuestionException;
import com.webapp.questionservice.model.Question;
import com.webapp.questionservice.repository.QuestionRepo;
import com.webapp.questionservice.service.QuestionService;



@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {
	
	@Mock
	QuestionRepo queRepo;
	
	@InjectMocks
	QuestionService queService;

//	 @BeforeEach
//	    void setUp() {
//	        MockitoAnnotations.openMocks(this);
//	    }
//	
//	@BeforeAll
//	 static void beforeAll() {
//		System.out.println("Before all");
//	}
//	
//	@AfterAll
//	static void afterAll() {
//		System.out.println("After all");
//	}
//	@BeforeEach
//	void beforeEach() {
//		System.out.println("Before each");
//	}
//	
//	@AfterEach
//	void afterEach() {
//		System.out.println("after each");
//	}
	
	@Test
	void testAllQuestions1_ReturnsQuestions() {
		QuestionDTO que1 = new QuestionDTO(1, "What is java?");
		QuestionDTO que2 = new QuestionDTO(2, "Does @Mock mocks the repository?");
		
		when(queRepo.findAllQuestions1()).thenReturn(List.of(que1, que2));
		
//		System.out.println("Implementation is in progress: ");
		
		List<QuestionDTO> allQuestion = queService.allQuestions1();
		assertNotNull(allQuestion);
		assertEquals(que1.getId(), allQuestion.get(0).getId());
		assertEquals(que2.getQuestionText(), allQuestion.get(1).getQuestionText());
	
	}

	@Test
	void testAllQuestions1_EmptyList_ThrowsException() {
		when(queRepo.findAllQuestions1()).thenReturn(Collections.emptyList());
		
		NoQuestionException exc = assertThrows(NoQuestionException.class, ()->{queService.allQuestions1();});
		
		assertEquals("No quesions found", exc.getMessage());
	}
	
	@Test
	void testAllQuestions1_NullRepoResponse_ThrowsException() {
		when(queRepo.findAllQuestions1()).thenReturn(null);
		
		assertNull(queRepo.findAllQuestions1());

	}
	
	@Test
	void testDeleteProductShouldDeleteProductSuccessfully() {
		Question question = new Question();
		
		when(queRepo.findById(1)).thenReturn(Optional.of(question));
		
		doNothing().when(queRepo).deleteById(1);
		
		String result = queService.deleteQuestionById(1);
		
		assertEquals("Question with id:1 deleted successfully", result);
		verify(queRepo, times(1)).findById(1);
		verify(queRepo,times(1)).deleteById(1);
	}
	
//	@Test
//	void testAllQuestion() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testAddQuestion() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testGetQuestionByCategory() {
//		fail("Not yet implemented");
//	}


}


/*
 * #1. Add unit tests to make code robust(ability to handle errors and unexpected situations efficiently, ensuring stable and reliable application execution.)
 *#2. A unit test is a to test the smallest parts of your code such as individual methods or classes to ensure they work as expected.
 *#3. why unit testing is important:
 *	- fast feedback: unit tests are quick to run and can immediately tell you if something is broken.
 *	-early bug detection: catch the bug at method level before they propogate
 * -easier to maintain: they help to ensure future changes don't break existing functionality.
 * -increases confidence: you can refactor you code with the confidence that everything still works 
 * #4. how much code is covered in unit test is called as code coverage. tools:  
 * ✅ @Mock mocks the repository.

✅ @InjectMocks injects the mocked repository into your service.

✅ MockitoAnnotations.openMocks(this) ensures the mocks are initialized.

✅ when(...).thenReturn(...) prepares your test data.
 *
 //Test Lifecycle:
 //BeforeAll - class level setup, to setup db connection
 //BeforeEach - to setup test surrounding ex. dynamic objects may be there for each test case 
 /Test
 //AfterEach - cleanup - ex. reset some values after each test case
 //AfterAll - class level cleanup,remove db connection

 
 */