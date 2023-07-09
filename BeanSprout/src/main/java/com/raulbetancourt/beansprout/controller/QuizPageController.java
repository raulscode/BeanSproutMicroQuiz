package com.raulbetancourt.beansprout.controller;

import com.raulbetancourt.beansprout.Service.QuizService;
import com.raulbetancourt.beansprout.model.FlashCard;
import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.repository.QuizRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Random;


@Controller
public class QuizPageController {

    private QuizRepository quizRepository;
    private QuizService quizService;

    private Integer quizID;

    public QuizPageController(QuizRepository quizRepository, QuizService quizService)
    {
        this.quizRepository = quizRepository;
        this.quizService = quizService;
    }

    @GetMapping("/quiz")
    public String sendData(Model model, @RequestParam(value= "quizid", required = false) Integer quizID, @RequestParam(value="randomquiz", required = false) String randomQuiz)
    {

        //If randomQuiz is true (the user requested a random quiz), give quizID a random value.
        if((randomQuiz != null) && (randomQuiz.equals("true")))
        {
            List<Quiz> quizzes = quizService.getQuizzes();
            Random randomNumber = new Random();
            Integer randomQuizNumber = randomNumber.nextInt(quizzes.size()) + 0;
            quizID = quizzes.get(randomQuizNumber).getQuizID();
        }

        //Assign quizID a value if it is null.
        if(quizID == null)
        {
            quizID = 5253;
        }

        //Pass quizID to class variable.
        this.quizID = quizID;

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

            //In case user does not answer, cardAnswer would be null, so let's make it a 0 (cannot match any correctAnswer).
            if(cardValue == null)
            {
                cardValue = "0";
            }

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
