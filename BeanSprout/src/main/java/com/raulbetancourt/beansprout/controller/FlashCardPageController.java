package com.raulbetancourt.beansprout.controller;

import com.raulbetancourt.beansprout.Service.FlashCardService;
import com.raulbetancourt.beansprout.model.FlashCard;
import com.raulbetancourt.beansprout.repository.FlashCardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

//Controller for the page that displays an individual flash card
@Controller
public class FlashCardPageController {


    private FlashCardRepository flashCardRepository;
    private FlashCardService flashCardService;

    public FlashCardPageController(FlashCardRepository flashCardRepository, FlashCardService flashCardService)
    {
        this.flashCardRepository = flashCardRepository;
        this.flashCardService = flashCardService;
    }


    @GetMapping({"/flashcard"})
    public String displayFlashCard(Model model, @RequestParam(value = "cardid", required = false) Integer cardID, @RequestParam(value="randomcard", required = false) String randomCard)
    {

        //If randomCard is true, then get random card ID.
        if((randomCard != null) && (randomCard.equals("true")))
        {
            List<FlashCard> cards = flashCardService.getAllFlashCards();
            Random randomNumber = new Random();
            Integer randomCardNumber = randomNumber.nextInt(cards.size()) + 0;
            cardID = cards.get(randomCardNumber).getCardID();
        }

        //Assign quizID a value if it is null.
        if(cardID == null)
        {
            cardID = 1;
        }

        FlashCard flashCard = flashCardRepository.findById(cardID).orElse(null);

        if(flashCard != null) {

            String question = flashCard.getQuestion();
            Integer answerNumber = flashCard.getAnswer();
            if(answerNumber == null)
            {
                answerNumber = 1;
            }
            List<String> answerList = flashCard.getAnswerList();
            String answer = answerList.get(answerNumber - 1);

            String quizName = "Quiz Name";

            model.addAttribute("displayQuestion", question);
            model.addAttribute("answer", answer);
            model.addAttribute("answerList", answerList);
            model.addAttribute("quizName", quizName);

        }

        return "flash_card_page";
    }




}
