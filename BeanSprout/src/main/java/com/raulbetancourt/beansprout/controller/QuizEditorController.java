package com.raulbetancourt.beansprout.controller;


import com.raulbetancourt.beansprout.model.FlashCard;
import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.repository.FlashCardRepository;
import com.raulbetancourt.beansprout.repository.QuizRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


//Controller for quiz editing page
@Controller
public class QuizEditorController {

    private QuizRepository quizRepository;
    private FlashCardRepository flashCardRepository;

    private Integer quizID;

    public QuizEditorController(QuizRepository quizRepository, FlashCardRepository flashCardRepository) {
        this.quizRepository = quizRepository;
        this.flashCardRepository = flashCardRepository;
    }

    //Edit quiz.
    @GetMapping("/editquiz")
    public String quizEditPage(Model model, @RequestParam("quizid") Integer quizID) {

        Quiz quiz = quizRepository.findById(quizID).orElse(null);

        if(quizID == null)
        {
            return "error";
        }

        //Another method will need to use the quizID.
        this.quizID = quizID;

        model.addAttribute("quiz", quiz);

        return "edit_quiz";

    }

    @PostMapping("/editquiz")
    public String getQuizEdits(Model model, Quiz quiz, RedirectAttributes redirectAttributes) {

        String operation = "edited";

        quiz.setQuizID(quizID);
        quizRepository.save(quiz);

        redirectAttributes.addFlashAttribute(operation);

        return "redirect:/successpage";
    }

}
