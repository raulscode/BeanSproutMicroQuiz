package com.raulbetancourt.beansprout.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//Model for the FlashCard entity.
//Holds attributes such as a card ID, a question with list of answers ("front" of card) and
//an integer denoting the correct answer from that list of answers.
//All flash cards are multiple choice, since they are the building blocks for quizzes.
@Entity
@Table(name = "flashcards")
public class FlashCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer cardID;
    String question;

    //Holds an integer that indicates the index of the correct answer in answerList ArrayList (below).
    Integer answer;

    //Holds list of answers in an ArrayList of strings.
    //1 is correct, 3 are incorrect. Correct answer is stored in "answer" variable.
    @ElementCollection
    @CollectionTable(name = "answer_table", joinColumns = @JoinColumn(name = "cardid"))
    @Column(name="answer_list")
    List<String> answerList = new ArrayList<>();

    //Establishes the relationship that FlashCard entities have to Quiz entities.
    //In practice, the flash cards are actually many-to-one, since they are associated with one quiz,
    //but mapping as many-to-many allows for more flexibility for future changes in the app.
    //For now, the relationship of quiz-to-flashcards matters more than flashcard-to-quiz for our purposes anyway.
    @ManyToMany(mappedBy = "flashCards")
    private List<Quiz> quizCollect;

    public FlashCard() {

    }

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
