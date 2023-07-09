package com.raulbetancourt.beansprout.controller;

import com.raulbetancourt.beansprout.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raulbetancourt.beansprout.model.Quiz;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class QuizGenerationController {

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/generatequiz")
    public String showQuizGenerator(){

        return "QuizGenerator";
    }

    @PostMapping("/generatequiz")
    public String getQuizTitle(Quiz quiz, RedirectAttributes redirectAttributes){

        quizRepository.save(quiz);

        redirectAttributes.addFlashAttribute("quiz", quiz);

        return "redirect:/generatecard";

    }

}
