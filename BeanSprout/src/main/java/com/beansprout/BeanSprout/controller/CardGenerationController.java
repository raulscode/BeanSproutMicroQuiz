package com.beansprout.BeanSprout.controller;

import com.beansprout.BeanSprout.model.FlashCard;
import com.beansprout.BeanSprout.repository.FlashCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class CardGenerationController {


    @Autowired
    private FlashCardRepository flashCardRepository;

    @GetMapping ("/generatecard")
    public String showFlashCardGenerator(Model model) {
        model.addAttribute("flashcard", new FlashCard());
        return "FlashCardGenerator";

    }

    @PostMapping("/generatecard")
    public String makeFlashCard(FlashCard flashcard, Model model)
    {
        //flashcard.setAnswerList(answerList);
        //flashcard.setAnswer(answer);
        //flashcard.setQuestion(question);
        flashCardRepository.save(flashcard);
        return "redirect:/generatecard";
    }

}
