package com.beansprout.BeanSprout.controller;

import com.beansprout.BeanSprout.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.beansprout.BeanSprout.model.Quiz;
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
    public String getQuizTitle(Quiz quiz, Model model, RedirectAttributes redirectAttributes){

        quizRepository.save(quiz);

        redirectAttributes.addFlashAttribute("quizTitle", quiz.getQuizTitle());
        redirectAttributes.addFlashAttribute("quizID", quiz.getQuizID());

        return "redirect:/generatecard";

    }

}
