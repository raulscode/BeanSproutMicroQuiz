package com.raulbetancourt.beansprout.Service;

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

}
