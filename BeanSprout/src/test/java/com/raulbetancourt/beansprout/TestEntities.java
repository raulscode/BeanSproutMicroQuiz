package com.raulbetancourt.beansprout;

import com.raulbetancourt.beansprout.model.FlashCard;
import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.repository.FlashCardRepository;
import com.raulbetancourt.beansprout.repository.QuizRepository;

import java.util.ArrayList;
import java.util.List;

//Class for loading test entries in the tables
public class TestEntities {


    QuizRepository quizRepository;
    FlashCardRepository flashCardRepository;

    public TestEntities(QuizRepository quizRepository, FlashCardRepository flashCardRepository) {
        this.quizRepository = quizRepository;
        this.flashCardRepository = flashCardRepository;
    }

    public TestEntities() {

    }

    public void createTestEntities(){

        FlashCard flashCard = new FlashCard();
        Quiz quiz = new Quiz();

        List<FlashCard> flashCardList1 = new ArrayList<>();
        List<FlashCard> flashCardList2 = new ArrayList<>();
        List<FlashCard> flashCardList3 = new ArrayList<>();
        List<FlashCard> flashCardList4 = new ArrayList<>();
        List<FlashCard> flashCardList5 = new ArrayList<>();

        List<List<FlashCard>> flashCardLists = new ArrayList<>();

        for(int i = 0; i < 5; i++)
        {
            flashCardList1.add(flashCard);
            flashCardList2.add(flashCard);
            flashCardList3.add(flashCard);
            flashCardList4.add(flashCard);
            flashCardList5.add(flashCard);
        }

        flashCardLists.add(flashCardList1);
        flashCardLists.add(flashCardList2);
        flashCardLists.add(flashCardList3);
        flashCardLists.add(flashCardList4);
        flashCardLists.add(flashCardList5);

        List<Quiz> quizList = new ArrayList<>();


        for(int i = 0; i < 5; i++)
        {
            quizList.add(quiz);
        }

        populateTestEntities(flashCardLists, quizList);

    }

    public void populateTestEntities(List<List<FlashCard>> flashCardLists, List<Quiz> quizList){

        String question;
        Integer answer ;
        List<String> answerList = new ArrayList<>();

        //Contents for flash cards 1 - 5.
        //Topic: PC components

        quizList.get(0).setQuizTitle("PC Components");

        question = "What is the purpose of a motherboard in a computer system?";
        answerList.add(0, "To provide power to the components");
        answerList.add(1, "To store data");
        answerList.add(2, "To display images and videos");
        answerList.add(3, "To connect and communicate between components");
        answer = 4;

        flashCardLists.get(0).get(0).setQuestion(question);
        flashCardLists.get(0).get(0).setAnswerList(answerList);
        flashCardLists.get(0).get(0).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(0).get(0));

        question = "Which component of a computer is responsible for processing instructions and performing calculations?";
        answerList.add(0, "RAM");
        answerList.add(1, "CPU");
        answerList.add(2, "GPU");
        answerList.add(3, "Motherboard");
        answer = 2;

