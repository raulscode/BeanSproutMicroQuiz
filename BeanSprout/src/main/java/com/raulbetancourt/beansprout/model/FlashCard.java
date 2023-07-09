package com.raulbetancourt.beansprout.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "flashcards")
public class FlashCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer cardID;
    String question;
    Integer answer;

    @ElementCollection
    @CollectionTable(name = "answer_table", joinColumns = @JoinColumn(name = "cardid"))
    @Column(name="answer_list")
    List<String> answerList = new ArrayList<>();

    @ManyToMany(mappedBy = "flashCards")
    private List<Quiz> quizCollect;

    public Integer getCardID() {
        return cardID;
    }

    public void setCardID(Integer cardID) {
        this.cardID = cardID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }

    public List<Quiz> getQuizCollect() {
        return quizCollect;
    }

    public void setQuizCollect(List<Quiz> quizCollect) {
        this.quizCollect = quizCollect;
    }
}
