package com.raulbetancourt.beansprout.controller;

import com.raulbetancourt.beansprout.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raulbetancourt.beansprout.model.Quiz;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//Controller for page where you can generate a new quiz.
//Basically just gets title of quiz, then passes control to flash card generator.
//The way this app is organized, a "quiz" is basically just an ArrayList of Flash Card questions.
@Controller
@RequestMapping("/")
public class QuizGenerationController {

    @Autowired
    private QuizRepository quizRepository;

    //Used to show the page where you can generate the quiz.
    //Now this page is handled by user login controller.

    @GetMapping("/generatequiz")
    public String showQuizGenerator(){

        return "quiz_generator";
    }


    //Grabs the info inputted by user to generate the quiz,
    //which is mostly at this point just a title and id with an empty flashcard arraylist.
    @PostMapping("/generatequiz")
    public String getQuizTitle(Quiz quiz, RedirectAttributes redirectAttributes){

        quizRepository.save(quiz);

        redirectAttributes.addFlashAttribute("quiz", quiz);

        return "redirect:/generatecard";

    }

}
