package com.beansprout.BeanSprout.Service;

import com.beansprout.BeanSprout.model.Quiz;
import com.beansprout.BeanSprout.repository.QuizRepository;
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
