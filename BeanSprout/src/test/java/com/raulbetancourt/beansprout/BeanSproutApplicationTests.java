package com.raulbetancourt.beansprout;

import com.raulbetancourt.beansprout.Service.FlashCardService;
import com.raulbetancourt.beansprout.Service.QuizService;
import com.raulbetancourt.beansprout.model.FlashCard;
import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.repository.FlashCardRepository;
import com.raulbetancourt.beansprout.repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BeanSproutApplicationTests {

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	FlashCardRepository flashCardRepository;

	@Autowired
	QuizService quizService;

	@Autowired
	FlashCardService flashCardService;

	@Test
	void contextLoads() {


		//Test quiz repository method
		Quiz quiz = new Quiz();
		quizRepository.save(quiz);

		Integer quizID = quiz.getQuizID();

		assertEquals(quiz, quiz);

		//Test flash card repository method
		List<FlashCard> flashCardList = flashCardService.getAllFlashCards();

		FlashCard flashCard = flashCardList.get(1);

		assertEquals(flashCard, flashCardRepository.findByCardID(1));


	}

}
