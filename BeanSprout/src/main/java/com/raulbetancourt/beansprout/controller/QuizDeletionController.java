package com.raulbetancourt.beansprout.controller;


import com.raulbetancourt.beansprout.Service.QuizService;
import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.repository.FlashCardRepository;
import com.raulbetancourt.beansprout.repository.QuizRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


//Controller for quiz deletion page.
@Controller
public class QuizDeletionController {

    private QuizRepository quizRepository;
    private FlashCardRepository flashCardRepository;

    private QuizService quizService;

    private Integer quizID;

    public QuizDeletionController(QuizRepository quizRepository, FlashCardRepository flashCardRepository, QuizService quizService) {
        this.quizRepository = quizRepository;
        this.flashCardRepository = flashCardRepository;
        this.quizService = quizService;
    }

    //Delete quiz.
    @GetMapping("/deletequiz")
    public String quizEditPage(Model model, @RequestParam("quizid") Integer quizID) {

        Quiz quiz = quizRepository.findById(quizID).orElse(null);

        if(quizID == null)
        {
            return "error";
        }

        //Another method will need to use the quizID.
        this.quizID = quizID;

        model.addAttribute("quiz", quiz);

        return "delete_quiz";

    }

    //Gets confirmation from user to delete quiz.
    @PostMapping("/deletequiz")
    public String getQuizEdits(Model model, Quiz quiz, RedirectAttributes redirectAttributes) {

        String operationString = "deleted";
        String entityString = "quiz";

            try{
                quizService.deleteQuizById(quizID);
                operationString = "deleted";
            }
            catch (Exception e)
            {
                System.out.println("Error deleting entity!");
                System.out.println(e);
                return "redirect:/error";
            }

        redirectAttributes.addFlashAttribute("operation", operationString);
        redirectAttributes.addFlashAttribute("entitytype", entityString);
        return "redirect:/successpage";
    }

}
