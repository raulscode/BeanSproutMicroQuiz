package com.beansprout.BeanSprout.controller;

import com.beansprout.BeanSprout.model.FlashCard;
import com.beansprout.BeanSprout.model.Quiz;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.beansprout.BeanSprout.repository.QuizRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuizPageController {

    private QuizRepository quizRepository;

    private Integer quizID;

    public QuizPageController(QuizRepository quizRepository)
    {
        this.quizRepository = quizRepository;
    }

    @GetMapping("/quiz")
    public String sendData(Model model)
    {
        //test id
        quizID = 5253;

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
    public String sendQuizGrade(HttpServletRequest request, RedirectAttributes redirectAttributes){

        Quiz quiz = quizRepository.findById(quizID).orElse(null);

        List<FlashCard> quizQuestions = quiz.getFlashCards();
        Integer correctAnswer;
        Integer givenAnswer;
        int questionCount = 0;
        int correctAnswerCount = 0;

        //Grading logic
        for(FlashCard card: quizQuestions) {
            questionCount++;
            correctAnswer = card.getAnswer();
            String cardValue = request.getParameter("card" + questionCount);
            givenAnswer = Integer.parseInt(cardValue);
            if(correctAnswer == givenAnswer)
            {
                correctAnswerCount++;
            }
        }

        Long quizGrade = Math.round(100 * ((double) correctAnswerCount) / ((double) questionCount));

        redirectAttributes.addFlashAttribute("quizGrade", quizGrade);

        return "redirect:/gradepage";
    }



}
