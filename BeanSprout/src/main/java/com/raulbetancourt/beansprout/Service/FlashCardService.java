package com.raulbetancourt.beansprout.Service;

import com.raulbetancourt.beansprout.model.FlashCard;
import com.raulbetancourt.beansprout.model.Quiz;
import com.raulbetancourt.beansprout.repository.FlashCardRepository;
import com.raulbetancourt.beansprout.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashCardService {

    private FlashCardRepository flashCardRepository;
    private QuizRepository quizRepository;

    public FlashCardService(FlashCardRepository flashCardRepository, QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
        this.flashCardRepository = flashCardRepository;
    }

    public List<FlashCard> getAllFlashCards(){

        return (List<FlashCard>) flashCardRepository.findAll();
    }

    public void deleteFlashCardById(Integer cardID) {

        //Retrieve flashcard.
        FlashCard flashcard = flashCardRepository.findById(cardID).orElseThrow();

        //Remove relationship in Quiz entity.
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
