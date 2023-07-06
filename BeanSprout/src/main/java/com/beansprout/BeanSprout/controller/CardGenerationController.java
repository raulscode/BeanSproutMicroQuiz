package com.beansprout.BeanSprout.controller;

import com.beansprout.BeanSprout.model.FlashCard;
import com.beansprout.BeanSprout.model.Quiz;
import com.beansprout.BeanSprout.repository.FlashCardRepository;
import com.beansprout.BeanSprout.repository.QuizRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class CardGenerationController {


    @Autowired
    private FlashCardRepository flashCardRepository;

    @Autowired
    private QuizRepository quizRepository;

    private Quiz quizEntity;


    @GetMapping ("/generatecard")
    public String showFlashCardGenerator(Model model, @ModelAttribute("quiz") Quiz quiz) {

        quizEntity = quiz;

        return "FlashCardGenerator";

    }

    @PostMapping("/generatecard")
    public String makeFlashCard(FlashCard flashcard, RedirectAttributes redirectAttributes)
    {

        flashCardRepository.save(flashcard);

        List<FlashCard> flashCardList;
        if(quizEntity.getFlashCards() == null) {
            flashCardList = new ArrayList<>();
        }
        else{
            flashCardList = quizEntity.getFlashCards();
        }
        flashCardList.add(flashcard);
        quizEntity.setFlashCards(flashCardList);
        quizRepository.save(quizEntity);

        redirectAttributes.addFlashAttribute("quiz", quizEntity);

        return "redirect:/generatecard";
    }


}
