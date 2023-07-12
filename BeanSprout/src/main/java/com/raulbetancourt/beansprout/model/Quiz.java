package com.raulbetancourt.beansprout.model;

import jakarta.persistence.*;

import java.util.List;

//Model for the quiz entity.
@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer quizID;
    String quizTitle;

    //Establishes relationship with the FlashCard entity.
    //They have a join table called "card_list" that associates the quizzes' IDs and the flash cards' IDs.
    @ManyToMany
    @JoinTable(name = "card_list",
                joinColumns = @JoinColumn(name = "quizid"),
                inverseJoinColumns = @JoinColumn(name = "cardid"))
    private List<FlashCard> flashCards;

    public Integer getQuizID() {
        return quizID;
    }

    public void setQuizID(Integer quizID) {
        this.quizID = quizID;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public List<FlashCard> getFlashCards() {
        return flashCards;
    }

    public void setFlashCards(List<FlashCard> flashCards) {
        this.flashCards = flashCards;
    }


}
