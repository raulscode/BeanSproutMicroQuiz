package com.raulbetancourt.beansprout.Service;

import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository)
    {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getQuizzes(){

        return (List<Quiz>) quizRepository.findAll();
    }

}
