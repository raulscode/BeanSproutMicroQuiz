package com.beansprout.BeanSprout.controller;

import com.beansprout.BeanSprout.Service.FlashCardService;
import com.beansprout.BeanSprout.model.FlashCard;
import com.beansprout.BeanSprout.model.Quiz;
import com.beansprout.BeanSprout.repository.FlashCardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

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
                answerNumber = 0;
            }
            List<String> answerList = flashCard.getAnswerList();
            String answer = answerList.get(answerNumber - 1);

           /* List<Quiz> quizList = flashCard.getQuizCollect();
            Quiz flashQuiz = new Quiz();
            quizList.add(flashQuiz); */

            String quizName = "Quiz Name";

            model.addAttribute("displayQuestion", question);
            model.addAttribute("answer", answer);
            model.addAttribute("quizName", quizName);

        }

        return "FlashCardPage";
    }




}
