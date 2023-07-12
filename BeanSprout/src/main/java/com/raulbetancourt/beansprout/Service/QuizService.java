package com.raulbetancourt.beansprout.Service;

import com.raulbetancourt.beansprout.repository.FlashCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raulbetancourt.beansprout.model.FlashCard;
import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.repository.QuizRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;


//Service class for quiz entity.
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final FlashCardRepository flashCardRepository;
    private final FlashCardService flashCardService;

    //Going to use this entity manager for custom query in changeQuizTitle() method.
    @PersistenceContext
    private EntityManager entityManager;

    public QuizService(QuizRepository quizRepository, FlashCardRepository flashCardRepository, FlashCardService flashCardService)
    {
        this.quizRepository = quizRepository;
        this.flashCardRepository = flashCardRepository;
        this.flashCardService = flashCardService;
    }

    //Just a method that fetches all quizzes in the quizzes table.
    public List<Quiz> getQuizzes(){

        return (List<Quiz>) quizRepository.findAll();
    }

    //Deletes a quiz after finding it by its ID and clears associations with flash cards (but does not delete flash cards).
    public void deleteQuizById(Integer quizID) {

        //Retrieve quiz.
        Quiz quiz = quizRepository.findById(quizID).orElseThrow();

        //Remove relationship in flash card entities first. (Otherwise it will not work.)
        for (FlashCard flashCard : quiz.getFlashCards())
        {
            flashCard.getQuizCollect().remove(quiz);
        }

        //Remove relationship to any related Quiz entities on FlashCard side.
        quiz.getFlashCards().clear();

        //Save changes.
        quizRepository.save(quiz);

        //Delete flash card.
        quizRepository.delete(quiz);

    }

    //Method that changes quiz title with a custom query the old-fashioned way.
    public boolean changeQuizTitle(Integer quizID, String newQuizTitle){

        boolean success = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String dburl = "jdbc:mysql://localhost:3306/beansproutdb";
        String user = "root";
        String password = "password";
        int updatedRows = 0;

        try{

            if(quizID != null) {
                connection = DriverManager.getConnection(dburl, user, password);
                final String customQuery = "UPDATE quizzes q SET q.quiz_title = " + "\"" + newQuizTitle + "\"" + " WHERE q.quizid = " + quizID + ";";
                preparedStatement = connection.prepareStatement(customQuery);
                try {
                    updatedRows = preparedStatement.executeUpdate();
                }
                catch(Exception e){
                    System.out.println(e);
                    System.out.println("Error!");
                }

                if(updatedRows > 0)
                {
                    success = true;
                }

                try{
                    preparedStatement.close();
                    connection.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }

            }
            else
            {
                //success will remain false
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return success;
    }


    public boolean createExampleQuiz(){

        boolean success = false;

        Quiz quiz = new Quiz();

        quiz.setQuizTitle("Example");

        quizRepository.save(quiz);

        success = true;

        return success;

    }

    public boolean createExampleModels(){

        boolean success = false;

        Quiz quiz = new Quiz();
        FlashCard flashCard = new FlashCard();
        List<Quiz> quizList = new ArrayList<>();

        String question = null;
        Integer answer = 1;

        List<FlashCard> flashCardList = new ArrayList<>();
        List answerList = new ArrayList<>();


        //Example answers
        answerList.add("Example answer 1");
        answerList.add("Example answer 2");
        answerList.add("Example answer 3");
        answerList.add("Example answer 4");

        quiz.setQuizTitle("Example of Loaded Quiz Model");

        for(int i = 0; i <5; i++) {
            flashCard = flashCardService.createExampleCard("Example Question", answerList, answer);
            flashCardList.add(flashCard);
        }

        quiz.setFlashCards(flashCardList);

        quizRepository.save(quiz);

        String quizTitle = null;

        Quiz quiz1 = new Quiz();
        quizTitle = "PC Components";
        quiz1.setQuizTitle(quizTitle);
        quiz1.setFlashCards(getExampleContents(1));
        quizRepository.save(quiz1);

        Quiz quiz2 = new Quiz();
        quizTitle = "Java Basics";
        quiz2.setQuizTitle(quizTitle);
        quiz2.setFlashCards(getExampleContents(2));
        quizRepository.save(quiz2);

        Quiz quiz3 = new Quiz();
        quizTitle = "Javascript Facts";
        quiz3.setQuizTitle(quizTitle);
        quiz3.setFlashCards(getExampleContents(3));
        quizRepository.save(quiz3);

        Quiz quiz4 = new Quiz();
        quizTitle = "Cat Facts";
        quiz4.setQuizTitle(quizTitle);
        quiz4.setFlashCards(getExampleContents(4));
        quizRepository.save(quiz4);

        Quiz quiz5 = new Quiz();
        quizTitle = "Outer Space Quiz";
        quiz5.setQuizTitle(quizTitle);
        quiz5.setFlashCards(getExampleContents(5));
        quizRepository.save(quiz5);



        success = true;

        return success;

    }

    public List<FlashCard> getExampleContents(Integer quizNumber) {

        String quizTitle = "Example Title";
        String question = "Example Question";
        Integer answer = 1;

        Quiz quiz = new Quiz();
        FlashCard flashCard;

        List<FlashCard> flashCardList = new ArrayList<>();

        FlashCard flashCard1 = new FlashCard();
        FlashCard flashCard2 = new FlashCard();
        FlashCard flashCard3 = new FlashCard();
        FlashCard flashCard4 = new FlashCard();
        FlashCard flashCard5 = new FlashCard();

        List<String> answerList1 = new ArrayList<>();
        List<String> answerList2 = new ArrayList<>();
        List<String> answerList3 = new ArrayList<>();
        List<String> answerList4 = new ArrayList<>();
        List<String> answerList5 = new ArrayList<>();

        switch(quizNumber)
        {
        case 1:
        //Contents for flash cards 1 - 5.
        //Topic: PC components



        question = "What is the purpose of a motherboard in a computer system?";
        answerList1.add(0, "To provide power to the components");
        answerList1.add(1, "To store data");
        answerList1.add(2, "To display images and videos");
        answerList1.add(3, "To connect and communicate between components");
        answer = 4;

        flashCard1.setQuestion(question);

        flashCard1.setAnswer(answer);



        question = "Which component of a computer is responsible for processing instructions and performing calculations?";
        answerList2.add(0, "RAM");
        answerList2.add(1, "CPU");
        answerList2.add(2, "GPU");
        answerList2.add(3, "Motherboard");
        answer = 2;

            flashCard2.setQuestion(question);

            flashCard2.setAnswer(answer);



        question = "What is the primary purpose of a graphics card in a computer?";
        answerList3.add(0, "To store data");
        answerList3.add(1, "To connect peripherals");
        answerList3.add(2, "To display images and videos");
        answerList3.add(3, "To provide power to the system");
        answer = 3;

            flashCard3.setQuestion(question);

            flashCard3.setAnswer(answer);



        question = "What does RAM stand for in computer terms?";
        answerList4.add(0, "Random Access Memory");
        answerList4.add(1, "Read-Only Memory");
        answerList4.add(2, "Central Processing Unit");
        answerList4.add(3, "Graphics Processing Unit");
        answer = 1;

            flashCard4.setQuestion(question);

            flashCard4.setAnswer(answer);



        question = "Which component of a computer is responsible for long-term storage of data and programs?";
        answerList5.add(0, "CPU");
        answerList5.add(1, "RAM");
        answerList5.add(2, "SSD");
        answerList5.add(3, "Power supply");
        answer = 3;

            flashCard5.setQuestion(question);

            flashCard5.setAnswer(answer);



        break;

        case 2:

        //Contents for flash cards 6 - 10.
        //Topic: Java Syntax basics

        question = "Which one of these keywords can be used to declare a variable in Java?";
        answerList1.add(0, "int");
        answerList1.add(1, "string");
        answerList1.add(2, "variable");
        answerList1.add(3, "declare");
        answer = 1;

            flashCard1.setQuestion(question);
            flashCard1.setAnswer(answer);


        question = "What does JVM Stand for?";
        answerList2.add(0, "Java Virtual Machine");
        answerList2.add(1, "Jello Victorious Machinations");
        answerList2.add(2, "Jolly Victory Machine");
        answerList2.add(3, "Java Vanilla MachineBean");
        answer = 1;

            flashCard2.setQuestion(question);
            flashCard2.setAnswer(answer);


        question = "Which symbol is used to indicate the end of a statement in Java?";
        answerList3.add(0, ":");
        answerList3.add(1, ",");
        answerList3.add(2, ";");
        answerList3.add(3, ".");
        answer = 2;

            flashCard3.setQuestion(question);
            flashCard3.setAnswer(answer);


        question = "What is the correct way to create an object of a class in Java?";
        answerList4.add(0, "new MyClass()");
        answerList4.add(1, "create MyClass()");
        answerList4.add(2, "MyClass.create()");
        answerList4.add(3, "create new MyClass()");
        answer = 1;

            flashCard4.setQuestion(question);
            flashCard4.setAnswer(answer);


        question = "Which keyword is used to define a method that does not return a value in Java?";
        answerList5.add(0, "void");
        answerList5.add(1, "null");
        answerList5.add(2, "return");
        answerList5.add(3, "boolean");
        answer = 1;


            flashCard5.setQuestion(question);
            flashCard5.setAnswer(answer);

            break;


        case 3:
        //Contents for flash cards 10 - 15.
        //Topic: Javascript facts


        question = "1. JavaScript is a high-level, interpreted programming language.";
        answerList1.add(0, "True");
        answerList1.add(1, "False");
        answerList1.add(2, "Sometimes");
        answerList1.add(3, "Depends on the context");
        answer = 1;

            flashCard1.setQuestion(question);
            flashCard1.setAnswer(answer);

        question = "2. JavaScript is primarily used for client-side web development.";
        answerList2.add(0, "True");
        answerList2.add(1, "False");
        answerList2.add(2, "Not sure");
        answerList2.add(3, "Depends on the use case");
        answer = 1;

            flashCard2.setQuestion(question);
            flashCard2.setAnswer(answer);

        question = "3. JavaScript was created by Brendan Eich in 1995.";
        answerList3.add(0, "True");
        answerList3.add(1, "False");
        answerList3.add(2, "I don't know");
        answerList3.add(3, "It's a possibility");
        answer = 1;

            flashCard3.setQuestion(question);
            flashCard3.setAnswer(answer);

        question = "4. JavaScript can be used on both the front-end and back-end of web development.";
        answerList4.add(0, "True");
        answerList4.add(1, "False");
        answerList4.add(2, "Maybe");
        answerList4.add(3, "Depends on the implementation");
        answer = 1;

            flashCard4.setQuestion(question);
            flashCard4.setAnswer(answer);

        question = "5. JavaScript supports object-oriented programming principles.";
        answerList5.add(0, "True");
        answerList5.add(1, "False");
        answerList5.add(2, "I'm not certain");
        answerList5.add(3, "It's possible");
        answer = 0;

            flashCard5.setQuestion(question);
            flashCard5.setAnswer(answer);

            break;

         case 4:
        //Contents for flash cards 16 - 20.
        //Topic: Cat facts

        question = "1. Which of the following is the scientific name for the house cat?";
        answerList1.add(0, "Felis silvestris catus");
        answerList1.add(1, "Felis domesticus");
        answerList1.add(2, "Felis catus");
        answerList1.add(3, "Felis familiaris");
        answer = 4;

                flashCard1.setQuestion(question);
                flashCard1.setAnswer(answer);

        question = "2. How many toes does the average house cat have?";
        answerList2.add(0, "16");
        answerList2.add(1, "18");
        answerList2.add(2, "20");
        answerList2.add(3, "22");
        answer = 2;

                flashCard2.setQuestion(question);
                flashCard2.setAnswer(answer);

        question = "3. What is the average lifespan of a house cat?";
        answerList3.add(0, "8-10 years");
        answerList3.add(1, "10-12 years");
        answerList3.add(2, "12-15 years");
        answerList3.add(3, "15-20 years");
        answer = 3;

                flashCard3.setQuestion(question);
                flashCard3.setAnswer(answer);

        question = "4. Which of the following is NOT a recognized cat breed?";
        answerList4.add(0, "Siamese");
        answerList4.add(1, "Persian");
        answerList4.add(2, "Maine Coon");
        answerList4.add(3, "Furballus maximus");
        answer = 4;

                flashCard4.setQuestion(question);
                flashCard4.setAnswer(answer);

        question = "5. What is the typical body temperature range for a healthy cat?";
        answerList5.add(0, "35-37°C (95-99°F)");
        answerList5.add(1, "38-40°C (100-104°F)");
        answerList5.add(2, "41-43°C (106-109°F)");
        answerList5.add(3, "44-46°C (111-115°F)");
        answer = 2;

                flashCard5.setQuestion(question);
                flashCard5.setAnswer(answer);

                break;

         case 5:
        //Populating flash cards 20 - 25.
        //Topic: Outer space

        question = "1. Which planet in our solar system is known for its prominent rings?";
        answerList1.add(0, "Mars");
        answerList1.add(1, "Jupiter");
        answerList1.add(2, "Saturn");
        answerList1.add(3, "Neptune");
        answer = 2;

             flashCard1.setQuestion(question);
             flashCard1.setAnswer(answer);

        question = "2. What is the largest moon in our solar system?";
        answerList2.add(0, "Europa");
        answerList2.add(1, "Titan");
        answerList2.add(2, "Ganymede");
        answerList2.add(3, "Enceladus");
        answer = 2;

             flashCard2.setQuestion(question);
             flashCard2.setAnswer(answer);

        question = "3. What is the name of the closest star to our solar system?";
        answerList3.add(0, "Proxima Centauri");
        answerList3.add(1, "Sirius");
        answerList3.add(2, "Alpha Centauri");
        answerList3.add(3, "Betelgeuse");
        answer = 1;

             flashCard3.setQuestion(question);
             flashCard3.setAnswer(answer);

        question = "4. What is a celestial event where the Moon passes between the Earth and the Sun?";
        answerList4.add(0, "Lunar eclipse");
        answerList4.add(1, "Solar eclipse");
        answerList4.add(2, "Asteroid collision");
        answerList4.add(3, "Meteor shower");
        answer = 1;

             flashCard4.setQuestion(question);
             flashCard4.setAnswer(answer);

        question = "5. Which of the following is NOT a type of galaxy?";
        answerList5.add(0, "Spiral");
        answerList5.add(1, "Elliptical");
        answerList5.add(2, "Irregular");
        answerList5.add(3, "Crescent");
        answer = 3;

             flashCard5.setQuestion(question);
             flashCard5.setAnswer(answer);

             break;

    }



        flashCard1.setAnswerList(answerList1);
        flashCardRepository.save(flashCard1);
        flashCardList.add(flashCard1);

        flashCard2.setAnswerList(answerList2);
        flashCardRepository.save(flashCard2);
        flashCardList.add(flashCard2);

        flashCard3.setAnswerList(answerList3);
        flashCardRepository.save(flashCard3);
        flashCardList.add(flashCard3);

        flashCard4.setAnswerList(answerList4);
        flashCardRepository.save(flashCard4);
        flashCardList.add(flashCard4);

        flashCard5.setAnswerList(answerList5);
        flashCardRepository.save(flashCard5);
        flashCardList.add(flashCard5);



        return flashCardList;

    }

}