        flashCardLists.get(0).get(1).setQuestion(question);
        flashCardLists.get(0).get(1).setAnswerList(answerList);
        flashCardLists.get(0).get(1).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(0).get(1));

        question = "What is the primary purpose of a graphics card in a computer?";
        answerList.add(0, "To store data");
        answerList.add(1, "To connect peripherals");
        answerList.add(2, "To display images and videos");
        answerList.add(3, "To provide power to the system");
        answer = 3;

        flashCardLists.get(0).get(2).setQuestion(question);
        flashCardLists.get(0).get(2).setAnswerList(answerList);
        flashCardLists.get(0).get(2).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(0).get(2));

        question = "What does RAM stand for in computer terms?";
        answerList.add(0, "Random Access Memory");
        answerList.add(1, "Read-Only Memory");
        answerList.add(2, "Central Processing Unit");
        answerList.add(3, "Graphics Processing Unit");
        answer = 1;

        flashCardLists.get(0).get(3).setQuestion(question);
        flashCardLists.get(0).get(3).setAnswerList(answerList);
        flashCardLists.get(0).get(3).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(0).get(3));

        question = "Which component of a computer is responsible for long-term storage of data and programs?";
        answerList.add(0, "CPU");
        answerList.add(1, "RAM");
        answerList.add(2, "SSD");
        answerList.add(3, "Power supply");
        answer = 3;

        flashCardLists.get(0).get(4).setQuestion(question);
        flashCardLists.get(0).get(4).setAnswerList(answerList);
        flashCardLists.get(0).get(4).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(0).get(4));

        quizList.get(0).setFlashCards(flashCardLists.get(0));
        quizRepository.save(quizList.get(0));

        //Contents for flash cards 6 - 10.
        //Topic: Java Syntax basics

        quizList.get(1).setQuizTitle("Java Basics");

        question = "Which one of these keywords can be used to declare a variable in Java?";
        answerList.set(0, "int");
        answerList.set(1, "string");
        answerList.set(2, "variable");
        answerList.set(3, "declare");
        answer = 1;

        flashCardLists.get(1).get(0).setQuestion(question);
        flashCardLists.get(1).get(0).setAnswerList(answerList);
        flashCardLists.get(1).get(0).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(1).get(0));

        question = "What is the correct syntax for a single-line comment in Java?";
        answerList.set(0, "// Comment goes here");
        answerList.set(1, "/* Comment goes here */");
        answerList.set(2, "-- Comment goes here");
        answerList.set(3, "# Comment goes here");
        answer = 1;

        flashCardLists.get(1).get(1).setQuestion(question);
        flashCardLists.get(1).get(1).setAnswerList(answerList);
        flashCardLists.get(1).get(1).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(1).get(1));

        question = "Which symbol is used to indicate the end of a statement in Java?";
        answerList.set(0, ":");
        answerList.set(1, ",");
        answerList.set(2, ";");
        answerList.set(3, ".");
        answer = 2;

        flashCardLists.get(1).get(2).setQuestion(question);
        flashCardLists.get(1).get(2).setAnswerList(answerList);
        flashCardLists.get(1).get(2).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(1).get(2));

        question = "What is the correct way to create an object of a class in Java?";
        answerList.set(0, "new MyClass()");
        answerList.set(1, "create MyClass()");
        answerList.set(2, "MyClass.create()");
        answerList.set(3, "create new MyClass()");
        answer = 1;

        flashCardLists.get(1).get(3).setQuestion(question);
        flashCardLists.get(1).get(3).setAnswerList(answerList);
        flashCardLists.get(1).get(3).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(1).get(3));

        question = "Which keyword is used to define a method that does not return a value in Java?";
        answerList.set(0, "void");
        answerList.set(1, "null");
        answerList.set(2, "return");
        answerList.set(3, "boolean");
        answer = 1;

        flashCardLists.get(1).get(4).setQuestion(question);
        flashCardLists.get(1).get(4).setAnswerList(answerList);
        flashCardLists.get(1).get(4).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(1).get(4));

        quizList.get(1).setFlashCards(flashCardLists.get(1));
        quizRepository.save(quizList.get(1));

        //Contents for flash cards 10 - 15.
        //Topic: Javascript facts

        quizList.get(2).setQuizTitle("Javascript");

        question = "1. JavaScript is a high-level, interpreted programming language.";
        answerList.set(0, "True");
        answerList.set(1, "False");
        answerList.set(2, "Sometimes");
        answerList.set(3, "Depends on the context");
        answer = 1;

        flashCardLists.get(2).get(0).setQuestion(question);
        flashCardLists.get(2).get(0).setAnswerList(answerList);
        flashCardLists.get(2).get(0).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(2).get(0));

        question = "2. JavaScript is primarily used for client-side web development.";
        answerList.set(0, "True");
        answerList.set(1, "False");
        answerList.set(2, "Not sure");
        answerList.set(3, "Depends on the use case");
        answer = 1;

        flashCardLists.get(2).get(1).setQuestion(question);
        flashCardLists.get(2).get(1).setAnswerList(answerList);
        flashCardLists.get(2).get(1).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(2).get(1));

        question = "3. JavaScript was created by Brendan Eich in 1995.";
        answerList.set(0, "True");
        answerList.set(1, "False");
        answerList.set(2, "I don't know");
        answerList.set(3, "It's a possibility");
        answer = 1;

        flashCardLists.get(2).get(2).setQuestion(question);
        flashCardLists.get(2).get(2).setAnswerList(answerList);
        flashCardLists.get(2).get(2).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(2).get(2));

        question = "4. JavaScript can be used on both the front-end and back-end of web development.";
        answerList.set(0, "True");
        answerList.set(1, "False");
        answerList.set(2, "Maybe");
        answerList.set(3, "Depends on the implementation");
        answer = 1;

        flashCardLists.get(2).get(3).setQuestion(question);
        flashCardLists.get(2).get(3).setAnswerList(answerList);
        flashCardLists.get(2).get(3).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(2).get(3));

        question = "5. JavaScript supports object-oriented programming principles.";
        answerList.set(0, "True");
        answerList.set(1, "False");
        answerList.set(2, "I'm not certain");
        answerList.set(3, "It's possible");
        answer = 0;

        flashCardLists.get(2).get(4).setQuestion(question);
        flashCardLists.get(2).get(4).setAnswerList(answerList);
        flashCardLists.get(2).get(4).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(2).get(4));

        quizList.get(2).setFlashCards(flashCardLists.get(2));
        quizRepository.save(quizList.get(2));

        //Contents for flash cards 16 - 20.
        //Topic: Cat facts

        quizList.get(3).setQuizTitle("Cat facts");

        question = "1. Which of the following is the scientific name for the house cat?";
        answerList.set(0, "Felis silvestris catus");
        answerList.set(1, "Felis domesticus");
        answerList.set(2, "Felis catus");
        answerList.set(3, "Felis familiaris");
        answer = 4;

        flashCardLists.get(3).get(0).setQuestion(question);
        flashCardLists.get(3).get(0).setAnswerList(answerList);
        flashCardLists.get(3).get(0).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(3).get(0));

        question = "2. How many toes does the average house cat have?";
        answerList.set(0, "16");
        answerList.set(1, "18");
        answerList.set(2, "20");
        answerList.set(3, "22");
        answer = 2;

        flashCardLists.get(3).get(1).setQuestion(question);
        flashCardLists.get(3).get(1).setAnswerList(answerList);
        flashCardLists.get(3).get(1).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(3).get(1));

        question = "3. What is the average lifespan of a house cat?";
        answerList.set(0, "8-10 years");
        answerList.set(1, "10-12 years");
        answerList.set(2, "12-15 years");
        answerList.set(3, "15-20 years");
        answer = 3;

        flashCardLists.get(3).get(2).setQuestion(question);
        flashCardLists.get(3).get(2).setAnswerList(answerList);
        flashCardLists.get(3).get(2).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(3).get(2));

        question = "4. Which of the following is NOT a recognized cat breed?";
        answerList.set(0, "Siamese");
        answerList.set(1, "Persian");
        answerList.set(2, "Maine Coon");
        answerList.set(3, "Furballus maximus");
        answer = 4;

        flashCardLists.get(3).get(3).setQuestion(question);
        flashCardLists.get(3).get(3).setAnswerList(answerList);
        flashCardLists.get(3).get(3).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(3).get(3));

        question = "5. What is the typical body temperature range for a healthy cat?";
        answerList.set(0, "35-37°C (95-99°F)");
        answerList.set(1, "38-40°C (100-104°F)");
        answerList.set(2, "41-43°C (106-109°F)");
        answerList.set(3, "44-46°C (111-115°F)");
        answer = 2;

        flashCardLists.get(3).get(4).setQuestion(question);
        flashCardLists.get(3).get(4).setAnswerList(answerList);
        flashCardLists.get(3).get(4).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(3).get(4));

        quizList.get(3).setFlashCards(flashCardLists.get(3));

        quizRepository.save(quizList.get(3));

        //Populating flash cards 20 - 25.
        //Topic: Outer space

        quizList.get(4).setQuizTitle("PC Components");

        question = "1. Which planet in our solar system is known for its prominent rings?";
        answerList.set(0, "Mars");
        answerList.set(1, "Jupiter");
        answerList.set(2, "Saturn");
        answerList.set(3, "Neptune");
        answer = 2;

        flashCardLists.get(4).get(0).setQuestion(question);
        flashCardLists.get(4).get(0).setAnswerList(answerList);
        flashCardLists.get(4).get(0).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(4).get(0));

        question = "2. What is the largest moon in our solar system?";
        answerList.set(0, "Europa");
        answerList.set(1, "Titan");
        answerList.set(2, "Ganymede");
        answerList.set(3, "Enceladus");
        answer = 2;

        flashCardLists.get(4).get(1).setQuestion(question);
        flashCardLists.get(4).get(1).setAnswerList(answerList);
        flashCardLists.get(4).get(1).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(4).get(1));

        question = "3. What is the name of the closest star to our solar system?";
        answerList.set(0, "Proxima Centauri");
        answerList.set(1, "Sirius");
        answerList.set(2, "Alpha Centauri");
        answerList.set(3, "Betelgeuse");
        answer = 1;

        flashCardLists.get(4).get(2).setQuestion(question);
        flashCardLists.get(4).get(2).setAnswerList(answerList);
        flashCardLists.get(4).get(2).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(4).get(2));

        question = "4. What is a celestial event where the Moon passes between the Earth and the Sun?";
        answerList.set(0, "Lunar eclipse");
        answerList.set(1, "Solar eclipse");
        answerList.set(2, "Asteroid collision");
        answerList.set(3, "Meteor shower");
        answer = 1;

        flashCardLists.get(4).get(3).setQuestion(question);
        flashCardLists.get(4).get(3).setAnswerList(answerList);
        flashCardLists.get(4).get(3).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(4).get(3));

        question = "5. Which of the following is NOT a type of galaxy?";
        answerList.set(0, "Spiral");
        answerList.set(1, "Elliptical");
        answerList.set(2, "Irregular");
        answerList.set(3, "Crescent");
        answer = 3;

        flashCardLists.get(4).get(4).setQuestion(question);
        flashCardLists.get(4).get(4).setAnswerList(answerList);
        flashCardLists.get(4).get(4).setAnswer(answer);

        flashCardRepository.save(flashCardLists.get(4).get(4));

        quizList.get(4).setFlashCards(flashCardLists.get(4));
        quizRepository.save(quizList.get(4));


    }

}
