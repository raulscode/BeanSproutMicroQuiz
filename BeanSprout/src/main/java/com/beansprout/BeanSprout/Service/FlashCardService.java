package com.beansprout.BeanSprout.Service;

import com.beansprout.BeanSprout.model.FlashCard;
import com.beansprout.BeanSprout.model.Quiz;
import com.beansprout.BeanSprout.repository.FlashCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashCardService {

    private FlashCardRepository flashCardRepository;

    public FlashCardService(FlashCardRepository flashCardRepository) {
        this.flashCardRepository = flashCardRepository;
    }

    public List<FlashCard> getAllFlashCards(){

        return (List<FlashCard>) flashCardRepository.findAll();
    }

}
