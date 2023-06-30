package com.beansprout.BeanSprout.controller;

import com.beansprout.BeanSprout.model.FlashCard;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuizPageController {
    @GetMapping({"/quiz"})
    public String sendData(Model model)
    {
        String quizTitle = "Quiz Title";
        List<FlashCard> cardList = new ArrayList<>();

        //Example test cards
        FlashCard card1 = new FlashCard();
        FlashCard card2 = new FlashCard();
        FlashCard card3 = new FlashCard();

        List<String> exampleAnswers = new ArrayList<>();

        exampleAnswers.add("Earth");
        exampleAnswers.add("World");
        exampleAnswers.add("Pluto");
        exampleAnswers.add("Neptune");

        card1.setCardID(1000);
        card1.setQuestion("Hello?");
        card1.setAnswer(2);
        card1.setAnswerList(exampleAnswers);

        card2.setCardID(2000);
        card2.setQuestion("Which one of these is a dwarf planet?");
        card2.setAnswer(3);
        card2.setAnswerList(exampleAnswers);

        card3.setCardID(3000);
        card3.setQuestion("Which one of these is a dwarf planet?");
        card3.setAnswer(3);
        card3.setAnswerList(exampleAnswers);

        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);

        //End of example test cards

        model.addAttribute("quizTitle", quizTitle);
        model.addAttribute("cardList", cardList);
        return "QuizPage";
    }



}
