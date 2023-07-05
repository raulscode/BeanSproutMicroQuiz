package com.beansprout.BeanSprout.controller;

import com.beansprout.BeanSprout.model.FlashCard;
import com.beansprout.BeanSprout.model.Quiz;
import com.beansprout.BeanSprout.repository.FlashCardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FlashCardPageController {


    private FlashCardRepository flashCardRepository;

    public FlashCardPageController(FlashCardRepository flashCardRepository)
    {
        this.flashCardRepository = flashCardRepository;
    }

    @GetMapping({"/flashcard"})
    public String displayFlashCard(Model model)
    {
        FlashCard flashCard = flashCardRepository.findById(1).orElse(null);

        if(flashCard != null) {

            String question = flashCard.getQuestion();
            List<String> answerList = flashCard.getAnswerList();

           /* List<Quiz> quizList = flashCard.getQuizCollect();
            Quiz flashQuiz = new Quiz();
            quizList.add(flashQuiz); */

            String quizName = "Quiz Name";

            model.addAttribute("displayQuestion", question);
            model.addAttribute("answerList", answerList);
            model.addAttribute("quizName", quizName);

        }

        return "FlashCardPage";
    }




}
