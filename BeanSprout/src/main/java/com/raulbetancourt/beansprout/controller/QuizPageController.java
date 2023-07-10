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

//Controller for the page that displays a single quiz.
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

    //Shows individual quiz page.
    //Asks for quiz ID as parameter. If there is no quiz ID, it shows a default one.
    //Asks for "random" flag as optional parameter. If present, then shows a random quiz.
    @GetMapping("/quiz")
    public String getQuizData(Model model, @RequestParam(value= "quizid", required = false) Integer quizID, @RequestParam(value="randomquiz", required = false) String randomQuiz)
    {

        //If randomQuiz is true (the user requested a random quiz), retrieve random quiz, then get the quizID.
        if((randomQuiz != null) && (randomQuiz.equals("true")))
        {
            //First retrieve quizzes and then choose random one based on array index.
            //Am avoiding generating random ID based on a range of random numbers because database entity ID numbers
            //are not always logical/sequential. This avoids accidentally generating an ID that does not exist.
            List<Quiz> quizzes = quizService.getQuizzes();
            Random randomNumber = new Random();
            Integer randomQuizNumber = randomNumber.nextInt(quizzes.size()) + 0;
            quizID = quizzes.get(randomQuizNumber).getQuizID();
        }

        //Assign quizID a default value if it is null.
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

        return "quiz_page";
    }


    //Request form info, including selected answers
    //Grade answers
    //Send grade as flash attribute to grade page controller
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

        //Make grade into Long, rounded to nearest integer
        Long quizGrade = Math.round(100 * ((double) correctAnswerCount) / ((double) questionCount));

        redirectAttributes.addFlashAttribute("quizGrade", quizGrade);

        return "redirect:/gradepage";
    }



}
