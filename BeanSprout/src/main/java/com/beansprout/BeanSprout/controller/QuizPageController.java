package com.beansprout.BeanSprout.controller;

import com.beansprout.BeanSprout.model.FlashCard;
import com.beansprout.BeanSprout.model.Quiz;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.beansprout.BeanSprout.repository.QuizRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class QuizPageController {

    private QuizRepository quizRepository;

    public QuizPageController(QuizRepository quizRepository)
    {
        this.quizRepository = quizRepository;
    }

    @GetMapping("/quiz")
    public String sendData(Model model)
    {
        //test id
        Integer quizID = 5052;

        Quiz quiz = quizRepository.findById(quizID).orElse(null);

        if(quiz != null) {
            String quizTitle = quiz.getQuizTitle();
            List<FlashCard> cardList = quiz.getFlashCards();

            model.addAttribute("quizTitle", quizTitle);
            model.addAttribute("cardList", cardList);

        }

        return "QuizPage";
    }


    @PostMapping("/quiz")
    public String sendQuizGrade(RedirectAttributes redirectAttributes){

        Double quizGrade = 0.0;

        redirectAttributes.addFlashAttribute("quizGrade", quizGrade);

        return "/gradepage";
    }



}
