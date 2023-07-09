package com.raulbetancourt.beansprout.Service;

import com.raulbetancourt.beansprout.model.FlashCard;
import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.repository.FlashCardRepository;
import com.raulbetancourt.beansprout.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//Flash card service class.
@Service
public class FlashCardService {

    private FlashCardRepository flashCardRepository;

    //Including quiz repository because flash card deletion method requires altering relationship with quiz entity.
    private QuizRepository quizRepository;

    public FlashCardService(FlashCardRepository flashCardRepository, QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
        this.flashCardRepository = flashCardRepository;
    }

    //Returns all flash cards that exist in flash card table, regardless of associated quiz.
    public List<FlashCard> getAllFlashCards(){

        return (List<FlashCard>) flashCardRepository.findAll();
    }

    //Takes flash card ID as parameter and deletes flash card with this ID.
    public void deleteFlashCardById(Integer cardID) {

        //Retrieve flashcard.
        FlashCard flashcard = flashCardRepository.findById(cardID).orElseThrow();

        //Remove relationship in Quiz entity first. (Otherwise it will not work.)
        for (Quiz quiz : flashcard.getQuizCollect())
        {
            quiz.getFlashCards().remove(flashcard);
        }

        //Remove relationship to any related Quiz entities on FlashCard side.
        flashcard.getQuizCollect().clear();

        //Save changes.
        flashCardRepository.save(flashcard);

        //Delete flash card.
        flashCardRepository.delete(flashcard);

    }

}
