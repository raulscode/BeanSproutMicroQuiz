package com.raulbetancourt.beansprout;

import com.raulbetancourt.beansprout.Service.FlashCardService;
import com.raulbetancourt.beansprout.Service.QuizService;
import com.raulbetancourt.beansprout.model.FlashCard;
import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.model.User;
import com.raulbetancourt.beansprout.model.Role;
import com.raulbetancourt.beansprout.repository.FlashCardRepository;
import com.raulbetancourt.beansprout.repository.QuizRepository;
import com.raulbetancourt.beansprout.repository.RoleRepository;
import com.raulbetancourt.beansprout.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Test
	void contextLoads() {


		//Test quiz repository method
		Quiz quiz = new Quiz();
		quiz.setQuizTitle("Test Quiz");
		quizRepository.save(quiz);

		Integer quizID = quiz.getQuizID();

		assertEquals("Test Quiz", quizRepository.findByQuizID(quizID).getQuizTitle());

		//Test flash card repository method
		FlashCard flashCard = new FlashCard();
		flashCard.setAnswer(2);
		flashCardRepository.save(flashCard);

		Integer cardID = flashCard.getCardID();

		assertEquals(2, flashCardRepository.findByCardID(cardID).getAnswer());

		User user = new User();
		if(userRepository.findByUsername("Gary") == null) {

			user.setUserName("Gary");
		}

		//Test user repository
		userRepository.save(user);

		Long userID = user.getUserID();

		assertEquals(userID, userRepository.findByUsername("Gary").getUserID());

		//Test role repository
		Role role = new Role();
		if(roleRepository.findByRoleName("Example4") == null) {

			role.setRoleName("Example4");
		}
		roleRepository.save(role);

		Integer roleID = role.getRoleID();

		assertEquals(roleID, roleRepository.findByRoleName("Example4").getRoleID());

		//Test flashcard service class
		List<String> stringList = new ArrayList<>();

		assertNotNull(flashCardService.createExampleCard("Example Question", stringList, 3));

		//Test quiz service class
		assertNotNull(quizService.getQuizzes());



	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4})
	void paraTest(int answer) {

		List<String> stringList = new ArrayList<>();
		assertNotNull(flashCardService.createExampleCard("Example Question 2", stringList, answer));

	}

}
