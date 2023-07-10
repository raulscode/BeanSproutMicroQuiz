package com.raulbetancourt.beansprout.Service;

import com.raulbetancourt.beansprout.model.FlashCard;
import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;


//Service class for quiz entity.
@Service
public class QuizService {

    private QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository)
    {
        this.quizRepository = quizRepository;
    }

    //Just a method that fetches all quizzes in the quizzes table.
    public List<Quiz> getQuizzes(){

        return (List<Quiz>) quizRepository.findAll();
    }

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

}
