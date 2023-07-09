package com.raulbetancourt.beansprout.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer quizID;
    String quizTitle;
    //List<Integer> cardList = new ArrayList<>();

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
