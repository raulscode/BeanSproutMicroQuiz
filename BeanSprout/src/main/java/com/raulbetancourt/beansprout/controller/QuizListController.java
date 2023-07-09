package com.raulbetancourt.beansprout.controller;

import com.raulbetancourt.beansprout.Service.QuizService;
import com.raulbetancourt.beansprout.model.Quiz;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//Controls the page that displays list of all available quizzes.
@Controller
public class QuizListController {

    private QuizService quizService;

    public QuizListController(QuizService quizService)
    {
        this.quizService = quizService;
    }

    //Grabs list of all quizzes and displays them.
    @GetMapping("/quizlist")
    public String showQuizList(Model model) {

        List<Quiz> quizList = quizService.getQuizzes();

        model.addAttribute("quizList", quizList);

        return "all_quizzes";

    }

}
