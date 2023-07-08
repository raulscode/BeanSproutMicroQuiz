package com.beansprout.BeanSprout.controller;

import com.beansprout.BeanSprout.Service.FlashCardService;
import com.beansprout.BeanSprout.model.FlashCard;
import com.beansprout.BeanSprout.model.Quiz;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FlashCardListController {

    private FlashCardService flashCardService;

    public FlashCardListController(FlashCardService flashCardService){
        this.flashCardService = flashCardService;
    }

    @GetMapping("/flashcardlist")
    public String getFlashCardList(Model model){

        List<FlashCard> flashCardList = flashCardService.getAllFlashCards();

        model.addAttribute("flashCardList", flashCardList);

        return "AllFlashCards";
    }

}
