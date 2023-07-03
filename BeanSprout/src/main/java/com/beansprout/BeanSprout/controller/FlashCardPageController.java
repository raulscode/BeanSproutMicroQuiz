package com.beansprout.BeanSprout.controller;

import com.beansprout.BeanSprout.model.FlashCard;
import com.beansprout.BeanSprout.repository.FlashCardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FlashCardPageController {


    private final FlashCardRepository flashCardRepository;

    public FlashCardPageController(FlashCardRepository flashCardRepository)
    {
        this.flashCardRepository = flashCardRepository;
    }

    @GetMapping({"/flashcard"})
    public String displayFlashCard(Model model)
    {
        FlashCard flashCard = flashCardRepository.findById(1000).orElse(null);

        if(flashCard != null) {

            String text = flashCard.getQuestion();
            model.addAttribute("displayQuestion", text);

        }

        return "FlashCardPage";
    }

    

}
